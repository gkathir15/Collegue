package com.msf.collegue.model;

public class UserPojo {

   private String Name,Email,Mobile,Designation,PhotoUrl;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public UserPojo(String name, String email, String mobile, String designation, String photoUrl) {

        Name = name;
        Email = email;
        Mobile = mobile;
        Designation = designation;
        PhotoUrl = photoUrl;
    }
}
