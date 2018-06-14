package com.msf.collegue.model;

import java.io.Serializable;

public class UserPojo implements Serializable {

   private String Name,Email,Mobile,Designation,PhotoUrl;

   private String Id;
    private String Team;

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public UserPojo(String name, String email, String mobile, String designation, String photoUrl, String id, String team) {

        Name = name;
        Email = email;
        Mobile = mobile;
        Designation = designation;
        PhotoUrl = photoUrl;
        Id = id;
        Team = team;
    }

    public UserPojo() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

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

    public UserPojo(String name, String email, String mobile, String designation, String photoUrl, String id) {
        Name = name;
        Email = email;
        Mobile = mobile;
        Designation = designation;
        PhotoUrl = photoUrl;
        Id = id;
    }
}
