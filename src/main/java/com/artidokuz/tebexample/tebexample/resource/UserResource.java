package com.artidokuz.tebexample.tebexample.resource;


import com.artidokuz.tebexample.tebexample.models.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, String>  kafkaTemplate;
    public static final String topic = "teb";

    @GetMapping("/")
    public String greetingForm() {
        return "greeting";
    }

    @PostMapping("/")
    public String greetingSubmit(@RequestBody Data data) {
        kafkaTemplate.send(topic , data.getJson());
        return "Published succesfully";
    }

}
