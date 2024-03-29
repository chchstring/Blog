package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.ParameterException;

import com.bit.blog.util.DButil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article article = new Article();
        // 处理前端请求数据
        String sid = req.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(sid);
        }catch (Exception e){
            throw new ParameterException("id错误（"+sid+")");
        }


        // 处理业务及数据库操作
        try {
            conn = DButil.getConnection();
            String sql = "select id,title,content,create_time " +
                    "from article where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs       = ps.executeQuery();
            while (rs.next()) {
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCreate_time(rs.getTimestamp("create_time"));
            }
            System.out.println(article);
        }finally {
            DButil.close(conn,ps,rs);
        }
        return article;
    }
}
