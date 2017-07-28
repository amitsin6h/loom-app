package com.genesisdigisec.loom;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmadrosid.library.FloatingLabelEditText;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fr.ganfra.materialspinner.MaterialSpinner;



/**
 * A simple {@link Fragment} subclass.
 */
public class AddLoomFragment extends Fragment  {

    //URL to our login.php file
    String ADD_ENTRY_URL = "http://192.168.43.252/loom/app/app-add-input.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String EMAIL = "email";
    public static final String DATE = "date";
    public static final String SHIFT = "shift";
    public static final String LOOM_NO = "loom_no";
    public static final String QUALITY = "quality";
    public static final String EMP_CODE = "emp_code";
    public static final String EMP_NAME = "emp_name";
    public static final String START_READING = "start_reading";
    public static final String END_READING = "end_reading";
    //public static final String PRODUCTION = "production";
    public static final String TYPE = "type";
    public static final String REMARKS = "remarks";




    FloatingLabelEditText ftDate, ftLoomNo, ftQuality,ftempCode, ftempName, ftStart, ftEnd,ftRemarks;
    MaterialSpinner spShift,spMess;
    Button btAddentry;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_loom, container, false);
        ftDate = (FloatingLabelEditText) rootView.findViewById(R.id.ftDate);
        ftLoomNo = (FloatingLabelEditText) rootView.findViewById(R.id.ftLoomNo);
        ftQuality = (FloatingLabelEditText) rootView.findViewById(R.id.ftQuality);
        ftempCode = (FloatingLabelEditText) rootView.findViewById(R.id.ftempCode);
        ftempName = (FloatingLabelEditText) rootView.findViewById(R.id.ftempName);
        ftStart = (FloatingLabelEditText) rootView.findViewById(R.id.ftStart);
        ftEnd = (FloatingLabelEditText) rootView.findViewById(R.id.ftEnd);
        ftRemarks = (FloatingLabelEditText) rootView.findViewById(R.id.ftRemarks);

//        Calendar calendar = Calendar.getInstance();
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
       // ftDate.setText(currentDateTimeString);

        Date date = new Date();
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat timeOnly = new SimpleDateFormat("hh:mm:ss");

        String strDate = dateOnly.format(date);
        ftDate.setText(strDate);
        ftDate.setKeyListener(null);



        String[] ITEMS = {"Day", "Night"};
        ArrayAdapter<String> shiftAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        shiftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spShift = (MaterialSpinner) rootView.findViewById(R.id.spShift);
        spShift.setAdapter(shiftAdapter);

        final String[] Mess = {"10*10","11*11","12*12","Other"};
        final ArrayAdapter<String> messAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Mess);
        messAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMess= (MaterialSpinner) rootView.findViewById(R.id.spMess);
        spMess.setAdapter(messAdapter);




        ftLoomNo = (FloatingLabelEditText) rootView.findViewById(R.id.ftLoomNo);




        //storing all the value in variable //

        btAddentry = (Button) rootView.findViewById(R.id.btAddentry);
        btAddentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             addLoom();
            }
        });



        return rootView;
    }



/*    private void insertUser() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(LoginDetail.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_email = sharedPref.getString(LoginDetail.EMAIL_SHARED_PREF, "");


        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ADD_ENTRY_URL).build();

        AddLoomAPI api = adapter.create(AddLoomAPI.class);

        api.insertUser(
                user_email.toString(),
                ftDate.getText().toString(),

            new Callback<Response>(){

            }

        );

    }*/











/*    private void addLoomEntry(){
        SharedPreferences sharedPref = getActivity().getSharedPreferences(LoginDetail.SHARED_PREF_NAME, Context.MODE_PRIVATE);
         String user_email = sharedPref.getString(LoginDetail.EMAIL_SHARED_PREF,"");

        //final String email = user_email;
        //final String date = ftDate.getText().toString();
        //final String shift = spShift.getSelectedItem().toString();
        //final String loom_no = ftLoomNo.getText().toString();
        //final String quality = ftQuality.getText().toString();
        //final String emp_code = ftempCode.getText().toString();
        //final String emp_name = ftempName.getText().toString();
        //final String start_reading = ftStart.getText().toString();
        //final String end_reading = ftEnd.getText().toString();
        //final String type = spMess.getSelectedItem().toString();
        //final String remarks = ftRemarks.getText().toString();

        //Log.e("Values: ",email+date+loom_no+quality+emp_code+emp_name+start_reading+end_reading+type+remarks);

        //here we are handling the http request to insert user to mysql db
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ADD_ENTRY_URL).build();

        AddLoomAPI api = adapter.create(AddLoomAPI.class);

        api.insertUser(
                //passing the value to api by senerity
                user_email,
                ftDate.getText().toString(),
                spShift.getSelectedItem().toString(),
                ftLoomNo.getText().toString(),
                ftQuality.getText().toString(),
                ftempCode.getText().toString(),
                ftempCode.getText().toString(),
                ftempName.getText().toString(),
                ftStart.getText().toString(),
                ftEnd.getText().toString(),
                spMess.getSelectedItem().toString(),
                ftRemarks.getText().toString();

                new Callback<retrofit.client.Response>(){
                    public void success(retrofit.client.Response result, retrofit.client.Response response){
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        String output = "";

                        try {
                            //initilizing buffer reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //raeding the output in the string
                            output = reader.readLine();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        //Display the output as a toast
                        Toast.makeText(getActivity(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error){
                        //if any error occured
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );

    }*/


    private void addLoom(){

        SharedPreferences sharedPref = getActivity().getSharedPreferences(LoginDetail.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String user_email = sharedPref.getString(LoginDetail.EMAIL_SHARED_PREF,"");

        final String email = user_email;
        final String date = ftDate.getText().toString();
        final String shift = spShift.getSelectedItem().toString();
        final String loom_no = ftLoomNo.getText().toString();
        final String quality = ftQuality.getText().toString();
        final String emp_code = ftempCode.getText().toString();
        final String emp_name = ftempName.getText().toString();
        final String start_reading = ftStart.getText().toString();
        final String end_reading = ftEnd.getText().toString();
        final String type = spMess.getSelectedItem().toString();
        final String remarks = ftRemarks.getText().toString();

        Log.e("Values: ",email+date+loom_no+quality+emp_code+emp_name+start_reading+end_reading+type+remarks);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_ENTRY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(EMAIL,email);
                params.put(DATE,date);
                params.put(SHIFT,shift );
                params.put(LOOM_NO,loom_no);
                params.put(QUALITY,quality);
                params.put(EMP_CODE,emp_code);
                params.put(EMP_NAME,emp_name);
                params.put(START_READING,start_reading);
                params.put(END_READING,end_reading);
                params.put(TYPE,type);
                params.put(REMARKS,remarks);
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


        /*stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20 * 1000,0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);



    }



}



