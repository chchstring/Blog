package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.util.DButil;
import com.bit.blog.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleListServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(request.getParameter("id"));

        Connection connection = DButil.getConnection();
        String sql = "select a.id,a.title,a.content,a.create_time from article a join user u on a.user_id = u.id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(request.getParameter("id")));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Article> articles = new ArrayList<>();
        while (resultSet.next()) {
            Article article = new Article();
            article.setId(resultSet.getInt("id"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setCreate_time(resultSet.getTimestamp("create_time"));
            articles.add(article);
        }
        return articles;


    }
}
