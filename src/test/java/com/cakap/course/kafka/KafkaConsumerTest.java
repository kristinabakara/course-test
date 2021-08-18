package com.cakap.course.kafka;

import static org.mockito.Mockito.when;

import com.cakap.course.model.CourseEntity;
import com.cakap.course.service.CourseServiceImpl;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaConsumerTest {

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Autowired
    KafkaProducer kafkaProducer;

    @MockBean
    private CourseServiceImpl serviceImpl;

    @Test
    public void ConsumerTest(){
        CourseEntity course = new CourseEntity();
        course.setId(1);
        course.setCourseName("Course");
        course.setCourseDescription("Course desc");
        course.setCourseCode("Course001");

        Gson gson=new Gson();

        kafkaProducer.postCourse("cakap-kafka.course.post", "course", course);
        when(serviceImpl.createCourse(course)).thenReturn(course);

        kafkaConsumer.processPostCourse(gson.toJson(course));
    }
}
