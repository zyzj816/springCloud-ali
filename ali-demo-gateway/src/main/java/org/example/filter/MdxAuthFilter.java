package org.example.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.example.alidemocommon.utils.CheckSkip;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author : jiagang
 * @date : Created in 2022/8/8 15:30
 */
@Component
@Slf4j
public class MdxAuthFilter implements GlobalFilter, Ordered {
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private JWTProvider jwtProvider;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${allowed.paths}")
    private String paths; // 不需要登录就能访问的路径

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("=========================请求进入filter=========================");

        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getPath().toString();

        boolean allowedPath = false;
        if (paths != null && !paths.equals("")){
            allowedPath = CheckSkip.checkSkipAuthUrls(requestPath, paths.split(","));
        }
        if (allowedPath || StringUtils.isEmpty(requestPath)){
            return chain.filter(exchange);
        }

        // 验证token
        String authHeader = exchange.getRequest().getHeaders().getFirst(tokenHeader);
        if (authHeader != null && authHeader.startsWith(prefix)){
            String authToken = authHeader.substring(prefix.length());
            String userName = jwtProvider.getUserNameFromToken(authToken);

            // 查询redis
            Object token = redisUtils.get("token" + userName);
            if (token == null){
                log.error("token验证失败或已过期...");
                return writeResponse(exchange.getResponse(),401,"token验证失败或已过期...请重新登录");
            }

            // 这里也可以使用 jwtProvider.validateToken() 来验证token,使用redis是因为管理员可以在任意时间将用户token踢出
            // 去除首尾空格
            String trimAuthToken = authToken.trim();
            if (! trimAuthToken.equals(token.toString())){
                log.error("token验证失败或已过期...");
                return writeResponse(exchange.getResponse(),401,"token验证失败或已过期...请重新登录");
            }
        }else {
            return writeResponse(exchange.getResponse(),500,"token不存在");
        }

        log.info("token验证成功...");
        return chain.filter(exchange);
    }

    /**
     *  Ordered类是过滤器的执行级别，数值越小执行顺序越靠前,值越小执行顺序越靠前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 构建返回内容
     *
     * @param response ServerHttpResponse
     * @param code     返回码
     * @param msg     返回数据
     * @return Mono
     */
    protected Mono<Void> writeResponse(ServerHttpResponse response, Integer code, String msg) {
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("msg", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
