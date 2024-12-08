package com.example.androidapplication;

public class ChatMessage {
    private String author;
    private String text;
    private String moment;

    public ChatMessage(String author, String text, String moment) {
        this.author = author;
        this.text = text;
        this.moment = moment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }
}
