package com.example.library_mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryMySqlApplication {

    public static void main(String[] args) {

//        SpringApplication.run(LibraryMySqlApplication.class, args);

        // 解决循环依赖
        SpringApplication springApplication = new SpringApplication(LibraryMySqlApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);

        // 通过命令行自动打开首页
        try {
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start http://localhost:8080/home"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
