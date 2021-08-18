package com.cakap.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cakap.course.model.CourseEntity;
import com.cakap.course.repository.CourseRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;
    @Test
    public void createCourseTest(){


        CourseEntity course=new CourseEntity();
        course.setCourseCode("Course001");
        course.setCourseDescription("Course description");
        course.setCourseName("Course");
        course.setId(1);
        Mockito.when(courseRepository.save(course)).thenReturn(course);

        CourseEntity result=courseService.createCourse(course);

        assertEquals("0001", result.getCourseCode());

    }
}
