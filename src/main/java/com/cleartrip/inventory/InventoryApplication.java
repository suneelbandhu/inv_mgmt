package com.cleartrip.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class InventoryApplication {
    public static Logger logger = Logger.getLogger(InventoryApplication.class.getName());
    private static FileHandler fh;
    public static void main(String[] args) {
        try {
            fh = new FileHandler("/home/tech/inventory.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("logging initialized");

        } catch(Exception e){

        }
		SpringApplication.run(InventoryApplication.class, args);
	}
}
