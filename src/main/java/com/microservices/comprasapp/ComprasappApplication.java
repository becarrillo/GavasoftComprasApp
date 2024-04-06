package com.microservices.comprasapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ComprasappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprasappApplication.class, args);
		/*
		MercadoPagoConfig.setAccessToken("${payments.mercado-pago.application.accessToken}");

		PaymentMethodClient client = new PaymentMethodClient();
		client.list();
		 */
	}

}
