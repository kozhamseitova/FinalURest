package com.company.models;

public class Student extends Model {
    private int student_id;
    private String first_name;
    private String last_name;
    private String password;
    private int enrol_date;

    public Student() {
    }

    public Student(int id, int student_id, String first_name, String last_name, int enrol_date) {
        super.setId(id);
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.enrol_date = enrol_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnrol_date() {
        return enrol_date;
    }

    public void setEnrol_date(int enrol_date) {
        this.enrol_date = enrol_date;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", enrol_date=" + enrol_date +
                '}';
    }
}
