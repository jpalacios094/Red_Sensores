package com.example.vikin.red_sensores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessages extends Thread{
    Socket socket;
    MainActivity activity;
    BufferedReader b;
    public ClientMessages(Socket socket,MainActivity activity){
        this.socket = socket;
        this.activity = activity;
        try {
            b = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        String mensaje="";
        while(true) {
            try {
                //Recibirá todos aquellos mensajes que envíe el cliente al servidor
                mensaje=b.readLine();
                if(mensaje!=null) {
                    //activity.info.setText(mensaje);
                    activity.getMyData(mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
