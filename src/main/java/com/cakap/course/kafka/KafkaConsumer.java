package com.cakap.course.kafka;

import com.cakap.course.model.CourseEntity;
import com.cakap.course.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    CourseService courseService;

    @KafkaListener(topics = "cakap-kafka.course.post", groupId = "course")
    public void processPostCourse(String courseJSON){
        logger.info("received content = '{}", courseJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            CourseEntity courseEntity = mapper.readValue(courseJSON, CourseEntity.class);
            CourseEntity course = courseService.createCourse(courseEntity);
            logger.info("Success process course '{}' with topic '{}'", course.getCourseName(), "cakap-kafka.course.post");
        }catch (Exception e){
            logger.error("An error occured! '{}'", e.getMessage());
        }
    }
}
