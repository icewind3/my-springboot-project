package org.icewind.demo;

import org.icewind.demo.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource(locations={"classpath:consumer.xml"} )
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-oauth2-oauth2.xml"});
//		context.start();
//		DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy
//		demoService.sayHello("hello");
	}
}
