package icu.mikanku.gobangbackend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("icu.mikanku.gobangbackend.**.mapper")
public class MybatisPlusConfig {
}
