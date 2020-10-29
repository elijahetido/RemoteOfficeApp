package com.etido.elijah.remoteoffice.DataModel;

public class Worker {
    public int id;
    public String fullName;
    public String category;
    public String email;
    public String password;

//    public Worker() {}

//    public Worker(Integer id, String fullName, String category, String email, String password) {
//        this.id = id;
//        this.fullName = fullName;
//        this.category = category;
//        this.email = email;
//        this.password = password;
//    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
