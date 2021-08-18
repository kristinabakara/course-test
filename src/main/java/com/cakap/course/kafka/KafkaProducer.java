package com.cakap.course.kafka;

import com.cakap.course.model.CourseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void postCourse(String topic, String groupId, CourseEntity paramCourse){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", paramCourse.getCourseName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(paramCourse));
        }catch (Exception e){
            logger.error("An error occured! '{}", e.getMessage());
        }
    }
}
