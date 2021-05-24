package com.example.lab9_18056521_hahuyhoang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Act1 extends AppCompatActivity {

    private Button btnSignIn;
    private Button btnRegister;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        btnSignIn = findViewById(R.id.btnSignIn1);
        btnRegister = findViewById(R.id.btnRegis1);
        Context ctx = this;

        intent =  getIntent();
        bundle = intent.getBundleExtra("data");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ctx, act2.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ctx, act3.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }
}