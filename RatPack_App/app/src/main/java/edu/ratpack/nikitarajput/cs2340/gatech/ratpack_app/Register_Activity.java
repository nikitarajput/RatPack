package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;


public class Register_Activity extends AppCompatActivity {

    EditText username, password, confirmPassword, email;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";
    Map<String, User> currentUsers = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText)findViewById(R.id.register_username_editText);
        password = (EditText)findViewById(R.id.register_password_editText);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_editText);
        email = (EditText)findViewById(R.id.email_address_editText);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    /**
     * Registers the user.
     *
     * @param v the current view that the data is coming from.
     */
    public void register(View v){
        if(username.getText().toString().length() > 16 || username.getText().toString().length() < 4) {
            Toast.makeText(getApplicationContext(), "Username must be of length 4-16", Toast.LENGTH_SHORT).show();
            username.requestFocus();
        }
        else if(password.getText().toString().length() > 16 || password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be of length 6-16", Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }
        else if(!noSpecialChar(username.getText().toString())){
            Toast.makeText(getApplicationContext(), "Username can only contain characters \'a-z\', \'A-Z\', \'0-9\', or \'_\'", Toast.LENGTH_SHORT).show();
            username.requestFocus();
        }
        else if(!verifyConfirmPassword());
        else if(!verifyUniqueUsername());
        else if(email.getText().toString().length() < 3){
            Toast.makeText(getApplicationContext(), "Email must contain at least 3 characters", Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Register_Activity.this, "Registration successful!.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                                } else {
                                    Toast.makeText(Register_Activity.this, "Authentication failed. Please try again.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

        }
    }

    /**
     * Verifies that the username is not already in the database.
     *
     * @return a boolean whether or not the username is unique.
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

    /**
     * Checks for special characters in text
     * @param text is either username
     * @return true for no special char and false for yes special char
     */
    private boolean noSpecialChar(String text) {
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(!(c == 95) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122) && !(c >= 48 && c <= 57))//[_,A-Z,a-z,0-9]
                return false;
        }
        return true;

    }

    public void toWelcomeActivity(View v){
        startActivity(new Intent(Register_Activity.this, Welcome_Activity.class));
    }
}