package com.example.easypay_task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import app.com.sample.R;

public class Faqja1 extends Fragment {

    EditText nameText,passText;
    Button btn;
    //public ArrayList<String[]> List = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v_View = inflater.inflate(R.layout.fragment_faqja1, container, false);
        nameText = (EditText) v_View.findViewById(R.id.editname);
        passText = (EditText) v_View.findViewById(R.id.editpassword);
        btn = (Button) v_View.findViewById(R.id.button);
        //Submit button onClick Lister

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = nameText.getText().toString();
                String passwordText = passText.getText().toString();
                //Write to file
                FileHandler.WritetoFile("firstfile.txt",inputText,passwordText,getContext());
                nameText.setText("");
                passText.setText("");
                if(Faqja2.List != null)
                    Faqja2.List.clear();
                Faqja2.displayList();


            }
        });

        return v_View;

    }



}
