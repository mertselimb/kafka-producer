package com.artidokuz.tebexample.tebexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
public class Checker {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public static final String TOPIC = "teb";
    public static String oldData = "";

    @PostConstruct
    @Scheduled(fixedDelay=5000)
    public void Check() throws IOException {
        File file = new File("D:\\Save\\java\\teb\\producer\\src\\main\\resources\\test.txt");
        System.out.println("Attempting to read from file in: "+file.getCanonicalPath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = "";
        String newData = "";
        while ((st = br.readLine()) != null)
           newData += st;
        String diff = difference(oldData,newData);
        if(diff != "")
        {
            oldData = newData;
            kafkaTemplate.send(TOPIC , diff);
            System.out.println("Send.");
        }
        System.out.println("checked.");
    }

    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        int at = indexOfDifference(str1, str2);
        if (at == -1) {
            return "";
        }
        return str2.substring(at);
    }

    public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return -1;
        }
        if (cs1 == null || cs2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                break;
            }
        }
        if (i < cs2.length() || i < cs1.length()) {
            return i;
        }
        return -1;
    }
}
