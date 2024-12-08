package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dataAccess.HibernateDataAccess;
import domain.Ride;
import domain.Driver;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;


public class BLFacadeImplementation  implements BLFacade {
	HibernateDataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager = new HibernateDataAccess();
	}
	
    public BLFacadeImplementation(HibernateDataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;
	}
    
    /**
     * {@inheritDoc}
     */
    public List<String> getDepartCities() {
		dbManager.open();
		List<String> departLocations = dbManager.getDepartCities();
		dbManager.close();
		return departLocations;
    }

    /**
     * {@inheritDoc}
     */
	public List<String> getDestinationCities(String from) {
		dbManager.open();
		List<String> targetCities = dbManager.getArrivalCities(from);
		dbManager.close();
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException {
		dbManager.open();
		Ride ride = dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride> rides = dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date> dates = dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}

	public boolean isRegistered(String erab, String passwd) {
		dbManager.open();
		boolean registered = dbManager.isRegistered(erab, passwd);
		dbManager.close();
		return registered;
	}

	public boolean addDriver(String username, String password, String name) {
		dbManager.open();
		boolean added = dbManager.addDriver(username, password, name);
		dbManager.close();
		return added;
	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeBD() {
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
}

