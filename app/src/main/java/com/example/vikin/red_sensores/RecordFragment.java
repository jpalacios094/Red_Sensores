package com.example.vikin.red_sensores;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {
    static final int READ_BLOCK_SIZE = 100;
    TextView msgRecord;
    Button button;
    MainActivity activity  =  new MainActivity();

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        activity = (MainActivity)getActivity();
        msgRecord = (TextView) view.findViewById(R.id.msgRecord);
        button = (Button) view.findViewById(R.id.selectbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity, button);
                popupMenu.getMenuInflater().inflate(R.menu.menu_files, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String sensor = item.getTitle().toString();
                        switch (sensor){
                            case "Sensor Temperatura":
                                try{
                                    msgRecord.setText("No existe historial almacenado");
                                    FileInputStream fis = activity.openFileInput("RegistroTemp.txt");
                                    InputStreamReader isr = new InputStreamReader(fis);
                                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                                    String s ="";
                                    int charRead;
                                    while ((charRead = isr.read(inputBuffer))>0){
                                        String readString = String.copyValueOf(inputBuffer, 0,charRead);
                                        s+= readString;
                                        inputBuffer = new char[READ_BLOCK_SIZE];

                                    }
                                    msgRecord.setText(s);
                                    Toast.makeText(activity.getBaseContext(), "cargado",Toast.LENGTH_SHORT);
                                    isr.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Sensor Gas":
                                msgRecord.setText("No existe historial almacenado");
                                try{
                                    FileInputStream fis = activity.openFileInput("RegistroGas.txt");
                                    InputStreamReader isr = new InputStreamReader(fis);
                                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                                    String s ="";
                                    int charRead;
                                    while ((charRead = isr.read(inputBuffer))>0){
                                        String readString = String.copyValueOf(inputBuffer, 0,charRead);
                                        s+= readString;
                                        inputBuffer = new char[READ_BLOCK_SIZE];

                                    }
                                    msgRecord.setText(s);
                                    Toast.makeText(activity.getBaseContext(), "cargado",Toast.LENGTH_SHORT);
                                    isr.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case "Borrar Registros":
                                activity.deleteFile("RegistroGas.txt");
                                activity.deleteFile("RegistroTemp.txt");
                                Toast.makeText(activity.getBaseContext(), "Registros eliminados",Toast.LENGTH_SHORT);
                             default:
                                 break;

                        }

                        Toast.makeText(activity, ""+item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
       /* if(getUserVisibleHint()){ // fragment is visible
            loadData();
        }*/

        return view;
    }
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
            loadData();
        }
    }

    public void loadData(){
        // data for fragment when it visible here
        try{
            FileInputStream fis = activity.openFileInput("Registro.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s ="";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer, 0,charRead);
                s+= readString;
                inputBuffer = new char[READ_BLOCK_SIZE];

            }
            msgRecord.setText(s);
            Toast.makeText(activity.getBaseContext(), "cargado",Toast.LENGTH_SHORT);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
