package siuu.projekt.siuuklient.preferences;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import siuu.projekt.siuuklient.Location;


public class EventDto {

    private String id;

    private String name;

    private String category;

    private Date startTime;

    private Date endTime;

    private Location location;

    private int maxPeople;

    private int minPeople;

    private String owner;

    private List<String> participants;

    private String comment;

    public EventDto() {
    }

    public EventDto(String id, String name, String category, Location location, int maxPeople, int minPeople, String owner, List<String> participants, String comment) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.maxPeople = maxPeople;
        this.minPeople = minPeople;
        this.owner = owner;
        this.participants = participants;
        this.comment = comment;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}