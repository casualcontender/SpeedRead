package edu.csumb.mattw.speedread;

/**
 * Created by Matt on 5/9/2015.
 */
public class UserLog {
    String event;
    String timeStamp;
    String dateStamp;
    String userName;

    UserLog(String event, String timeStamp, String dateStamp, String userName){
        this.event = event;
        this.timeStamp = timeStamp;
        this.userName = userName;
        this.dateStamp = dateStamp;
    }

    public String getEvent() {
        return event;
    }

    public String getTime() {
        return timeStamp;
    }

    public String getUserName() { return userName; }

    public String getDateStamp() { return dateStamp; }
}
