package com.example.iwebsocket;

public interface IWebSocket {
	void start();
	boolean init();
	void subscribe(IObserver observer);
	void sendMessage(String id, String message);
	void close();
}
