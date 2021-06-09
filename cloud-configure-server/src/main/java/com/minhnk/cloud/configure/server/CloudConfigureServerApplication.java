package com.minhnk.cloud.configure.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigureServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigureServerApplication.class, args);
	}

}
