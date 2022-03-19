package com.uml.projectapp;

import com.uml.projectapp.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wuyuda
 * @date 2022-03-18 19:40
 */
@SpringBootTest
public class DataBaseTest {

    @Autowired
    UserDao userDao;

    @Test
    public void mainTest(){
        Long id = 1354971607569526785L;
        System.out.println(id);
        System.out.println(userDao == null);
    }
}
