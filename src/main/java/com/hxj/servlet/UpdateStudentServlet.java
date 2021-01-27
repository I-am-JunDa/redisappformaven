package com.hxj.servlet;

import com.alibaba.fastjson.JSON;
import com.hxj.pojo.Student;
import com.hxj.utils.RedisOps;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
@WebServlet("/updateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        //通过id查询学生的数据
        Student stu = RedisOps.findByid(id);
        String jsonStudent = JSON.toJSONString(stu);
        //执行删除操作
        RedisOps.zRem("student",jsonStudent);
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
        Student student = new Student(id,name,dateTime,description,avgScoreInt,birthday);
        //存放数据
        RedisOps.setZadd("student",avgScoreInt,student);
        //跳转到显示页面
        resp.sendRedirect("/redisapp/listStudentServlet");
    }


}
