package gerardo.com.urv.practica21.Models;

public class Location {
    private int id;
    private String city;
    private float latitude;
    private float longitude;
    private int postal_code;
    private String state_province;
    private String street_address;

    public Location (int id, String city, float latitude, float longitude, int postal_code, String state_province, String street_address){
        this.id = id;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postal_code = postal_code;
        this.state_province = state_province;
        this.street_address = street_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", postal_code=" + postal_code +
                ", state_province='" + state_province + '\'' +
                ", street_address='" + street_address + '\'' +
                '}';
    }
}
