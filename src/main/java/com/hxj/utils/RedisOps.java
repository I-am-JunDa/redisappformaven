package com.hxj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxj.pojo.Student;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
public class RedisOps {
    /**
     * 往集合里面添加一个学生信息
     * @param key
     * @param score
     * @param student
     */
    public static void setZadd(String key, double score,Object student){
        Jedis jedis = RedisConnection.getJedis();
        jedis.zadd(key,score, JSON.toJSONString(student));
        jedis.close();
    }

    /**
     * 获取redis里面所有学生数据
     * @param key
     */
    public static ArrayList<Student> getZrange(String key){
        Jedis jedis = RedisConnection.getJedis();
        Set<String> zrange = jedis.zrevrange(key, 0, -1);
        ArrayList<Student> list = new ArrayList<>();
        for (String stu : zrange) {
            Student student = JSON.parseObject(stu, Student.class);
            list.add(student);
        }
        jedis.close();
        return list;

    }

    /**
     * 根据key和value删除元素
     * @param key
     * @param student
     */
    public static void zRem(String key,String student){
        Jedis jedis = RedisConnection.getJedis();
        jedis.zrem(key, student);
        jedis.close();
    }

    /**
     * 通过id查询Student
     * @param id
     * @return
     */
    public static Student findByid(String id){
        //获取list集合
        ArrayList<Student> list = RedisOps.getZrange("student");
        Student stu = null;
        for (Student s : list) {
            if(id.equals(s.getId())){
                stu = new Student(s.getId(),s.getName(),s.getBirthday(),s.getDescription(),s.getAvgscore(),s.getBirthdayDate());
                break;
            }
        }
        return stu;
    }

    /**
     * 获取数据库里面的总页数
     * @return
     */
    public static ArrayList pageTotal(){
        ArrayList<Student> studentHashSet = RedisOps.getZrange("student");
        //总共条数
        int size = studentHashSet.size();
        //设置每页显示5条数据
        int totalPage = size / 10;
        if(size%10>0){
            totalPage=totalPage+1;
        }
        ArrayList list = new ArrayList();
        for(int i =1;i<=totalPage;i++){
            list.add(i);
        }
        return list;
    }

    /**
     * 获取分页后的余数
     * @return
     */
    public static Integer pageYuShu(){
        ArrayList<Student> studentHashSet = RedisOps.getZrange("student");
        int size = studentHashSet.size();
        //计算余数
        int YuShu = size%10;
        return YuShu;
    }

}