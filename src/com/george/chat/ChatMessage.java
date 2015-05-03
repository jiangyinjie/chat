package com.george.chat;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable
{

    private Date date;
    private String message;
    private Type type;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

}
