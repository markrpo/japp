package com.example.iwebsocket;

public interface IWebSocket {
	void registerObserver(IObserver observer);
	void sendMessage(String id, String message);
	void close();
}
