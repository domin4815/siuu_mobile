package siuu.projekt.siuuklient;


import java.util.List;

public class User {
    private String id;
    private String name;
    private Location location;
    private List<PreferedActivity> preferedActivities;

    public User() {}

    public User(String id, String name, Location location, List<PreferedActivity> preferedActivities) {
        this.id = id;

        this.location = location;
        this.preferedActivities = preferedActivities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<PreferedActivity> getPreferedActivities() {
        return preferedActivities;
    }

    public void setPreferedActivities(List<PreferedActivity> preferedActivities) {
        this.preferedActivities = preferedActivities;
    }
}
