package cn.lzj66.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.请求到登录页，放行

        //2.有一些操作要通过，获取验证码，登录，注销

        //3.已经登录，放行
        if(request.getSession().getAttribute("student") != null ){
            return true;
        }
        //4.非法请求 需要登录才能访问，直接到登录页面
        System.out.println("student 拦截器");
        response.sendRedirect("/error/404");
        return false;
    }
}

//public class StudentInterceptor extends HandlerInterceptorAdapter {
////    private static final Logger logger = LoggerFactory.getLogger(StudentInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.请求到登录页，放行
//
//        //2.有一些操作要通过，获取验证码，登录，注销
//
//        //3.已经登录，放行
//        if(request.getSession().getAttribute("student") != null ){
//            return true;
//        }
//        //4.非法请求 需要登录才能访问，直接到登录页面
//        // request.getRequestDispatcher("/reader/index").forward(request,response);
//        System.out.println("student 拦截器");
//        response.sendRedirect("/error/404");
//        return false;
////        String url = request.getRequestURL().toString();
////        String method = request.getMethod();
////        String uri = request.getRequestURI();
////        String queryString = request.getQueryString();
////        System.out.println(request.getParameterMap());
////        System.out.println(String.format("请求参数，url:%s,method:%s,uri:%s,params:%s",url,method,uri,queryString));
////        logger.info(String.format("请求参数，url:%s,method:%s,uri:%s,params:%s",url,method,uri,queryString));
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//
//    }
//}
