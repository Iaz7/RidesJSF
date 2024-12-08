package model;

import businessLogic.BLFacade;
import domain.Ride;
import factories.BLFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("myRides")
@RequestScoped
public class MyRidesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Ride> myRides;

    private static BLFacade appFacadeInterface;

    public MyRidesBean() {
        appFacadeInterface = BLFactory.getFacade();
        myRides = appFacadeInterface.getRidesByDriver(appFacadeInterface.getLoggedInDriver());
    }

    public List<Ride> getMyRides() {
        return myRides;
    }

    public void setMyRides(List<Ride> myRides) {
        this.myRides = myRides;
    }
}
