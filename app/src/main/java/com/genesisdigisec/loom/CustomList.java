package com.genesisdigisec.loom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by amitsin6h on 6/23/17.
 */

public class CustomList extends ArrayAdapter<String> {

    private String[] entry_date;
    private String[] shift;
    private String[] loom_no;
    private String[] emp_name;
    private String[] start_reading;
    private Activity context;


    public CustomList(Activity context, String[] entry_date, String[] shift, String[] loom_no, String[] emp_name, String[] start_reading){
        super(context, R.layout.live_loom_layout, emp_name);
        this.context = context;
        this.emp_name = emp_name;
        this.entry_date = entry_date;
        this.shift = shift;
        this.loom_no = loom_no;
        this.start_reading = start_reading;

    };


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View customview = inflater.inflate(R.layout.live_loom_layout,null,true);
        TextView tvEntryDate = (TextView) customview.findViewById(R.id.tvEntryDate);
        TextView tvShift = (TextView) customview.findViewById(R.id.tvShift);
        TextView tvLoomNo = (TextView) customview.findViewById(R.id.tvLoomNo);
        TextView tvEmpNo = (TextView) customview.findViewById(R.id.tvEmpName);
        TextView tvStartReading = (TextView) customview.findViewById(R.id.tvStartReading);


        tvEntryDate.setText(entry_date[position]);
        tvShift.setText(shift[position]);
        tvLoomNo.setText(loom_no[position]);
        tvEmpNo.setText(emp_name[position]);
        tvStartReading.setText(start_reading[position]);

        return customview;
    }


}
