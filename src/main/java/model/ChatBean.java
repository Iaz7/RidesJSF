package model;

import businessLogic.BLFacade;
import domain.ChatMessage;
import factories.BLFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("chat")
@ViewScoped
public class ChatBean implements Serializable {

    private List<ChatMessage> messages;
    private String currentUser;
    private String input = "";

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public ChatBean() {
        messages = appFacadeInterface.getMessages();
        currentUser = appFacadeInterface.getLoggedInDriver();
    }

    public void sendMessage() {
        if (getInput().isEmpty()) return;
        appFacadeInterface.sendMessage(currentUser, input);
        messages = appFacadeInterface.getMessages();
        input = "";
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public String getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getInput() {
        return input.strip();
    }
    public void setInput(String input) {
        this.input = input.strip();
    }
}
