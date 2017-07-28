package com.genesisdigisec.loom;

/**
 * Created by amitsin6h on 6/15/17.
 */

public class LoginDetail {

    //url og login php file
    public static final String LOGIN_URL = "http://genesisdigisec.com/client/20172/app/app_login.php";

    //Keys for email and password as defined in our $_POST['key'] in php file
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "Successfully loged in";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
