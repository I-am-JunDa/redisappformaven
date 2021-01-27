package com.hxj.servlet;

import com.alibaba.fastjson.JSON;
import com.hxj.pojo.Student;
import com.hxj.utils.RedisOps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
@WebServlet("/deleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("id");
        //通过id查询学生的数据
        Student stu = RedisOps.findByid(uuid);
        String jsonStudent = JSON.toJSONString(stu);
        //执行删除操作
        RedisOps.zRem("student",jsonStudent);
        //跳转到学生显示页面
        resp.sendRedirect("/redisapp/listStudentServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
