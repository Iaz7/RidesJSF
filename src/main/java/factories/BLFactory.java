package factories;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.HibernateDataAccess;

public class BLFactory {

    private BLFactory() {}

    private static BLFacade bl;

    public static BLFacade getFacade() {
        if (bl == null) {
            HibernateDataAccess da = new HibernateDataAccess();
            bl = new BLFacadeImplementation(da);
        }
        return bl;
    }
}
