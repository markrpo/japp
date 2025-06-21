package com.example;
import com.example.iwebsocket.IObserver;
import com.example.iwebsocket.IWebSocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler implements IWebSocket {

    private final List<IObserver> observers = new ArrayList<>();
	private final List<SessionWrapper> sessions = new ArrayList<>();

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String id, String message) {
        for (IObserver obs : observers) {
            obs.notify(id, message);
        }
    }

    @Override
    public void sendMessage(String id, String message) {
		for (SessionWrapper sessionWrapper : sessions) {
	    	if (sessionWrapper.getId().equals(id)) {
			try {
		    	sessionWrapper.getSession().sendMessage(new TextMessage(message));
		    	System.out.println("Mensaje enviado a " + id + ": " + message);
			} catch (Exception e) {
		    	System.err.println("Error al enviar mensaje a " + id + ": " + e.getMessage());
			}
			return;
	    	}
		}
		System.err.println("No se encontró la sesión con ID: " + id);
    }

    @Override
    public void close() {
		for (SessionWrapper sessionWrapper : sessions) {
		    try {
				sessionWrapper.getSession().close();
	    	} catch (Exception e) {
				System.err.println("Error al cerrar sesión: " + e.getMessage());
	    	}
		}
		sessions.clear();
		observers.clear();
		System.out.println("Conexiones y observadores cerrados.");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
		String id = session.getId();
		sessions.add(new SessionWrapper(id, session));
		System.out.println("Nueva conexión establecida: " + id);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
	    String id = session.getId();
        notifyObservers(id, message.getPayload());
    }
}

class SessionWrapper {
    private final WebSocketSession session;
    private final String id;

    public SessionWrapper(String id, WebSocketSession session) {
	if (id == null || session == null) {
	    throw new IllegalArgumentException("ID and session cannot be null");
	}
		this.id = id;
		this.session = session;
    }

    public WebSocketSession getSession() {
		return session;
    }

    public String getId() {
		return id;
    }
}
