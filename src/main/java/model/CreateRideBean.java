package model;

import businessLogic.BLFacade;
import configuration.UtilDate;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import factories.BLFactory;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("createRide")
@SessionScoped
public class CreateRideBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String departCity;
    private String arrivalCity;
    private Integer seats;
    private Float price;
    private Date rideDate;

    private static BLFacade appFacadeInterface = BLFactory.getFacade();

    public String createRide() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (rideDate.before(new Date())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date must be later than today.", null));
            return null;
        }

        try {
            appFacadeInterface.createRide(departCity, arrivalCity, UtilDate.trim(rideDate), seats, price, "driver");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ride successfully created!", null));
        } catch (RideAlreadyExistException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ride already exists.", null));
        } catch (RideMustBeLaterThanTodayException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date must be later than today.", null));
        }

        return null;
    }

    public String getDepartCity() { return departCity; }
    public void setDepartCity(String departCity) { this.departCity = departCity; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }

    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }

    public Date getRideDate() { return rideDate; }
    public void setRideDate(Date rideDate) { this.rideDate = rideDate; }
}
