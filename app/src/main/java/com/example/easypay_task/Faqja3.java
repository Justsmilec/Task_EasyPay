package com.example.easypay_task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import app.com.sample.R;

public class Faqja3 extends Fragment {

    public static ArrayList<String[]> list = new ArrayList<>();
    LinearLayout myLayout = null;
    public int count = 0;
    Button reset_button;
    ProgressDialog pd;
    //public ArrayList<String[]> List = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v_View = inflater.inflate(R.layout.fragment_faqja3, container, false);
        reset_button = (Button) v_View.findViewById(R.id.btnReset);

        LinearLayout.LayoutParams myparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        myLayout = (LinearLayout) v_View.findViewById(R.id.mylayout);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLayout.removeAllViews();
                new JsonTask().execute("https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole");

                for(int i = 0;i<list.size();i++){
                    Button button = new Button(getContext());
                    button.setText("  NAME:  " + list.get(i)[0]);
                    button.setId(i);
                    myLayout.addView(button,myparams);
                    Log.d("asfsdfaaaaaaaaaaaaaaaa", "onClick: PO "+ list.size() );

                }
            }
        });



        return v_View;

    }



    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(getContext());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            //txtJson.setText(result);
            try {
                list.clear();
                //JSONObject obj = new JSONObject(result);
                JSONArray gd = new JSONArray(result);
                for(int i = 0;i<gd.length();i++)
                {
                    String[] myString = new String[3];
                    myString[0] = gd.getJSONObject(i).getString("first");
                    myString[1] = gd.getJSONObject(i).getString("last");
                    myString[2] = gd.getJSONObject(i).getString("email");
                    list.add(myString);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
