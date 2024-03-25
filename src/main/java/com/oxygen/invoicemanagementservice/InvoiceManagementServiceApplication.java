package com.oxygen.invoicemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InvoiceManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagementServiceApplication.class, args);
    }

}
