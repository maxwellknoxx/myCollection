package com.knoxx.mycollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MyCollectionApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MyCollectionApplication.class, args);
		myThing(1);
		myThing(true);
		myThing("Oi, eu sou Goku");
		myThing(7.3);
	}

	public static <T> void myThing(T thing) {
		System.out.println(thing);
	}

}
