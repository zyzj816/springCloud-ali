package org.example.config;

import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.logging.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
@Slf4j
public class MyBatisFlexConfiguration implements ConfigurationCustomizer {

    @Override
    public void customize(FlexConfiguration configuration) {
        configuration.setLogImpl(StdOutImpl.class);
    }
}