package dataAccess;

import domain.ChatMessage;
import domain.Driver;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HibernateDataAccess {

    EntityManager em;

    public void open() {
        em = JPAUtil.getEntityManager();
    }

    public void close() {
        em.close();
    }

    public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideAlreadyExistException, RideMustBeLaterThanTodayException {
        try {
            if(new Date().compareTo(date)>0) {
                throw new RideMustBeLaterThanTodayException("Ride must be later than today");
            }

            em.getTransaction().begin();
            Driver driver = em.find(Driver.class, driverEmail);

            if (driver.doesRideExists(from, to, date)) {
                em.getTransaction().commit();
                throw new RideAlreadyExistException("Ride already exists");
            }

            Ride ride = driver.addRide(from, to, date, nPlaces, price);

            em.persist(driver);

            em.getTransaction().commit();
            return ride;
        } catch (NullPointerException | PersistenceException e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        }
    }

    public List<String> getDepartCities() {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
        return query.getResultList();
    }

    public List<String> getArrivalCities(String from) {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
        query.setParameter(1, from);
        return query.getResultList();
    }

    public List<Ride> getRides(String from, String to, Date date) {
        TypedQuery<Ride> query = em.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);
        query.setParameter(1, from);
        query.setParameter(2, to);
        query.setParameter(3, date);
        return query.getResultList();
    }

    public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
        return null;
    }

    public boolean isRegistered(String username, String password) {
        TypedQuery<Long> travelerQuery = em.createQuery("SELECT COUNT(d) FROM Driver d WHERE d.email = :username AND d.password = :password", Long.class);
        travelerQuery.setParameter("username", username);
        travelerQuery.setParameter("password", password);
        return travelerQuery.getSingleResult() > 0;
    }

    public boolean addDriver(String username, String password, String name) {
        boolean added = false;
        try {
            em.getTransaction().begin();
            if (em.find(Driver.class, username) == null) {
                Driver driver = new Driver(username, password, name);
                em.persist(driver);
                added = true;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return added;
    }

    public List<Ride> getRidesByDriver(String driverEmail) {
        TypedQuery<Ride> query = em.createQuery("SELECT r FROM Ride r WHERE r.driver.email=?1",Ride.class);
        query.setParameter(1, driverEmail);
        return query.getResultList();
    }

    public List<ChatMessage> getMessages() {
        TypedQuery<ChatMessage> query = em.createQuery("SELECT m FROM ChatMessage m ORDER BY m.date ASC", ChatMessage.class);
        return query.getResultList();
    }

    public boolean sendMessage(String senderEmail, String message)
    {
        boolean sent = false;
        try {
            em.getTransaction().begin();
            Driver sender = em.find(Driver.class, senderEmail);
            if (sender != null) {
                ChatMessage chatMessage = new ChatMessage(sender, message);
                sender.getSentMessages().add(chatMessage);
                em.persist(chatMessage);
                sent = true;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return sent;
    }

    public void initializeDB() {
    }
}
