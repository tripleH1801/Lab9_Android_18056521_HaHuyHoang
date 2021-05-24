package com.example.lab9_18056521_hahuyhoang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    }
}