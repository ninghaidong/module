package com.xbtx.mallmodel;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: 宁
 * @className: builder 模式
 * @date: 2021/10/12 15:30
 */
public class Student {

    private final int stuId;//必须

    private final String name;//必须

    private final int age;//可选

    private final int gender;//可选

    private final String address;//可选

    private Student(StudentBuilder builder){

        this.stuId = builder.stuId;

        this.name = builder.name;

        this.age = builder.age;

        this.gender = builder.gender;

        this.address = builder.address;

    }

    public int getStuId() {

        return stuId;

    }

    public String getName() {

        return name;

    }

    public int getAge() {

        return age;

    }

    public int getGender() {

        return gender;

    }

    public String getAddress() {

        return address;

    }

    public static class StudentBuilder{

        private final int stuId;

        private final String name;

        private int age;

        private int gender;

        private String address;

        public StudentBuilder(int stuId,String name){

            this.stuId = stuId;

            this.name = name;

        }

        public StudentBuilder setAge(int age){

            this.age = age;

            return this;

        }

        public StudentBuilder setGender(int gender){

            this.gender = gender;

            return this;

        }

        public StudentBuilder setAddress(String address){

            this.address = address;

            return this;

        }

        public Student build(){

            return new Student(this);

        }

    }

    public void Toast(Context context){
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

}