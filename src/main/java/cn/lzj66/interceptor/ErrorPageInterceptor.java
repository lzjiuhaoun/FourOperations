package cn.lzj66.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

/**
 * 处理错误页面的拦截器
 */
public class ErrorPageInterceptor implements HandlerInterceptor {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return true;
    }
}


//public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
//    private List<Integer> errorCodeList = Arrays.asList(404,403,500,501);
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(errorCodeList.contains(response.getStatus())){
//            response.sendRedirect("/error/"+response.getStatus());
//            return false;
//        }
//        return super.preHandle(request, response, handler);
//    }
//}
