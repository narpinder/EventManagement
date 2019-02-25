package com.s.eventmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText UserName;
    private EditText Password;

    private Button Login;
    private Button Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);
        Registration = findViewById(R.id.Registration);

        Login.setOnClickListener(this);
        Registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == Login) {
            if (UserName.getText().toString().isEmpty()) {
                showToast("Please enter username");
                return;
            } else if (Password.getText().toString().isEmpty()) {
                showToast("Please enter the password");
                return;
            }
            showToast("Login Successful");
            Intent intent = new Intent(getApplicationContext(), EventActivity.class);
            startActivity(intent);
        } else if (v == Registration) {
            Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
