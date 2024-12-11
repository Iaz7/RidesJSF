package model;

import businessLogic.BLFacade;
import domain.Ride;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("myRides")
@SessionScoped
public class MyRidesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private LoginBean loginBean;

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public List<Ride> getMyRides() {
        return appFacadeInterface.getRidesByDriver(loginBean.getLoggedInDriver());
    }
}
