package com.s.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDetailsScreen extends AppCompatActivity {

    private TextView evenName;
    private ImageView eventImage;
    private TextView eventDate;
    private TextView eventDescription;

    private Data event;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        evenName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.event_date);
        eventImage = findViewById(R.id.event_image);
        eventDescription = findViewById(R.id.event_details);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            event = (Data) intent.getExtras().get("event");
            if (event != null) {
                evenName.setText(event.getName());
                eventDate.setText(parseDateToddMMyyyy(event.getDate()).toUpperCase());
                eventDescription.setText(event.getDescription());
                new DownloadImageTask(eventImage)
                        .execute("http://18.222.74.167/event-management/uploads/image/" + event.getImage());
            }
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
