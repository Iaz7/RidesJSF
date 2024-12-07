package model;

import businessLogic.BLFacade;
import domain.Ride;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Named("myRides")
@SessionScoped
public class MyRidesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Ride> myrides;

    private static BLFacade appFacadeInterface;

    public MyRidesBean() {
        appFacadeInterface = BLFactory.getFacade();
        String username = "driver";
        myrides = appFacadeInterface.getRidesByDriver(username);
    }

    public List<Ride> getMyrides() {
        return myrides;
    }

    public void setMyrides(List<Ride> myrides) {
        this.myrides = myrides;
    }
}
