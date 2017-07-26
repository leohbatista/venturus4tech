package br.org.venturus.venturus4tech;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {

    private static SocketManager mInstance;
    private Socket mSocket = null;

    public static SocketManager getInstance() {
        if (mInstance == null) {
            mInstance = new SocketManager();
        }
        return mInstance;
    }

    private SocketManager() {
        try {
            mSocket = IO.socket("http://172.20.6.37:3000");
        } catch (URISyntaxException e) {
            mSocket = null;
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
