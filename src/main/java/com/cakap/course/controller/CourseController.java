package com.cakap.course.controller;

import com.cakap.course.kafka.KafkaConsumer;
import com.cakap.course.kafka.KafkaProducer;
import com.cakap.course.model.CourseEntity;
import com.cakap.course.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.CustomErrorType;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    public static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${cakap-kafka.course.post}")
    String postCourseTopic;

    @PostMapping(value = "/created", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addCourse(@RequestBody CourseEntity courseEntity){
        logger.info(("--Start create course--"));
        try {
            kafkaProducer.postCourse(postCourseTopic, kafkaGroupId, courseEntity);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponseEntityError(e.getMessage());
        }
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }
}
