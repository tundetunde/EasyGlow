package com.techone.easyglow;

/**
 * Created by tunde_000 on 23/08/2016.
 */
public class UserItem {

    public String firstname;
    public String surname;
    public String email;
    public String pword;
    public String phonenumber;
    public String address;
    public String accountType;
    public LocationItem locationItem;

    public UserItem(String firstname, String surname, String email, String password, String Phonenumber, String address
    , String accountType){
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.pword = password;
        this.phonenumber = Phonenumber;
        this.address = address;
        this.accountType = accountType;
    }

}
