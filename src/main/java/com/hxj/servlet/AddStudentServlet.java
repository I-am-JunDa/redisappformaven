package com.hxj.servlet;


import com.hxj.pojo.Student;
import com.hxj.utils.RedisOps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
@WebServlet("/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //通过uuid来生成学生id，这样的话就可以方便的进行查询
        String uuid = UUID.randomUUID().toString();
        String name = req.getParameter("name");

        String birthday = req.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String description = req.getParameter("description");

        String avgScore = req.getParameter("avgscore");
        int avgScoreInt = Integer.parseInt(avgScore);
        //构建学生对象
        Student student = new Student(uuid,name,dateTime,description,avgScoreInt,birthday);
        resp.setCharacterEncoding("utf-8");
        //存放数据
        RedisOps.setZadd("student",avgScoreInt,student);
        //跳转到显示页面
        resp.sendRedirect("/redisapp/listStudentServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
