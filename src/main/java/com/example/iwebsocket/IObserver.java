package com.example.iwebsocket;

public interface IObserver {
    void notify(String id, String message);
	void init();
}
