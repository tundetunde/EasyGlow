package com.techone.easyglow;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by tunde_000 on 24/08/2016.
 */
public class DBConnectivity {
    private static DatabaseReference mDatabase;

    public DBConnectivity(){

    }

    public static void initialize(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public UserItem GetUser(String emailAddress){
        final UserItem[] theUser = new UserItem[1];
        emailAddress = emailAddress.replace(".", ",");
        Query user = mDatabase.child("users").child(emailAddress);

        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserItem user = dataSnapshot.getValue(UserItem.class);
                theUser[0] = user;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return theUser[0];
    }

    public void updateLocation(String email, LocationItem location){
        email = email.replace(".", ",");
        UserItem user = GetUser(email);
        if(user != null){
            user.locationItem = location;
            mDatabase.child("users").child(email).setValue(user);
        }
    }
}
