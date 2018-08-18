package com.example.vikin.red_sensores;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;
    //codigo nuevo marcado con *
    private Context mContext;
    /*public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        //mContext = context;//*
        this.numOfTabs = numOfTabs;
    }*/
    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;//*
        //this.numOfTabs = numOfTabs;
    }//Codigo nuevo
//Esto determina el fragmento para cada pestaña
    @Override
    public Fragment getItem(int position) {
        /*switch (numOfTabs) {
            case 0:
                return new TempFragment();
            case 1:
                return new GasFragment();
            case 2:
                return new RecordFragment();
            default:
                return null;
        }*/
        if(position==0){
            return new TempFragment();
        }else if(position==1){
            return new GasFragment();
        }else{
            return new RecordFragment();
        }
        //return null;
    }
    //Esto determina el numero de pestañas
    @Override
    public int getCount() {
        //return numOfTabs;
        return 3;
    }
    //Determina el titulo para cada pestaña
   @Override
    public CharSequence getPageTitle(int position){
        //Generar el nombre basado en la posicion del objeto
        switch (position){
            case 0:
                return mContext.getString(R.string.TempInfo);
            case 1:
                return mContext.getString(R.string.GasInfo);
            case 2:
                return mContext.getString(R.string.RecordInfo);
            default:
                return null;
        }
    }
}
