package com.bit.blog.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DButilTest {
    @Test
    public void testConnection(){
        Connection connection = DButil. getConnection();
        Assert.assertNotNull(connection);

    }
}
