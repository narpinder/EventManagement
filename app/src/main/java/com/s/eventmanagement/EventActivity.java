package com.s.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView eventList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventList = findViewById(R.id.event_list);
        setEventData();
        eventList.setOnItemClickListener(this);
    }

    private List<Event> eventListData;

    private void setEventData() {
        eventListData = new ArrayList<>();

        eventListData.add(new Event("Event 1", R.drawable.first, "21 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 2", R.drawable.second, "22 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 3", R.drawable.third, "23 Feb, 2019", getString(R.string.dummy)));
        eventListData.add(new Event("Event 4", R.drawable.forth, "24 Feb, 2019", getString(R.string.dummy)));

        EventAdapter eventAdapter = new EventAdapter(eventListData, getApplicationContext());

        eventList.setAdapter(eventAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EventDetailsScreen.class);
        intent.putExtra("event", (Serializable) eventListData.get(position));
        startActivity(intent);
    }
}
