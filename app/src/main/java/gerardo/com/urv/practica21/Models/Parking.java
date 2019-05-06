package gerardo.com.urv.practica21.Models;

import java.util.Arrays;
import java.util.List;

public class Parking {
    private int id;
    private Location location;
    private int company_number;
    private String name;
    private List<Floor> floors;

    public Parking (int id, Location location, int company_number, String name, List<Floor> floors){
        this.id = id;
        this.location = location;
        this.company_number = company_number;
        this.name = name;
        this.floors = floors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", location=" + location.toString() +
                ", company_number=" + company_number +
                ", name='" + name + '\'' +
                ", floors=" + floors +
                '}';
    }
}
