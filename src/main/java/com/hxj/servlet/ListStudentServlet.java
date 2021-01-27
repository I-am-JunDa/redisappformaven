package com.hxj.servlet;

import com.hxj.pojo.Student;
import com.hxj.utils.RedisOps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
@WebServlet("/listStudentServlet")
public class ListStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        ArrayList<Student> studentHashSet = RedisOps.getZrange("student");
        //总页数
        ArrayList list = RedisOps.pageTotal();
        int lastpage = list.size();
        int sizeall = studentHashSet.size();
        //获取list里面的学生数量，用来判断是否大于最小分页条数
        Integer less = sizeall;
        List<Student> students = null;
        //number为空时，显示数据
        if(number == null){
            if(less<=10){
                students = studentHashSet.subList(0,less);
            }else if(less>10){
                students = studentHashSet.subList(0,10);
            }
        }else {
            //number不为空时，执行分页功能
            int i = Integer.parseInt(number);
            int fromIndex = (i-1)*10;
            Integer YuShu = RedisOps.pageYuShu();
            //当余数大于0时，说明不止一页
            if(YuShu ==0){
                students = studentHashSet.subList(fromIndex, fromIndex + 10);
            //当页数为1，且余数小于10的时候
            }else if(i == 1 && less<=10){
                students = studentHashSet.subList(0,less);
            //当页数为1，且余数大于10的时候
            }else if(i == 1 && less>10){
                students = studentHashSet.subList(0,10);
            }else if(i<lastpage){
                students = studentHashSet.subList(fromIndex, fromIndex + 10);
            }else if(i==lastpage){
                students = studentHashSet.subList(fromIndex, fromIndex + YuShu);
            }
        }
        req.setAttribute("studentHashSet",students);

        req.setAttribute("totalPage",list);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
