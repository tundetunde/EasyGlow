package com.techone.easyglow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by User on 01/10/2016.
 */
public class SignUpPartner extends Activity implements View.OnClickListener{
    private ProgressDialog progressDialog;
    private Button btPartner;
    private DBConnectivity database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.become_partner);
        initialize();

    }

    private void initialize(){
        progressDialog = new ProgressDialog(this);
        btPartner = (Button)findViewById(R.id.btActivatePartnership);
        btPartner.setOnClickListener(this);
        database = new DBConnectivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btActivatePartnership:
                progressDialog.setMessage("Activating Partnership...");
                progressDialog.show();
                database.becomePartner();
                startActivity(new Intent("com.techone.easyglow.MAPSCREEN"));
                break;
        }
    }
}
