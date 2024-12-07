package model;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named("driverMenu")
@SessionScoped
public class DriverMenuBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String driverName ="driver";

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
}