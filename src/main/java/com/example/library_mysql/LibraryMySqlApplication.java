package com.example.library_mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryMySqlApplication.class, args);

        // 通过命令行自动打开首页
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
