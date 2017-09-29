package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Register_Activity extends AppCompatActivity {

    EditText username, password, confirmPassword;

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
        if (verifyConfirmPassword() && verifyUniqueUsername()) {
            User addedUser = new User(username.getText().toString(), password.getText().toString(), false, username.getText().toString());
            // need to add this user to the database
            Toast.makeText(getApplicationContext(), "Registering your account...",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        }
    }

    /**
     * Verifies that the username is not already in the database.
     *
     * @return a boolean whether or not the username is unique.
     */
    private boolean verifyUniqueUsername() {
        if (false) { // need to check if username is already in database
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
     * Verifies that the password and confirm password are the same.
     *
     * @return a boolean whether or not the password is the same.
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