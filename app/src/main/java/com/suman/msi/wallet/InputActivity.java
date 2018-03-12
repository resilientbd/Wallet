package com.suman.msi.wallet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{

    TextView show_Date;
    EditText inpMoney, inpNote;
    Calendar currentDate;
    int day, month, year;
    Button pickDate;
    Spinner chooseCategory;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        show_Date = (TextView) findViewById(R.id.Show_Date);
        inpMoney = (EditText) findViewById(R.id.editMoney);
        inpNote = (EditText) findViewById(R.id.editNote);
        pickDate = (Button) findViewById(R.id.DatePicker);
        chooseCategory = (Spinner) findViewById(R.id.addCategory);

        currentDate = Calendar.getInstance();

        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);
        month = month+1;
        if (day<10){
            show_Date.setText("0"+day+"/"+month+"/"+year);
        }else {
            show_Date.setText(day+"/"+month+"/"+year);
        }

        pickDate.setOnClickListener(this);

        adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCategory.setAdapter(adapter);
        chooseCategory.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        chooseCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position) +" is selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportActionBar().setTitle("New expense");
        getSupportActionBar().setSubtitle("Accountant");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addCategory){
            Intent intent=new Intent(InputActivity.this,null);
            startActivity(intent);
        }else if (v.getId() == R.id.DatePicker){
            DatePickerDialog datePickerDialog = new DatePickerDialog(InputActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    show_Date.setText(dayOfMonth+"/"+month+"/"+year);
                }
            }, year,month, day);
            datePickerDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
