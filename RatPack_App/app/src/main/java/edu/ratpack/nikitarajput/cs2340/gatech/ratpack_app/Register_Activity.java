package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Set;


public class Register_Activity extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Map<String, String> accountInformation = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText)findViewById(R.id.register_username_editText);
        password = (EditText)findViewById(R.id.register_password_editText);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_editText);
    }

    public void register(View v){
        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not match.",Toast.LENGTH_SHORT).show();
            password.setText("");
            confirmPassword.setText("");
            password.requestFocus();
        } else if (accountInformation.containsKey(username.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Account already exists for this username.",Toast.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
            confirmPassword.setText("");
            username.requestFocus();
        } else {
            accountInformation.put(username.getText().toString(), password.getText().toString());
            Toast.makeText(getApplicationContext(), "Registering your account...",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        }
    }

    public void toWelcomeActivity(View v){
        startActivity(new Intent(Register_Activity.this, Welcome_Activity.class));
    }
}