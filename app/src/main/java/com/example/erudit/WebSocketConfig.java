package com.example.erudit;

import com.example.erudit.Modals.Player;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketConfig {
    private OkHttpClient client;
    private WebSocket webSocket;

    public void start() {
        client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://localhost:8080").build();

        WebSocketListener listener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                System.out.println("WebSocket подключен");
                webSocket.send("Привет от Android!");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println("Получено сообщение: " + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                System.out.println("Получено бинарное сообщение: " + bytes.hex());
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                webSocket.close(1000, null);
                System.out.println("WebSocket закрывается: " + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                System.err.println("Ошибка WebSocket: " + t.getMessage());
            }
        };

        webSocket = client.newWebSocket(request, listener);

        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        } else {
            System.out.println("WebSocket еще не подключен");
        }
    }
}
