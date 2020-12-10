package com.aeter.training;



import com.aeter.training.detect.DetectLabel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.io.IOException;



@SpringBootApplication
public class TrainingApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TrainingApplication.class, args);
        //SpringApplication.run(View.class, args);


    }

}
