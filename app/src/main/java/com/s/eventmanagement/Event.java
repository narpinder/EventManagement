package com.s.eventmanagement;

public class Event {
    private String EventName;
    private int EventDrawable;
    private String EventDate;
    private String EventDescription;

    public Event(String eventName, int eventDrawable, String eventDate, String eventDescription) {
        EventName = eventName;
        EventDrawable = eventDrawable;
        EventDate = eventDate;
        EventDescription = eventDescription;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getEventDrawable() {
        return EventDrawable;
    }

    public void setEventDrawable(int eventDrawable) {
        EventDrawable = eventDrawable;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }
}
