package ru.blizzed.opensongkick.models;

public class Venue extends BaseVenue {

    private City city;
    private String street;
    private String zip;
    private String phone;
    private String website;
    private int capacity;
    private String description;

    public City getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }
}
