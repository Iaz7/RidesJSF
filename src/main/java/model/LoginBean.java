package model;

import businessLogic.BLFacade;
import factories.BLFactory;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (appFacadeInterface.isRegistered(username, password)) {
            return "login";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password.", null));
            return null;
        }
    }
}
