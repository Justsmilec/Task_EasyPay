package com.example.easypay_task;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Write and Read from file (All functions linked to a file)
 */
public class FileHandler {

    public static void WritetoFile(String filename,String dataname, String data, Context context) {
        File myDir = context.getFilesDir();


        try {
            File secondFile = new File(myDir, filename);
                secondFile.createNewFile();
                FileWriter fos = new FileWriter(secondFile,true);
                Log.d("-----------", "WritetoFile:  FILE PER SHKRIM EKZISTON");
                fos.write(dataname);
                fos.write("-");
                fos.write(Base.encode(data.getBytes()));
                fos.write("--");
                Log.d("-----------", "WritetoFile:  FILE PER SHKRIM EKZISTON");

                fos.flush();
                fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String[]> ReadonFile(String filename, Context context) {
        File myDir = context.getFilesDir();
        String decodeString;

        try {
            File secondInputFile = new File(myDir, filename);
            InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
            BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            r.close();
            secondInputStream.close();
            Log.d("File", "File contents: " + total);
            decodeString = new String(Base.decode(total.toString()));
            Log.d("File", "File contents: DECODED " + decodeString);

            return returnList(total.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static ArrayList<String[]> returnList(String totalString)
    {

        //Ndaje per cdo person Emer--Password$
        //Ndahet nje objekt kur kemi $
        ArrayList<String[]> myList = new ArrayList<>();
        String[] arrof = totalString.split("--");
        for(int i = 0 ; i< arrof.length; i++) {
            Log.d("ELEMENT: ", "returnList: " + arrof[i]);
            myList.add(returnCredentials(arrof[i]));
        }

        return  myList;
    }

    /**
     * Behet ndarja Emer   dhe Password
     * @param listElement  -- nje element liste  Ndaje per cdo person Emer--Password
     * @return
     */
    static private String[] returnCredentials(String listElement)
    {
        String[] splitstr;
        Log.d("OUTPUT STRING", "returnCredentials: "+listElement);

        splitstr = listElement.split("-");
        Log.d("OUTPUT", "returnCredentials: "+ splitstr[0] + " --- " + splitstr[1]);
        String[] str = new String[2];
        str[0] = splitstr[0];
        str[1] = new String(Base.decode(splitstr[1]));
        return  str;
    }


}


