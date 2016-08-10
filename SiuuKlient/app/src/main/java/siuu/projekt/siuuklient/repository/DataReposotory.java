package siuu.projekt.siuuklient.repository;

import java.util.ArrayList;
import java.util.List;

import siuu.projekt.siuuklient.preferences.EventDto;

/**
 * Created by domin4815 on 30.07.16.
 *
 */

public class DataReposotory {

    private final List<EventDto> events = new ArrayList<>();

    public List<EventDto> getEvents() {
        return events;
    }

}
