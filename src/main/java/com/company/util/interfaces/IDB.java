package com.company.util.interfaces;

import java.util.List;

public interface IDB<T>{

    T getStudentById(int id);
    List<T> getAllStudents();
    List<T> getAllStudentsByMajor(int major_id);
    List<T> getAllStudentsByGroup(int group_id);
}
