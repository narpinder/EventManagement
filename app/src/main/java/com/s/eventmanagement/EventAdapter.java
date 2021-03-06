package com.s.eventmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Data> {

    private List<Data> eventList;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView evenName;
        ImageView eventImage;
        TextView eventDate;
        TextView eventDescription;
    }

    public EventAdapter(List<Data> eventList, Context context) {
        super(context, R.layout.event_row, eventList);
        this.eventList = eventList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Data event = getItem(position);
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
            viewHolder.evenName.setText(event.getName());
            viewHolder.eventDate.setText(parseDateToddMMyyyy(event.getDate()).toUpperCase());
            viewHolder.eventDescription.setText(event.getDescription());
            new DownloadImageTask(viewHolder.eventImage)
                    .execute("http://18.222.74.167/event-management/uploads/image/" + event.getImage());
        }
        return convertView;
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

    @Override
    public int getCount() {
        return eventList.size();
    }
}