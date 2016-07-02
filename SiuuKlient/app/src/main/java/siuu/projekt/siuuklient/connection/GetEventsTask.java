package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.map.LeafletConnectorImpl;
import siuu.projekt.siuuklient.preferences.Event;

/**
 * Created by domin4815 on 28.06.16.
 */
public class GetEventsTask extends AsyncTask<Void, Void, Void> {
    private LeafletConnectorImpl map;

    public GetEventsTask(LeafletConnectorImpl map) {
        this.map = map;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String url= ApplicationUtils.SERV_ADDR + "/events";
            List<Event> requestBody = new LinkedList<Event>();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Event[] preferences = restTemplate.getForObject(url, Event[].class);
            List<Event> events = new ArrayList<>();
            for (Event e : preferences){
                events.add(e);
            }
            map.onEventsUpdate(events);

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
