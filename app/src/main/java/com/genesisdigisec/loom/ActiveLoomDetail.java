package com.genesisdigisec.loom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActiveLoomDetail extends AppCompatActivity {

    TextView dEmpName,dEntryDate,dShift,dLoomNo,dStartReading;
    Button btSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_loom_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        String emp_name = intent.getStringExtra("emp_name");
        String entry_date = intent.getStringExtra("entry_date");
        String shift = intent.getStringExtra("shift");
        String loom_no = intent.getStringExtra("loom_no");
        String start_reading = intent.getStringExtra("start_reading");


        dEmpName = (TextView) findViewById(R.id.dEmpName);
        dEntryDate = (TextView) findViewById(R.id.dEntryDate);
        dShift = (TextView) findViewById(R.id.dShift);
        dLoomNo = (TextView) findViewById(R.id.dLoomNo);
        dStartReading = (TextView) findViewById(R.id.dStartReading);


        dEmpName.setText(emp_name);
        dEntryDate.setText(entry_date);
        dShift.setText(shift);
        dLoomNo.setText(loom_no);
        dStartReading.setText(start_reading);



        btSubmit = (Button) findViewById(R.id.btSubmit);




    }

}
