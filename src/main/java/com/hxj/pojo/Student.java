package com.hxj.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date: 2021/1/26
 * @Author: 黄先军
 * @Description:
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -3210884885630038713L;

    private String id;
    private String name;
    private Date birthday;
    private String description;
    private Integer avgscore;
    private String birthdayDate;

    public Student() {
    }

    public Student(String id, String name, Date birthday, String description, Integer avgscore,String birthdayDate) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.description = description;
        this.avgscore = avgscore;
        this.birthdayDate = birthdayDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", avgscore=" + avgscore +
                ", birthdayDate='" + birthdayDate + '\'' +
                '}';
    }
}
