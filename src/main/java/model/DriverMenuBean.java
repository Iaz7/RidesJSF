package model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("driverMenu")
@SessionScoped
public class DriverMenuBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private LoginBean loginBean;

    public LoginBean getLoginBean() { return loginBean; }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}