package com.example.lab9_18056521_hahuyhoang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class act3 extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName3;
    private TextView tvEmail3;
    private TextView tvPass3;
    private TextView tvRpPass3;
    private TextView tvGotoSignIn3;
    private Button btnSignIn2;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String userId;

    private final String NAME_NULL = "Enter your name";
    private final String EMAIL_NULL = "Choose your email address";
    private final String PASS_NULL = "Enter your password";
    private final String RP_PASS_NULL = "Repeat your password";
    private final String RP_PASS_FAIL = "The passwords entered do not match. Try again";
    private final String REGISTER_SUCCESS = "Registration Successful";
    private final String REGISTER_FAIL = "Registration Fail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_act3);
        tvName3 = findViewById(R.id.tvName3);
        tvEmail3 = findViewById(R.id.tvEmail3);
        tvPass3 = findViewById(R.id.tvPass3);
        tvRpPass3 = findViewById(R.id.tvRpPass3);
        tvGotoSignIn3 = findViewById(R.id.tvGotoSignIn3);
        btnSignIn2 = findViewById(R.id.btnSignIn2);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        tvGotoSignIn3.setOnClickListener(this);
        btnSignIn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn2:
                String name = tvName3.getText().toString().trim();
                String email = tvEmail3.getText().toString().trim();
                String pass = tvPass3.getText().toString().trim();
                String passRepeat = tvRpPass3.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(this, NAME_NULL, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.isEmpty()) {
                    Toast.makeText(this, EMAIL_NULL, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.isEmpty()) {
                    Toast.makeText(this, PASS_NULL, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passRepeat.isEmpty()) {
                    Toast.makeText(this, RP_PASS_NULL, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passRepeat.equals(pass)) {
                    Toast.makeText(this, RP_PASS_FAIL, Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    userId = firebaseAuth.getCurrentUser().getUid();
                                    User user = new User(name, email, 0, 0, 0);
                                    firebaseFirestore.collection("Users")
                                            .document(userId) //xem lai
                                            .set(user) // xemlai
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(act3.this, REGISTER_SUCCESS, Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(act3.this, act2.class);
                                                    startActivity(intent);
                                                }

                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(act3.this, REGISTER_FAIL, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    Toast.makeText(act3.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;

            case R.id.tvGotoSignIn3:
                Intent intent = new Intent(act3.this, act2.class);
                startActivity(intent);
                break;
        }
    }
}