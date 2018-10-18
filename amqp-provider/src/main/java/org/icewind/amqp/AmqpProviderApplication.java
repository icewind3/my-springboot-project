package org.icewind.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@SpringBootApplication
//@ImportResource(locations={"classpath:rabbitmq-provider.xml"} )
public class AmqpProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqpProviderApplication.class, args);
    }
}
