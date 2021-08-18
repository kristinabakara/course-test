package com.cakap.course.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "public", catalog = "course")
public class CourseEntity {

    private int id;
    private String courseName;
    private String courseDescription;
    private String courseCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_description")
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Basic
    @Column(name = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return id == that.id &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(courseDescription, that.courseDescription) &&
                Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, courseName,courseDescription,courseCode);
    }
}
