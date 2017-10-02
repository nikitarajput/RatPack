package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class Register_Activity extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Map<String, User> currentUsers = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText)findViewById(R.id.register_username_editText);
        password = (EditText)findViewById(R.id.register_password_editText);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_editText);
    }

    /**
     * Registers the user.
     *
     * @param v the current view that the data is coming from.
     */
    public void register(View v){
        if (verifyUniqueUsername() && verifyUsernameLength() && verifyPasswordLength() && verifyConfirmPassword()) {
            User addedUser = new User(username.getText().toString(), password.getText().toString(), username.getText().toString());
            currentUsers.put(addedUser.getUsername(), addedUser);
            Toast.makeText(getApplicationContext(), "Registering your account...",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        }
    }

    /**
     * Verifies that the username is not already in the database.
     *
     * @return boolean true if the username is unique and false otherwise.
     *
     */
    private boolean verifyUniqueUsername() {
        if (currentUsers.containsKey(username.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Account already exists for this username.", Toast.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
            confirmPassword.setText("");
            username.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Verifies that the email is the correct length.
     *
     * @return boolean true if the password has the correct length and false if it does not.
     *
     */
    private boolean verifyUsernameLength() {
        if (username.getText().toString().length() < 3){
            Toast.makeText(getApplicationContext(), "Email must contain at least 3 characters.", Toast.LENGTH_SHORT).show();
            username.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Verifies that the password has the correct length.
     *
     * @return boolean true if the password has the correct length and false if it does not.
     *
     */
    private boolean verifyPasswordLength() {
        if (password.getText().toString().length() > 16 || password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be of length 6-16.", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Verifies that the password and confirm password are the same.
     *
     * @return a boolean false is passwords aren't the same and true if they are.
     *
     */
    private boolean verifyConfirmPassword() {
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not match.",Toast.LENGTH_SHORT).show();
            password.setText("");
            confirmPassword.setText("");
            password.requestFocus();
            return false;
        }
        return true;
    }

    public void toWelcomeActivity(View v){
        startActivity(new Intent(Register_Activity.this, Welcome_Activity.class));
    }
}