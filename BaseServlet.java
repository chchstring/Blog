package com.bit.blog.servlet;

import com.bit.blog.entity.JSON;
import com.bit.blog.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");


        JSON result = new JSON();

        try{
            Object data = process(request,response);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setMessage("服务器异常");
        }
        response.getWriter().write(JSONUtil.format(result));

    }

    public abstract Object process(HttpServletRequest request, HttpServletResponse response) throws  Exception;


}
