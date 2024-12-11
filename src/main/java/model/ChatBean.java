package model;

import businessLogic.BLFacade;
import domain.ChatMessage;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("chat")
@SessionScoped
public class ChatBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    private List<ChatMessage> messages;
    private String input = "";

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public ChatBean() {
        refreshMessages();
    }

    public void sendMessage() {
        if (getInput().isEmpty()) return;
        appFacadeInterface.sendMessage(loginBean.getLoggedInDriver(), input);
        refreshMessages();
        input = "";
    }

    public void refreshMessages() {
        messages = appFacadeInterface.getMessages();
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public String getCurrentUser() {
        return loginBean.getLoggedInDriver();
    }

    public String getInput() {
        return input.strip();
    }
    public void setInput(String input) {
        this.input = input.strip();
    }
}
