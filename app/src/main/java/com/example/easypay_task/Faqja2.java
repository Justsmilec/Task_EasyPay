package com.example.easypay_task;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import app.com.sample.R;

public class Faqja2 extends Fragment implements View.OnClickListener {
    public static LinearLayout myLayout = null;
    public static Context faqja2Context = null;
    public static ArrayList<String[]> List = new ArrayList<>();
    public static ArrayList<Button> buttonList = new ArrayList<>();
    public  static View v_View = null;
    public static View.OnClickListener forme = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v_View = inflater.inflate(R.layout.fragment_faqja2, container, false);
        faqja2Context = getContext();
        forme = this;
        //Merr Listen

        displayList();

        return v_View;
    }

    public static void displayList(){
        List.clear();
        myLayout = (LinearLayout) v_View.findViewById(R.id.mylayout);
        myLayout.removeAllViews();
        List = FileHandler.ReadonFile("firstfile.txt",faqja2Context);
//        for(int i = 0;i<List.size();i++) {
//            System.out.println(List.size() + "  NAME:  " + List.get(i)[0] + "  PASSWORD: "+ List.get(i)[1]);
//        }

        LinearLayout.LayoutParams myparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if(List != null)
        {
            for(int i = 0;i<List.size();i++){
                Button button = new Button(faqja2Context);
                button.setText("  NAME:  " + List.get(i)[0]);
                button.setId(i);
                buttonList.add(button);
                myLayout.addView(button,myparams);
            }

            for(int i = 0;i<buttonList.size();i++){
                buttonList.get(i).setOnClickListener(forme);
            }
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        Log.d("BUTTON CLICKED", "onClick: CLICKED BUTTON: "+ id);

        for(int i = 0;i<buttonList.size();i++){
            if(id == buttonList.get(i).getId())
            {
                String allString = "  NAME:  " + List.get(i)[0] + "  PASSWORD:  " + List.get(i)[1];
                buttonList.get(i).setText(allString);
            }
        }

    }

}