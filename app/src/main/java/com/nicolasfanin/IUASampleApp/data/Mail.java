package com.nicolasfanin.IUASampleApp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Mail implements Parcelable {

    private String from;
    private String to;
    private String subject;
    private String content;
    private String date;

    public Mail(String from, String to, String subject, String content, String date) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
