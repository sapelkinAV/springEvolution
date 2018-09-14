package ru.alfabank.bankinfo;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
