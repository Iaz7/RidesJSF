package model;

import businessLogic.BLFacade;
import domain.ChatMessage;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("chat")
@SessionScoped
public class ChatBean implements Serializable {

    private List<ChatMessage> messages;
    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public ChatBean() {
        messages = appFacadeInterface.getMessages();
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
