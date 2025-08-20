package com.chatservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvPrinter implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Environment Variables ===");
        System.out.println("MYSQLHOST = " + System.getenv("MYSQLHOST"));
        System.out.println("MYSQLPORT = " + System.getenv("MYSQLPORT"));
        System.out.println("MYSQLDATABASE = " + System.getenv("MYSQLDATABASE"));
        System.out.println("MYSQLUSER = " + System.getenv("MYSQLUSER"));
        System.out.println("MYSQLPASSWORD = " + System.getenv("MYSQLPASSWORD"));
        System.out.println("=============================");
    }
}
