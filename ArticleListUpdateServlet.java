package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.BusinessException;
import com.bit.blog.util.DButil;
import com.bit.blog.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/articleUpdate")
public class ArticleListUpdateServlet extends BaseServlet{


    @Override
    public Object process(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        Connection connection =null;
        PreparedStatement preparedStatement= null;
    //处理前端请求数据 {"id"...}
        Article article = JSONUtil.get(request,Article.class);
        //处理数据库操作
        try {
            connection = DButil.getConnection();
            String sql = "update article set title=?,content=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setInt(3,article.getId());


            int r =  preparedStatement.executeUpdate();
            if(r>0){
                return r;
            }else {
                throw new BusinessException("没有该文章"+article.getId());
            }

        } finally {
            DButil.close(connection, preparedStatement,null);
        }
    }

}
