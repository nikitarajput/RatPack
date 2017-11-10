package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

/**
 * Class that allows user to login.
 */
public class Login_Activity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPasswordLogIn";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        username = (EditText)findViewById(R.id.username_editText);
        password = (EditText)findViewById(R.id.password_editText);
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Authorizes user login.
     * @param v current view
     */
    public void login(View v) {
        mAuth.signInWithEmailAndPassword(username.getText().toString(),
                password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            RatFB.init();
                            Toast.makeText(getApplicationContext(),
                                    "Logging in...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_Activity.this,
                                    Home_Activity.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login_Activity.this,
                                    "Incorrect username or password. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Takes user to Welcome Screen.
     * @param v current view
     */
    public void toWelcomeActivity(View v){
        startActivity(new Intent(Login_Activity.this, Welcome_Activity.class));
    }
}
