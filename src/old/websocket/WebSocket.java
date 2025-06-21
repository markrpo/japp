package com.example.websocket;

import com.example.iwebsocket.IWebSocket;
import com.example.iwebsocket.IObserver;
import java.util.ArrayList;


public class WebSocket implements IWebSocket {

    @Override
	public boolean init() {
		// Initialization logic for WebSocket
		System.out.println("WebSocket initialized.");
		return true;
    }

    @Override
	public void start() {
		// Logic to start the WebSocket connection
		System.out.println("WebSocket started.");
		while (true) {
			notifyObservers("123", "Hello from WebSocket!");
			try {
				Thread.sleep(5000); // Simulate periodic message sending
			} catch (InterruptedException e) {
				System.err.println("WebSocket interrupted: " + e.getMessage());
				break;
			}
		}
    }

    @Override
    public void sendMessage(String id, String message) {
		// Logic to send a message over the WebSocket
		System.out.println("Sending message: " + message);
    }

    @Override
    public void close() {
		// Logic to close the WebSocket connection
		System.out.println("WebSocket connection closed.");
    }

    @Override
	public void subscribe(IObserver observer) {
		System.out.println("Observer subscribed.");
		observers.add(observer);
    }

    private ArrayList<IObserver> observers = new ArrayList<>();

	private void notifyObservers(String id, String message) {
		for (IObserver observer : observers) {
			observer.notify(id, message);
		}
	}
}
