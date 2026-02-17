package com.tablero_medidores.backend.service.mail;

public class EmailDetails {
    private String from;
    private String to;
    private String subject;
    private String text;

    public EmailDetails (String from , String to, String subject, String text ) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getText() {
        return this.text;
    }

}
