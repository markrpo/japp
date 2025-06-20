package com.example;

import com.example.iwebsocket.IObserver;
import com.example.iwebsocket.IWebSocket;

public class Observer implements IObserver {
	public Observer(IWebSocket webSocket) {
		this.m_webSocket = webSocket;
	}

	@Override
	public void init() {
		m_webSocket.subscribe(this);
		System.out.println("Observer initialized.");
	}

	public void notify(String id, String message) {
		System.out.println("Notification received: " + message + " for ID: " + id);
	}

	private IWebSocket m_webSocket;
}
