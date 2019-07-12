package com.joseph.resume;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String phone;

    private ArrayList<String> achievements;

    private ArrayList<String> experience;

    private ArrayList<String> skills;

    @NonNull
    private String aRaw;

    @NonNull
    private String eRaw;

    @NonNull
    private String sRaw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<String> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<String> experience) {
        this.experience = experience;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getaRaw() {
        return aRaw;
    }

    public void setaRaw(String aRaw) {
        this.aRaw = aRaw;
    }

    public String geteRaw() {
        return eRaw;
    }

    public void seteRaw(String eRaw) {
        this.eRaw = eRaw;
    }

    public String getsRaw() {
        return sRaw;
    }

    public void setsRaw(String sRaw) {
        this.sRaw = sRaw;
    }
}
