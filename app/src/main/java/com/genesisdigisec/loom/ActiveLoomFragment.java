package com.genesisdigisec.loom;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveLoomFragment extends Fragment {

    ListView lv;
    public static final String adderss = "http://192.168.43.252/loom/app/app-live-loom.php";
    InputStream is = null;
    String line = null;
    String result = null;


    String[] entry_date;
    String[] shift ;
    String[] loom_no ;
    String[] emp_name ;
    String[] start_reading;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_active_loom, container, false);

        lv = (ListView) rootView.findViewById(R.id.listview);


        //allow the network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        //get data
        getData();


        CustomList customList = new CustomList(getActivity(), entry_date,shift,loom_no, emp_name, start_reading);
        lv.setAdapter(customList);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Toast.makeText(getActivity(),"You Clicked "+emp_name[i], Toast.LENGTH_SHORT).show();
                Log.e("JSON",emp_name[i]);*/
                Intent intent = new Intent(getActivity(),ActiveLoomDetail.class);

                intent.putExtra("emp_name",emp_name[i]);
                intent.putExtra("entry_date", entry_date[i]);
                intent.putExtra("shift",shift[i]);
                intent.putExtra("loom_no",loom_no[i]);
                intent.putExtra("start_reading", start_reading[i]);

                startActivity(intent);

            }
        });






        return rootView;
    }




    private void getData(){

        //sending get request using http connection
        try {
            URL url = new URL(adderss);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            is = new BufferedInputStream(con.getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }


        //reading content
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine())!= null){
                sb.append(line+"\n");
            }

            is.close();
            result = sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }


        //parsing json
        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;


            entry_date = new String[ja.length()];
            shift = new String[ja.length()];
            loom_no = new String[ja.length()];
            emp_name = new String[ja.length()];
            start_reading = new String[ja.length()];

            for (int i=0; i<ja.length();i++){

                jo = ja.getJSONObject(i);

                entry_date[i] = jo.getString("entry_date");
                shift[i] = jo.getString("shift");
                loom_no[i] = jo.getString("loom_no");
                emp_name[i] = jo.getString("emp_name");
                start_reading[i] = jo.getString("start_reading");





                Log.e("Shift",entry_date[i]);
                //jo.getString("start_reading")
            }


        }catch (JSONException e){
            e.printStackTrace();
        }





    }









}
