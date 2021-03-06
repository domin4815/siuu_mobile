package siuu.projekt.siuuklient;


import java.util.Set;

import siuu.projekt.siuuklient.model.Location;
import siuu.projekt.siuuklient.preferences.PreferedActivity;

public class User {
    private String id;
    private String name;
    private Location location;
    private Set<PreferedActivity> preferedActivities;

    public User() {}

    public User(String id, String name, Location location, Set<PreferedActivity> preferedActivities) {
        this.id = name;
        this.name = name;

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

    public Set<PreferedActivity> getPreferedActivities() {
        return preferedActivities;
    }

    public void setPreferedActivities(Set<PreferedActivity> preferedActivities) {
        this.preferedActivities = preferedActivities;
    }
}
