package com.techone.easyglow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.api.model.StringList;

/**
 * Created by tunde_000 on 19/08/2016.
 */
public class Login extends Activity implements View.OnClickListener{
    EditText username;
    EditText password;
    Button btSignUp;
    Button btLogin;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            Intent i = new Intent("com.techone.easyglow.MAPSCREEN");
            startActivity(i);
        }
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initialize(){
        username = (EditText)findViewById(R.id.etUsernameLogin);
        password = (EditText)findViewById(R.id.etPasswordLogin);
        btSignUp = (Button)findViewById(R.id.btSignUpLoginScreen);
        btSignUp.setOnClickListener(this);
        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        String usernameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        switch (view.getId()) {
            case R.id.btLogin:
                firebaseAuth.signInWithEmailAndPassword(usernameText, passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT);
                            Intent i = new Intent("com.techone.easyglow.MAPSCREEN");
                            startActivity(i);
                        }else {
                            Toast.makeText(Login.this, "Username or Password is incorrect, Please Try Again", Toast.LENGTH_SHORT);
                        }
                    }
                });

                break;
            case R.id.btSignUpLoginScreen:
                Intent j = new Intent("com.techone.easyglow.SIGNUP");
                startActivity(j);
                break;
        }
    }
}
