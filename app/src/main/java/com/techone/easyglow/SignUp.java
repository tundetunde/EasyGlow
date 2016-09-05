package com.techone.easyglow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tunde_000 on 19/08/2016.
 */
public class SignUp extends Activity implements View.OnClickListener{
    EditText firstName;
    EditText surname;
    EditText username;
    EditText address;
    EditText password;
    EditText mobileNo;
    Button signUp;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initialize();
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void initialize(){
        firstName = (EditText)findViewById(R.id.etFirstName);
        surname = (EditText)findViewById(R.id.etSurname);
        username = (EditText)findViewById(R.id.etUsername);
        address = (EditText)findViewById(R.id.etAddress);
        password = (EditText)findViewById(R.id.etPassword);
        mobileNo = (EditText)findViewById(R.id.etPhoneNumber);
        signUp = (Button)findViewById(R.id.btSignUp);
        signUp.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    private void signUp(){
        String email = username.getText().toString().trim();
        final String firstNameText = firstName.getText().toString().trim();
        final String surnameText = surname.getText().toString().trim();
        final String passwordText = password.getText().toString().trim();
        final String addressText = address.getText().toString().trim();
        final String mobileNoText = mobileNo.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT);
            return;
        }

        final String emailText = email.replace(".", ",");

        if(TextUtils.isEmpty(firstNameText)){
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(surnameText)){
            Toast.makeText(this, "Please enter your surname", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(passwordText)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(mobileNoText)){
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(addressText)){
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT);
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is succesfully registered and logged in
                            //Move straight to Main Menu
                            Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT);
                            UserItem userItem = new UserItem(firstNameText, surnameText, emailText, passwordText, mobileNoText, addressText, "c");

                            mDatabase.child("users").child(emailText).setValue(userItem);
                            Intent i = new Intent("com.techone.easyglow.MAPSCREEN");
                            startActivity(i);
                        }else
                            Toast.makeText(SignUp.this, "Could not register... try again please", Toast.LENGTH_SHORT);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btSignUp:
                signUp();
                break;
        }
    }
}
