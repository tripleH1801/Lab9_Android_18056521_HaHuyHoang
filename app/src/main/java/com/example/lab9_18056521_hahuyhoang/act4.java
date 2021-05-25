package com.example.lab9_18056521_hahuyhoang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class act4 extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgBtnGood;
    private ImageButton imgBtnSoSo;
    private ImageButton imgBtnBad;
    private Button btnFinish;

    private FirebaseFirestore db;
    private DocumentReference documentReference;

    private String userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4);

        imgBtnGood  = findViewById(R.id.imgBtnGood);
        imgBtnSoSo  = findViewById(R.id.imgBtnSoSo);
        imgBtnBad  = findViewById(R.id.imgBtnBad);
        btnFinish  = findViewById(R.id.btnFinish);

        imgBtnGood.setOnClickListener(this);
        imgBtnSoSo.setOnClickListener(this);
        imgBtnBad.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("id");

        db = FirebaseFirestore.getInstance();
        documentReference = db.collection("Users").document(userId);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBtnGood:
                user.setHappy(1);
                user.setNormal(0);
                user.setSad(0);
                Toast.makeText(this, "Happy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgBtnSoSo:
                user.setHappy(1);
                user.setNormal(0);
                user.setSad(0);
                Toast.makeText(this, "Normal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgBtnBad:
                user.setHappy(0);
                user.setNormal(0);
                user.setSad(1);
                Toast.makeText(this, "Sad", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnFinish:
                documentReference.update("happy", user.getHappy(),
                "sad", user.getSad(),
                "normal", user.getNormal());
                Toast.makeText(this, "Thanks for your opinion", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}