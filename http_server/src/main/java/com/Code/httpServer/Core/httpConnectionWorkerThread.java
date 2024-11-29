package com.Code.httpServer.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class httpConnectionWorkerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private Socket socket;
    public httpConnectionWorkerThread(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
             inputStream = socket.getInputStream();
             outputStream = socket.getOutputStream();

            String html = "<html><head><title>Felipe's http server</title> </head><body> <h1>Testing again</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10
            String response =
                    "HTTP/1.1 200 OK" + CRLF + // Status Line : HTTTP VERSION RESPONSE CODE RESPONSE MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection Processing Finished");
        } catch (IOException e) {
            LOGGER.error("Connection Processing Error", e);


        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }

        }
    }
}
