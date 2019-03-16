package com.s.eventmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText UserName;
    private EditText Password;

    private Button Login;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);

        Login.setOnClickListener(this);

        dialog = new ProgressDialog(MainActivity.this);
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
            dialog.setMessage("Logging in...");
            dialog.show();
            new HTTPAsyncTask().execute("http://18.222.74.167/event-management/api/login.json");

        }
    }


    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    return httpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    return "Error!";
                }
            } catch (IOException e) {
                dialog.dismiss();
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if (result.equals("OK")) {
                showToast("Login Successful");
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);
            } else {
                showToast("Wrong username or password");
            }
        }
    }

    private String httpPost(String myUrl) throws IOException, JSONException {
        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("Accept", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        return conn.getResponseMessage() + "";

    }

    private JSONObject buidJsonObject() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("username", UserName.getText().toString());
        jsonObject.accumulate("password", Password.getText().toString());
        return jsonObject;
    }

    private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }


    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
