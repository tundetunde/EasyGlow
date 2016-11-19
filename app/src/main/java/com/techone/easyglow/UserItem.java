package com.techone.easyglow;

/**
 * Created by tunde_000 on 23/08/2016.
 */
public class UserItem {

    private String firstname;
    private String surname;
    private String email;
    private String pword;
    private String phonenumber;
    private String address;
    private String accountType;
    private LocationItem locationItem;

    public UserItem(){

    }
    public UserItem(UserItem uI){
        firstname = uI.firstname;
        surname = uI.surname;
        email = uI.email;
        pword = uI.pword;
        phonenumber = uI.phonenumber;
        address = uI.address;
        accountType = uI.accountType;
        locationItem = uI.locationItem;
    }

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

    public void getData(UserItem uI){
        firstname = uI.firstname;
        surname = uI.surname;
        email = uI.email;
        pword = uI.pword;
        phonenumber = uI.phonenumber;
        address = uI.address;
        accountType = uI.accountType;
        locationItem = uI.locationItem;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocationItem getLocationItem() {
        return locationItem;
    }

    public void setLocationItem(LocationItem locationItem) {
        this.locationItem = locationItem;
    }

}
