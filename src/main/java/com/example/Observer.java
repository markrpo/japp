package com.example;
import com.example.iwebsocket.IWebSocket;
import com.example.iwebsocket.IObserver;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Observer implements IObserver {

	public Observer(IWebSocket webSocket) {
		this.m_webSocket = webSocket;
	}

	@Override
	public void init() {
		m_webSocket.registerObserver(this);
		System.out.println("Observer initialized.");
	}

	public void notify(String id, String message) {
		System.out.println("Notification received: " + message + " for ID: " + id);
	}

	private IWebSocket m_webSocket;
}
