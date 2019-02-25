package com.s.eventmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private ListView eventList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventList = findViewById(R.id.event_list);
        setEventData();
    }

    private void setEventData() {
        List<Event> eventListData = new ArrayList<>();

        eventListData.add(new Event("Event 1", R.drawable.first, "21 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 2", R.drawable.second, "22 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 3", R.drawable.third, "23 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 4", R.drawable.forth, "24 Feb, 2019", getString(R.string.dummy)));

        EventAdapter eventAdapter = new EventAdapter(eventListData, getApplicationContext());

        eventList.setAdapter(eventAdapter);
    }
}
