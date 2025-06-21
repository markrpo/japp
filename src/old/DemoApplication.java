package com.example;

import com.example.*;
import com.example.websocket.*;

public class DemoApplication {
    public static void main(String[] args) {
		WebSocket webSocket = new WebSocket();
		webSocket.init();
		Observer observer = new Observer(webSocket);
		observer.init();
		webSocket.start();
    }
}


