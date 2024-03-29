package br.com.rafaelmurata.model;

import java.util.Date;

public class Message {

    public enum Priority {

        HIGH, MEDIUM, LOW
    }

    private long timestamp;
    private Priority priority;
    private String text;

    public Message(long timestamp, Priority priority, String text) {
        this.timestamp = timestamp;
        this.priority = priority;
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getText() {
        return text;
    }

	@Override
	public String toString() {
		return "Message [timestamp=" + timestampToCalendar(timestamp)  + ", priority=" + priority + ", text=" + text + "]";
	}
	public static Date timestampToCalendar( long timestamp) {
	    java.util.Date time = new Date(timestamp);
	    return time;
	}

}
