package com.imooc.oa.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 页面跳转Servlet
 */
@WebServlet(name="ForwardServlet", urlPatterns = "/forward/*")
public class ForwardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        /** 动态提取 把第一个/抛出外 再去寻找第一个‘/’
         * /forward/form
         * /forward/a/b/c/form
         */
        String subUri = uri.substring(1);
        String page = subUri.substring(subUri.indexOf("/"));
        request.getRequestDispatcher(page + ".ftl").forward(request,response); //扩展名 + web.xml映射路径
    }
}
