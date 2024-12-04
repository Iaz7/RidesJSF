package model;

import businessLogic.BLFacade;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("register")
@SessionScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username = null;
    private String password = null;
    private String userType = "Driver";

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                userType == null || userType.trim().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please fill in all fields.", null));
            return null;
        }

        boolean added = false;

        if ("Driver".equals(userType)) {
            added = appFacadeInterface.addDriver(username, password);
        } else if ("Traveler".equals(userType)) {
            added = appFacadeInterface.addTraveler(username, password);
        }

        if (added) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful!", null));
            username = "";
            password = "";
            userType = "Driver";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed. Try again.", null));
        }

        return null;
    }
}
