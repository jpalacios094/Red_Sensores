package com.example.vikin.red_sensores;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class TempFragment extends Fragment {
   private  TextView msg, infoip, recibido;
   private FloatingActionButton button;
   Server server;
     //private StringBuilder recDataString = new StringBuilder();
    String msgRecibido ;
    public MainActivity activity = new MainActivity();
    // create boolean for fetching data
    //private boolean isViewShown = false;
    private Boolean isStarted = false;
    private Boolean isVisible = false;

    public TempFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        msg = (TextView) view.findViewById(R.id.msg);
        infoip = (TextView) view.findViewById(R.id.infoip);
        button = (FloatingActionButton) view.findViewById(R.id.botoncito);
        activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.setMyData();
        infoip.setText(myDataFromActivity);  //mostar dirección IP y el puerto de escucha del servidor
       // server = new Server(this);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = (MainActivity) getActivity();
                msgRecibido = activity.mensajeSensorTemp();
                msg.setText(msgRecibido);
            }
        });*/
        //UNPACK OUR DATA FROM OUR BUNDLE
        //msgRecibido = this.getArguments().getString("NAME_KEY").toString();
        //if(this.getArguments()!= null) {
            String name = this.getArguments().getString("NAME_KEY").toString();
            //int year = this.getArguments().getInt("YEAR_KEY");
          //  if (name != null) {
              //  msg.setText("NAME : " + name);

            //}
        //}
        //yearFragTxt.setText("YEAR : " + String.valueOf(year));

        // if(getUserVisibleHint()){ // fragment is visible
         //   loadData();
        //}
        // msgRecibido = activity.mensajesRecibidos();
       // msg.setText(msgRecibido);
        return view;
    }
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
            loadData();
        }
    }

    public void loadData(){
        // data for fragment when it visible here
        activity = (MainActivity) getActivity();
        msgRecibido = activity.mensajeSensorTemp();
        msg.setText(msgRecibido);
    }

    public void escribe(String mensaje){
        msg.setText(mensaje);
    }*/
   /*@Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        if (isVisible && isStarted){
            viewDidAppear();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {
            viewDidAppear();
        }
    }

    public void viewDidAppear() {
        // your logic
        MainActivity activity = (MainActivity) getActivity();
        msgRecibido = activity.mensajesRecibidos();
        msg.setText(msgRecibido);
    }*/



}