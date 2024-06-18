package com.fullstack;

import com.fullstack.model.Like;
import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}