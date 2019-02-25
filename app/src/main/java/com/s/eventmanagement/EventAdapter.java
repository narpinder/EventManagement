package com.s.eventmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private List<Event> eventList;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView evenName;
        ImageView eventImage;
        TextView eventDate;
        TextView eventDescription;
    }

    public EventAdapter(List<Event> eventList, Context context) {
        super(context, R.layout.event_row, eventList);
        this.eventList = eventList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.event_row, parent, false);
            viewHolder.evenName = convertView.findViewById(R.id.event_name);
            viewHolder.eventDate = convertView.findViewById(R.id.event_date);
            viewHolder.eventImage = convertView.findViewById(R.id.event_image);
            viewHolder.eventDescription = convertView.findViewById(R.id.event_details);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (event != null) {
            viewHolder.evenName.setText(event.getEventName());
            viewHolder.eventDate.setText(event.getEventDate());
            viewHolder.eventDescription.setText(event.getEventDescription());
            viewHolder.eventImage.setImageDrawable(mContext.getResources().getDrawable(event.getEventDrawable()));
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }
}