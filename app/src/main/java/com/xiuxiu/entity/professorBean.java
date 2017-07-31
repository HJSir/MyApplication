package com.xiuxiu.entity;

/**
 * Created by jian on 2016/12/26.
 */

public class professorBean {
     private String professorName;
    private String professorTel;
    private String professorExp;
    private int professorAge;
    private String professorTime;

    public professorBean(String professorName, String professorTime) {
        this.professorName = professorName;
        this.professorTime = professorTime;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getProfessorTel() {
        return professorTel;
    }

    public int getProfessorAge() {
        return professorAge;
    }

    public String getProfessorExp() {
        return professorExp;
    }

    public String getProfessorTime() {
        return professorTime;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public void setProfessorTel(String professorTel) {
        this.professorTel = professorTel;
    }

    public void setProfessorExp(String professorExp) {
        this.professorExp = professorExp;
    }

    public void setProfessorAge(int professorAge) {
        this.professorAge = professorAge;
    }

    public void setProfessorTime(String professorTime) {
        this.professorTime = professorTime;
    }



}
