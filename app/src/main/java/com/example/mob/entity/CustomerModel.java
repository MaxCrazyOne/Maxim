package com.example.mob.entity;

import java.io.Serializable;

public class CustomerModel implements Serializable {

    String name;
    String age;
    String gender;
    String jop;
    String idCard;
    String PhoneNumber;
    String address;

    public CustomerModel(String name, String age, String gender, String jop, String idCard, String phoneNumber, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.jop = jop;
        this.idCard = idCard;
        PhoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJop() {
        return jop;
    }

    public void setJop(String jop) {
        this.jop = jop;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", jop='" + jop + '\'' +
                ", idCard='" + idCard + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}