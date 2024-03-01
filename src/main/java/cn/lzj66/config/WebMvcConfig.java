package cn.lzj66.config;

import cn.lzj66.interceptor.ErrorPageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfig
 * Package: cn.lzj66.config
 * Description:
 *
 * @Author 工学院-liuzhaojun
 * @Create 2024/3/1 16:06
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new TeacherInterceptor());
//        registry.addInterceptor(new ErrorPageInterceptor());
        registry.addInterceptor(new ErrorPageInterceptor()).addPathPatterns("/**");//默认拦截所有路径
    }
}
