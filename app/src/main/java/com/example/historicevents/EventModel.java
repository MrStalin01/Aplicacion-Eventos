package com.example.historicevents;

public class EventModel {
    public String eventName;
    public String eventDate;
    public String eventLocation;
    private String answer;
    private boolean isCompleted = false;


    public EventModel(String eventName, String eventDate, String eventLocation, String answer) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.answer = answer;
    }

    public String getEventName() { return eventName; }
    public String getEventDate() { return eventDate; }
    public String getEventLocation() { return eventLocation; }

    public String getAnswer() { return answer; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}