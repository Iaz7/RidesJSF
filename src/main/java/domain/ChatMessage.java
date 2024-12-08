package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ChatMessage implements Serializable {

    @Id
    @XmlJavaTypeAdapter(IntegerAdapter.class)
    @GeneratedValue
    private int id;

    @ManyToOne
    private Driver sender;

    public ChatMessage() { super(); }

    public ChatMessage(Driver sender, String message) {
        this.sender = sender;
        this.message = message;
        this.date = new Date();
    }

    private Date date;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Driver getSender() {
        return sender;
    }

    public void setSender(Driver sender) {
        this.sender = sender;
    }

}
