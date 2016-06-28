package siuu.projekt.siuuklient;

public class Location {
    private double lon = 0.0;
    private double lat = 0.0;

    public Location() {}

    public Location(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}