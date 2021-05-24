package com.example.lab9_18056521_hahuyhoang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class act2 extends AppCompatActivity {

    private TextView tvRegis2;
    private TextView tvEmail2;
    private TextView tvPass2;
    private Button btnSignIn2;

    private Intent intent;
    private Bundle bundle;

    private FirebaseAuth firebaseAuth;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        tvRegis2 = findViewById(R.id.tvRegis2);
        tvEmail2 = findViewById(R.id.tvEmail3);
        tvPass2 = findViewById(R.id.tvPass3);
        btnSignIn2 = findViewById(R.id.btnSignIn2);
        Context ctx = this;

        intent =  getIntent();
        bundle = intent.getBundleExtra("data");

        tvRegis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ctx, act3.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        btnSignIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvEmail2.getText().toString().trim();
                String password = tvPass2.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(act2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(act2.this, "Cant't sign in", Toast.LENGTH_LONG).show();

                                } else {
                                    userId = firebaseAuth.getCurrentUser().getUid();
                                    Intent intent = new Intent(act2.this, act4.class);
                                    intent.putExtra("id", userId);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}