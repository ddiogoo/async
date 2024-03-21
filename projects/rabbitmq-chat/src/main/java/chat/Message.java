package chat;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String content;
    private String date;
    private String hour;

    public Message(String sender, String content, String date, String hour) {
        this.sender = sender;
        this.content = content;
        this.date = date;
        this.hour = hour;
    }

    public String sender() {
        return sender;
    }

    public String content() {
        return content;
    }

    public String date() {
        return date;
    }

    public String hour() {
        return hour;
    }
}
