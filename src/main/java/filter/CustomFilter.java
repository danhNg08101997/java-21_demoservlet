//package filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//// urlPatterns khi nguoi dung goi link duoc quy dinh trong day thi filter se duoc kich hoat
//@WebFilter(urlPatterns = {"/login"})
//public class CustomFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
////        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    // Noi quy dinh rule
//        System.out.println("Filter da duoc kich hoat");
//        // cho phep di vao link ma nguoi dung req
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//
//}
