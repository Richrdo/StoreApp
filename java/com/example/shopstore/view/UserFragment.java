package com.example.shopstore.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.shopstore.R;

public class UserFragment extends Fragment {
    private Activity mActivity;
    private Button btn_logoff;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivity= (Activity) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        btn_logoff=view.findViewById(R.id.btn_logoff);
        btn_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoff();
            }
        });

        return view;
    }

    public void logoff(){
        SharedPreferences sp=mActivity.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("PHONE_NUMBER",null);
        editor.putString("PASSWORD",null);
        editor.apply();
        Intent intent=new Intent(mActivity,LoginPane.class);
        startActivity(intent);
    }
}
