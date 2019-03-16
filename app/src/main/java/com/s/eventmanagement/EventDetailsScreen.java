package com.s.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
                eventDate.setText(event.getDate());
                eventDescription.setText(event.getDescription());
                new DownloadImageTask(eventImage)
                        .execute("http://18.222.74.167/event-management/uploads/image/" + event.getImage());
            }
        }
    }
}
