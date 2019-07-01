package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jt.easymall.mapper")
public class StarterEM {
	public static void main(String[] args) {
		//分支实战，我的分支hot_fix
		SpringApplication.run(StarterEM.class, args);
	}
}
