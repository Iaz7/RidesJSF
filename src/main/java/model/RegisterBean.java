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

    private String email = null;
    private String name = null;
    private String password = null;

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (appFacadeInterface.addDriver(email, password, name)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful!", null));
            email = "";
            name = "";
            password = "";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed. Try again.", null));
        }

        return null;
    }
}
