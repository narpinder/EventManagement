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

    private Event event;

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
            event = (Event) intent.getExtras().get("event");
            if (event != null) {
                evenName.setText(event.getEventName());
                eventDate.setText(event.getEventDate());
                eventDescription.setText(event.getEventDescription());
                eventImage.setImageDrawable(getResources().getDrawable(event.getEventDrawable()));
            }
        }
    }
}
