package com.s.eventmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView eventList;
    private ProgressDialog dialog;
    private List<Data> eventListData;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        eventList = findViewById(R.id.event_list);
        eventList.setOnItemClickListener(this);
        dialog = new ProgressDialog(EventActivity.this);
        swipe_refresh.setOnRefreshListener(this);
        dialog.setMessage("Getting events...");
        dialog.show();
        getEvents();
    }

    private void getEvents() {
        new FetchEvents().execute();
    }

    private void setEventData() {
        EventAdapter eventAdapter = new EventAdapter(eventListData, getApplicationContext());

        eventList.setAdapter(eventAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EventDetailsScreen.class);
        intent.putExtra("event", (Serializable) eventListData.get(position));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getEvents();
    }

    private class FetchEvents extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://18.222.74.167/event-management/api/events.json");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                urlConnection.setRequestProperty("Accept", "application/json; charset=utf-8");
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                swipe_refresh.setRefreshing(false);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        swipe_refresh.setRefreshing(false);
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipe_refresh.setRefreshing(false);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (s.contains("success")) {
                Log.i("json", s);
                Gson gson = new Gson();
                Type typeOfObjectsList = new TypeToken<Event>() {
                }.getType();
                Event event = gson.fromJson(s, typeOfObjectsList);
                eventListData = event.getResponse().getData();
                setEventData();
            }
        }
    }


}


