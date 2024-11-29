package com.Code.httpServer.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);


    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket (this.port);
    }
    @Override
    public void run() {

        try {
            while (serverSocket.isBound()&& !serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();

                LOGGER.info("Accepted connection from " + socket.getRemoteSocketAddress());

                httpConnectionWorkerThread workerThread = new httpConnectionWorkerThread(socket);
                workerThread.start();

            }
            //serverSocket.close();// TODO Handle close
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problem setting the socket");
        }
        finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
