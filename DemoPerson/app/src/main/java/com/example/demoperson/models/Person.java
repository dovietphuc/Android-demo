package com.example.demoperson.models;

import java.util.Date;
import java.util.UUID;

/*
1. Tạo lớp Person với các thông tin:
	- id: UUID, tự động sinh khi tạo đối tượng
	- name: String
	- date of birth: Date
	- gender: Int, 0 là nữ, 1 là nam, 2 là giới tính khác
	- address: String
 */
public class Person {
    private final UUID id;
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private String address;

    public Person(){
        id = UUID.randomUUID();
    }

    public Person(String name, Date dateOfBirth, Gender gender, String address){
        id = UUID.randomUUID();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
