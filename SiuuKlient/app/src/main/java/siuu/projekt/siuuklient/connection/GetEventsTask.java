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
import siuu.projekt.siuuklient.preferences.EventDto;

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
            List<EventDto> requestBody = new LinkedList<EventDto>();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventDto[] preferences = restTemplate.getForObject(url, EventDto[].class);
            List<EventDto> eventDtos = new ArrayList<>();
            for (EventDto e : preferences){
                eventDtos.add(e);
            }
            map.onEventsUpdate(eventDtos);

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
