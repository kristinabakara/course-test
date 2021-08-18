package com.cakap.course.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cakap.course.kafka.KafkaConsumer;
import com.cakap.course.kafka.KafkaProducer;
import com.cakap.course.model.CourseEntity;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Test
    public void TestCreated() throws Exception{
        CourseEntity data=new CourseEntity();
        data.setCourseCode("0001");
        data.setCourseDescription("courseDescription");
        data.setCourseName("courseName");
        data.setId(11);

        Gson gson=new Gson();
        kafkaProducer.postCourse("test", "test", data);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/course/created").header("Authorization", "Basic YWRtaW46YWRtaW4=").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(data)))

                .andExpect(status().isOk());


    }
}
