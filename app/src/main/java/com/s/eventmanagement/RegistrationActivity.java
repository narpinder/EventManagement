package com.s.eventmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Username;
    private EditText Password;
    private EditText Name;
    private EditText Phone;

    private Button Registration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        Registration = findViewById(R.id.Registration);

        Registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == Registration) {
            if (Username.getText().toString().isEmpty()) {
                showToast("Please enter username");
                return;
            } else if (Password.getText().toString().isEmpty()) {
                showToast("Please enter the password");
                return;
            } else if (Name.getText().toString().isEmpty()) {
                showToast("Please enter the name");
                return;
            } else if (Phone.getText().toString().isEmpty()) {
                showToast("Please enter the phone number");
                return;
            }
            showToast("Register successfully");
        }
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
