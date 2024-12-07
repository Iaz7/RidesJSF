package dataAccess;

import domain.Ride;

import java.util.Date;
import java.util.List;

public class HibernateDataAccess {
    public void open() {
    }

    public void close() {
    }

    public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) {
        return null;
    }

    public List<String> getDepartCities() {
        return null;
    }

    public List<String> getArrivalCities(String from) {
        return null;
    }

    public List<Ride> getRides(String from, String to, Date date) {
        return null;
    }

    public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
        return null;
    }

    public boolean isRegistered(String erab, String passwd) {
        return false;
    }

    public boolean addDriver(String username, String password) {
        return false;
    }

    public void initializeDB() {
    }
}
