package com.example.vikin.red_sensores;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //TempFragment fragment;
    String mensaje = "";
    String respuestas = "";
    String sensor ="";
    Server server;
    String enviaTemp="";
    String enviaGas="";
    public int someIntValue =1;
    private String myString = "hola";
    private ViewPager mViewPager;
    //private StringBuilder Cabecera = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewpager = (ViewPager)findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        TabItem tabTemp = findViewById(R.id.tabTemp);
        TabItem tabGas = findViewById(R.id.tabGas);
        TabItem tabRecord = findViewById(R.id.tabRecord);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewpager);
        //server = new Server(this);
       // TempFragment tempFragment = (TempFragment)getSupportFragmentManager().findFragmentById(R.id.msg);

    }

    //Obtiene la dirección IP y el puerto
    public String setMyData(){
        server = new Server(this);
        mensaje = server.getIpAddress()+":"+server.getPort();
        return mensaje;
    }
    //Recibe el mensaje por parte del cliente
    public void getMyData(String message){     //String
        int opc;
        respuestas = message;
        if(respuestas!=""){
            opc = Integer.parseInt(respuestas.substring(0,2));
            switch (opc){
                case 01:
                    enviaTemp = respuestas.substring(2,respuestas.length());
                   // TempFragment tempFragment = (TempFragment)getSupportFragmentManager().findFragmentById(R.id.msg);
                    //PACK DATA IN A BUNDLE
                    Bundle bundle = new Bundle();
                    bundle.putString("NAME_KEY", enviaTemp);
                   // bundle.putInt("YEAR_KEY", Integer.valueOf(launchYearSpinner.getSelectedItem().toString()));

                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    TempFragment myFragment = new TempFragment();
                    myFragment.setArguments(bundle);

                    //nameTxt.setText("");
                    //launchYearSpinner.setSelection(0);

                    //THEN NOW SHOW OUR FRAGMENT
                    //
                    getSupportFragmentManager().beginTransaction().add(R.id.viewPager,myFragment).commit();
                    //getSupportFragmentManager().beginTransaction().

                    String tempRecord = respuestas.substring(2,respuestas.length());

                    try{
                        FileOutputStream fos = openFileOutput("RegistroTemp.txt",MODE_APPEND);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        osw.write(tempRecord + Calendar.getInstance().getTime() + "\n");
                        osw.flush();
                        osw.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    mensajeSensorTemp();
                    break;
                    //return envia;
                case 02:
                    enviaGas += respuestas.substring(2,respuestas.length()) + "\n";
                    String gasRecord = respuestas.substring(2,respuestas.length()) + "\n";
                    try{
                        FileOutputStream fos = openFileOutput("RegistroGas.txt",MODE_APPEND);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        osw.write(gasRecord + Calendar.getInstance().getTime() +"\n");
                        osw.flush();
                        osw.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    mensajeSensorGas();
                    break;
                    //return envia;
                default:
                    break;
            }
            // return response;
        }
       // TempFragment tempFragment = new TempFragment();
       //tempFragment.loadData();

    }
    //Envia el mensaje al fragmento
   /* public void mensajesRecibidos(){
        int opc;

        String response = respuestas;
        if(response!=""){
             opc = Integer.parseInt(response.substring(0,2));
            switch (opc){
                case 01:
                    envia = response.substring(3,9);
                    mensajeSensorTemp();
                    //return envia;
                case 02:
                    envia = response.substring(3,9);
                    mensajeSensorGas();
                    //return envia;
                default:
                    break;
            }
           // return response;
        }
        //TempFragment tempFragment = new TempFragment();
        //tempFragment.viewDidAppear();
        //return response;
    }*/
    public String mensajeSensorGas(){
        /*try{
            FileOutputStream fos = openFileOutput("RegistroGas.txt",MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(enviaGas + Calendar.getInstance().getTime());
            osw.flush();
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/
        return enviaGas;
    }
    public String mensajeSensorTemp(){
       /* try{
            FileOutputStream fos = openFileOutput("RegistroTemp.txt",MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(enviaTemp);
            osw.flush();
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/

        return enviaTemp;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        server.onDestroy();
    }
}
