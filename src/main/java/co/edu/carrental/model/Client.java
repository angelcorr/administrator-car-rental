package co.edu.carrental.model;

import java.time.LocalDate;

public class Client {
    private String fullName;
    private String id;
    private String phone;
    private int age;
    private LocalDate createAt;

    // constructor
    public Client(String fullName, String id, String phone, int age, LocalDate createAt) {
        this.fullName = fullName;
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.createAt = createAt;
    }

    //Getters and setters
    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt=createAt;
    }
}
