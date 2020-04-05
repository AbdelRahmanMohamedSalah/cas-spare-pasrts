package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity {
    Button signIn;
    TextView signUp;
    EditText email, password;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener AuthStateListener;
    String from;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(AuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        from = getIntent().getStringExtra("from");


        AuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    if (from.equals("CHECKOUT")) {
                        startActivity(new Intent(LogInActivity.this, CheckoutActivity.class));

                    } else {
                        startActivity(new Intent(LogInActivity.this, MainActivity.class));
                    }
                    finish();
                }
            }
        };
        firebaseAuth = FirebaseAuth.getInstance();


        signIn = findViewById(R.id.signIn);
        email = findViewById(R.id.eTemailLogIn);
        password = findViewById(R.id.eTpassWordLogIn);
        signUp = findViewById(R.id.signUp);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Email = email.getText().toString();
                String pass = password.getText().toString();
                if (!Email.isEmpty()) {
                    if (!pass.isEmpty()) {
                        if (pass.length() >= 6) {
                            firebaseAuth.signInWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {

                                        Toast.makeText(LogInActivity.this, "email or password not true", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        } else {

                            Toast.makeText(LogInActivity.this, "password shortest", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(LogInActivity.this, "please enter Password", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(LogInActivity.this, "please enter email", Toast.LENGTH_SHORT).show();

                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LogInActivity.this, RegistrationActivity.class).putExtra("from", "CHECKOUT"));
            }
        });


    }
}
