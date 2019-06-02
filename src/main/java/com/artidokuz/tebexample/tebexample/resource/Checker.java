package com.artidokuz.tebexample.tebexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Checker {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public static final String TOPIC = "teb";
    public static List<String> oldData = new ArrayList<>();

    @PostConstruct
    @Scheduled(fixedDelay = 500)
    public void Check() throws IOException {
        File file = new File("D:\\Save\\java\\teb\\producer\\src\\main\\resources\\test.txt");
        System.out.println("Attempting to read from file in: " + file.getCanonicalPath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = "";
        List<String> newData = new ArrayList<>();
        while ((st = br.readLine()) != null)
            newData.add(st);

        int i = 0;
        int limit = oldData.size();
        List<String> diff = new ArrayList<>();
        for (String str : newData) {
            if (limit <= i) {
                diff.add(str);
            } else {
                if (!str.equals(oldData.get(i)) && !str.equals("")){
                    diff.add(str);
                }
            }
            i++;
        }
        for (String str : diff){
            kafkaTemplate.send(TOPIC , str);
            System.out.println(str);
            System.out.println("Send.");
        }
        System.out.println("checked.");
        if(oldData.equals(newData)){
            System.out.println("No new data.");
        }else{
            System.out.println("Added new data.");
            oldData = newData;
        }
    }
}
