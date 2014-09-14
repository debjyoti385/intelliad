package com.debjyotipaul.api;

import org.hibernate.validator.constraints.Length;

public class Message {

    private final long id;

    @Length(max = 3)
    private final String content;

    public Message(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
