package com.techone.easyglow;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tunde_000 on 24/08/2016.
 */
public class DBConnectivity {
    private static DatabaseReference mDatabase;
    private static FirebaseAuth firebaseAuth;
    public DBConnectivity(){

    }

    public static void initialize(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void GetUser(String emailAddress, LocationItem location){
        final LocationItem locationItem1 = location;
        emailAddress = emailAddress.replace(".", ",");
        Query user = mDatabase.child("users").child(emailAddress);

        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Log.d("Database Snapshot", "Snapshot Value: " + dataSnapshot.getValue().toString());
                    UserItem user = new UserItem(dataSnapshot.getValue(UserItem.class));
                    Log.d("Database Snapshot", "Snapshot Collected: " + user.getEmail());
                    user.setLocationItem(locationItem1);
                    mDatabase.child("users").child(user.getEmail()).setValue(user);
                }catch (DatabaseException e){
                    Log.w("Database Exc", "Not of UserItem Type: " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateLocation(String email, LocationItem location){
        email = email.replace(".", ",");
        GetUser(email, location);
    }

    public void becomePartner(){
        String emailAddress = "";
        try {
            firebaseAuth.getCurrentUser().getEmail();
        }catch (NullPointerException e){
            Log.w("Not Signed In", "DBConnectivity");
            return;
        }
        emailAddress = emailAddress.replace(".", ",");
        Query user = mDatabase.child("users").child(emailAddress);

        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Log.d("Database Snapshot", "Snapshot Value: " + dataSnapshot.getValue().toString());
                    UserItem user = new UserItem(dataSnapshot.getValue(UserItem.class));
                    Log.d("Database Snapshot", "Snapshot Collected: " + user.getEmail());
                    user.setAccountType("p");
                    mDatabase.child("users").child(user.getEmail()).setValue(user);
                }catch (DatabaseException e){
                    Log.w("Database Exc", "Not of UserItem Type: " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
