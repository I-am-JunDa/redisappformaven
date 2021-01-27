package com.hxj.servlet;

import com.hxj.pojo.Student;
import com.hxj.utils.RedisOps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
@WebServlet("/findByidServlet")
public class FindByidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Student stu = RedisOps.findByid(id);
        Date birthday = stu.getBirthday();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(birthday);
        req.setAttribute("sBirthday",format);
        req.setAttribute("student",stu);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
