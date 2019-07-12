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
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private ArrayList<Education> achievements = new ArrayList<>();

    @NonNull
    private ArrayList<Experience> experience = new ArrayList<>();

    @NonNull
    private ArrayList<Skill> skills = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ArrayList<Education> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<Education> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<Experience> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<Experience> experience) {
        this.experience = experience;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}
