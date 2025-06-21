package com.example;

import com.example.Observer;
import com.example.MyWebSocketHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class InitService {

	InitService(MyWebSocketHandler webSocketHandler, Observer observer) {
		this.m_WebSocketHandler = webSocketHandler;
		this.m_Observer = observer;
	}

    public static void main(String[] args) {
		SpringApplication.run(InitService.class, args);
    }

    private MyWebSocketHandler m_WebSocketHandler;
    private Observer m_Observer;

    @PostConstruct
    public void init() {
		m_Observer.init();
    }

}
