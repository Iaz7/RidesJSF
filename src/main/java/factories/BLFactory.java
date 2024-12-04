package factories;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class BLFactory {

    private BLFactory() {}

    private static BLFacade bl;

    public static BLFacade getFacade() {
        if (bl == null) {
            DataAccess da = new DataAccess();
            bl = new BLFacadeImplementation(da);
        }
        return bl;
    }
}
