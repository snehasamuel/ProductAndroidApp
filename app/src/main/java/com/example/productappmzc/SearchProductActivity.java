package com.example.productappmzc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchProductActivity extends AppCompatActivity {

    DatabaseHelper helper;

    EditText ed1,ed2,ed3;
    AppCompatButton b1,b2;

    String getProCode,getProName,getPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        helper=new DatabaseHelper(this);
        helper.getWritableDatabase();

        ed1=(EditText) findViewById(R.id.code);
        ed2=(EditText) findViewById(R.id.name);
        ed3=(EditText) findViewById(R.id.price);
        b1=(AppCompatButton) findViewById(R.id.search);
        b2=(AppCompatButton) findViewById(R.id.menu);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProCode=ed1.getText().toString();

                Cursor c=helper.searchData(getProCode);
                     if(c.getCount()==0)
                     {
                         ed2.setText("");
                         ed3.setText("");
                         Toast.makeText(getApplicationContext(), "Invalid Code", Toast.LENGTH_SHORT).show();
                     }
                     else
                     {
                        while(c.moveToNext())
                        {
                            getProName=c.getString(2);
                            getPrice=c.getString(3);
                        }
                        ed2.setText(getProName);
                        ed3.setText(getPrice);
                     }



            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}