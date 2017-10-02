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

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPasswordRegistration";

    EditText username, password, confirmPassword;
    Map<String, User> currentUsers = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText)findViewById(R.id.register_username_editText);
        password = (EditText)findViewById(R.id.register_password_editText);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_editText);
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Registers the user.
     *
     * @param v the current view that the data is coming from.
     */
    public void register(View v) {
        if (verifyUsernameLength() && verifyPasswordLength() && verifyConfirmPassword()) {
            mAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail" + task.isSuccessful());
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
        } else {
            Toast.makeText(getApplicationContext(), "Registration unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
        }
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