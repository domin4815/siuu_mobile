package siuu.projekt.siuuklient.dto;

/**
 * Created by domin4815 on 05.08.16.
 */
public class VenueQueryData {
    private double lon;
    private double lat;
    private double dist;
    private String category;

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

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
