package com.reciver.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String gender;

    private Integer marks1;
    private Integer marks2;
    private Integer marks3;
    private Integer total;
    private Double average;
    private String result;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMarks1() {
        return marks1;
    }

    public void setMarks1(Integer marks1) {
        this.marks1 = marks1;
    }

    public Integer getMarks2() {
        return marks2;
    }

    public void setMarks2(Integer marks2) {
        this.marks2 = marks2;
    }

    public Integer getMarks3() {
        return marks3;
    }

    public void setMarks3(Integer marks3) {
        this.marks3 = marks3;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // Custom Methods
    @PrePersist
    @PreUpdate
    private void calculateTotalAndResult() {
        if (marks1 != null && marks2 != null && marks3 != null) {
            this.total = marks1 + marks2 + marks3;
            this.average = total / 3.0;

            if (marks1 >= 35 && marks2 >= 35 && marks3 >= 35) {
                this.result = "PASS";
            } else {
                this.result = "FAIL";
            }
        }
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}
