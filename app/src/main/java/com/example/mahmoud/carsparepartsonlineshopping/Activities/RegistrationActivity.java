package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {


    EditText fName, lName, email, password, confirmPassword, phone;
    Button SignUp;
    TextView visible, unVisible, confirmPasswordVisible, confirmPasswordUnVisible;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        from = getIntent().getStringExtra("from");

        fName = findViewById(R.id.firstNameSignup);
        lName = findViewById(R.id.lastNameSignUp);
        email = findViewById(R.id.emailSignUp);
        password = findViewById(R.id.passwordSignUp);
        confirmPassword = findViewById(R.id.confirmPassswordSignUp);
        phone = findViewById(R.id.phoneSignUp);
        SignUp = findViewById(R.id.SignUp);
        visible = findViewById(R.id.visiblePassWordSignUp);
        unVisible = findViewById(R.id.uNvisiblePassWordSignUp);
        confirmPasswordVisible = findViewById(R.id.visibleConfirmPassWordSignup);
        confirmPasswordUnVisible = findViewById(R.id.uNvisibleConfirmPassWordSignup);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                visible.setVisibility(View.INVISIBLE);
                unVisible.setVisibility(View.VISIBLE);
            }
        });
        unVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                visible.setVisibility(View.VISIBLE);
                unVisible.setVisibility(View.INVISIBLE);
            }
        });
        confirmPasswordVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                confirmPasswordVisible.setVisibility(View.INVISIBLE);
                confirmPasswordUnVisible.setVisibility(View.VISIBLE);
            }
        });
        confirmPasswordUnVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPasswordVisible.setVisibility(View.VISIBLE);
                confirmPasswordUnVisible.setVisibility(View.INVISIBLE);
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fName.getText().toString().isEmpty()) {
                    if (!lName.getText().toString().isEmpty()) {
                        if (!email.getText().toString().isEmpty()) {
                            if (!phone.getText().toString().isEmpty()) {
                                if (phone.getText().toString().length() == 11 && phone.getText().toString().startsWith("01")) {
                                    if (!password.getText().toString().isEmpty()) {
                                        if (password.getText().toString().length() >= 6) {
                                            if (!confirmPassword.getText().toString().isEmpty()) {
                                                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                                                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful()) {

                                                                String user = firebaseAuth.getCurrentUser().getUid();
                                                                databaseReference.child(user).child("user_id").setValue(user);
                                                                databaseReference.child(user).child("name").setValue(fName.getText().toString().concat(" " + lName.getText().toString()));
                                                                databaseReference.child(user).child("email").setValue(email.getText().toString());
                                                                databaseReference.child(user).child("phone").setValue(phone.getText().toString());

                                                                if (from.equals("CHECKOUT")) {
                                                                    startActivity(new Intent(RegistrationActivity.this, CheckoutActivity.class));

                                                                } else {
                                                                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                                                }
                                                                finish();
                                                            } else {

                                                                Toast.makeText(RegistrationActivity.this, "Email or password not valid", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                                } else {

                                                    Toast.makeText(RegistrationActivity.this, "password not matched", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {

                                                Toast.makeText(RegistrationActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {

                                            Toast.makeText(RegistrationActivity.this, "password is shortest", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {

                                        Toast.makeText(RegistrationActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "phone not true", Toast.LENGTH_SHORT).show();

                                }
                            } else {

                                Toast.makeText(RegistrationActivity.this, "phone is empty", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(RegistrationActivity.this, "email is empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(RegistrationActivity.this, "last Name is empty", Toast.LENGTH_SHORT).show();
                    }
                } else

                {

                    Toast.makeText(RegistrationActivity.this, "first Name is empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
