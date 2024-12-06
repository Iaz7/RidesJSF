package model;

import businessLogic.BLFacade;
import domain.Ride;
import factories.BLFactory;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("queryRides")
@ViewScoped
public class QueryRidesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> departCities;
    private List<String> destinationCities;
    private Date rideDate;

    private String departCity;
    private String destinationCity;

    private List<Ride> foundRides;

    private static BLFacade appFacadeInterface;

    public QueryRidesBean() {
        appFacadeInterface = BLFactory.getFacade();
        departCities = appFacadeInterface.getDepartCities();
        if (departCities.size() > 0) {
            departCity = departCities.get(0);
            destinationCities = appFacadeInterface.getDestinationCities(departCity);
            destinationCity = destinationCities.get(0);
            foundRides = appFacadeInterface.getRides(departCity, destinationCity, rideDate);
        }
    }

    public List<String> getDepartCities() { return departCities; }
    public void setDepartCities(List<String> departCities) { this.departCities = departCities; }

    public List<String> getDestinationCities() { return destinationCities; }
    public void setDestinationCities(List<String> destinationCities) { this.destinationCities = destinationCities; }

    public Date getRideDate() { return rideDate; }
    public void setRideDate(Date rideDate) { this.rideDate = rideDate; }

    public String getDestinationCity() { return destinationCity; }
    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }

    public String getDepartCity() { return departCity; }
    public void setDepartCity(String departCity) { this.departCity = departCity; }

    public List<Ride> getFoundRides() { return foundRides; }
    public void setFoundRides(List<Ride> foundRides) { this.foundRides = foundRides; }

    public void departCitySelected() {
        destinationCities = appFacadeInterface.getDestinationCities(departCity);
        destinationCity = destinationCities.get(0);
        foundRides = appFacadeInterface.getRides(departCity, destinationCity, rideDate);
    }

    public void destinationCitySelected() {
        foundRides = appFacadeInterface.getRides(departCity, destinationCity, rideDate);
    }

    public void rideDateSelected() {
        foundRides = appFacadeInterface.getRides(departCity, destinationCity, rideDate);
    }
}
