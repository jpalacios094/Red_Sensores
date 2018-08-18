package com.example.vikin.red_sensores;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GasFragment extends Fragment {

    private TextView msg, infoip;
    String msgRecibido="";
    public MainActivity activity = new MainActivity();
    private Boolean isStarted = false;
    private Boolean isVisible = false;

    public GasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gas, container, false);
        msg = (TextView) view.findViewById(R.id.msgGas);
        infoip = (TextView) view.findViewById(R.id.infoipGas);
        activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.setMyData();
        infoip.setText(myDataFromActivity);  //mostar dirección IP y el puerto de escucha del servidor
        if(getUserVisibleHint()){ // fragment is visible
            loadData();
        }
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
            loadData();
        }
    }

    public void loadData(){
        // data for fragment when it visible here
        activity = (MainActivity) getActivity();
        msgRecibido = activity.mensajeSensorGas();
        msg.setText(msgRecibido);
    }

}
