package com.iot.quickhpu.pojo;

import java.io.Serializable;

public class User implements Serializable {

    private String studentId;//[0] 学生号
    private String name;//[1] 姓名
    private String sex;//3  性别
    private String age;//7 年龄
    private String institute;// 学院  12
    private String major;// 专业  13
    private String grade;//14  年级
    private String studentClass;//15 班级


    public User() {
    }

    public User(String studentId, String name, String sex, String age, String institute, String major, String grade, String studentClass) {
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.institute = institute;
        this.major = major;
        this.grade = grade;
        this.studentClass = studentClass;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", institute='" + institute + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }
}
