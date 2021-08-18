package com.cakap.course.service;

import com.cakap.course.model.CourseEntity;
import com.cakap.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("CourseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public CourseEntity createCourse(CourseEntity courseEntity){
        return courseRepository.save(courseEntity);
    }

}
