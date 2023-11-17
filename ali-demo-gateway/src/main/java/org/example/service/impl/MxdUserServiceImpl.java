package org.example.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.example.dao.MxdUserDao;
import org.example.dto.LoginDto;
import org.example.pojo.MxdUser;
import org.example.pojo.table.MxdUserTableDef;
import org.example.service.MxdUserService;
import com.mybatisflex.core.query.QueryWrapper;
import org.example.filter.JWTProvider;
import org.example.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MxdUserServiceImpl implements MxdUserService {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private MxdUserDao mxdUserDao;
    @Resource
    private JWTProvider jwtProvider;

    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    public LoginDto userLogin(LoginDto login) {
        MxdUser mdxUser = mxdUserDao.selectOneByQuery(QueryWrapper.create().select().where(MxdUserTableDef.MXD_USER.USER_NAME.eq(login.getUserName())));
        if (mdxUser == null){
            throw new RuntimeException("用户不存在");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 判断用户名密码是否正确
        if (StringUtils.isEmpty(mdxUser.getUserName()) ||
                ! encoder.matches(login.getPassWord(), mdxUser.getPassword())){
            throw new RuntimeException("用户名或者密码错误");
        }
        // 生成token
        String token = jwtProvider.generateToken(mdxUser.getUserName());

        // 将token存入redis
        redisUtils.set("token" + mdxUser.getUserName(),token,604800);

        return LoginDto.builder()
                .passWord(mdxUser.getPassword())
                .userName(mdxUser.getUserName())
                .token(prefix + " " + token).build();

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        System.out.println(password);
    }
}
