package cn.lzj66;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: ApplicationStart
 * Package: cn.lzj66
 * Description:
 *
 * @Author 工学院-liuzhaojun
 * @Create 2024/3/1 15:59
 */
@SpringBootApplication
@ServletComponentScan //配置druid必须加的注解，如果不加，访问页面打不开，filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
@EnableTransactionManagement//启动事务
@ComponentScan(basePackages = "cn.lzj66")
@MapperScan("cn.lzj66.dao")
@PropertySource("application.properties")
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
