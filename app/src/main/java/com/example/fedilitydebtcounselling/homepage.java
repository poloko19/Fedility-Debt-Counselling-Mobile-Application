package com.example.fedilitydebtcounselling;

import androidx.appcompat.app.AppCompatActivity;
//code to call/open a new page/activity that has a button
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class homepage extends AppCompatActivity {
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        button = (Button) findViewById(R.id.btncon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(homepage.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
    }
}