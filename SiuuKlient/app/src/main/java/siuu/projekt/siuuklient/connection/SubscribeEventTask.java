package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.preferences.EventDto;

public class SubscribeEventTask extends AsyncTask<Void, Void, Void> {
    private EventDto eventDto;

    public SubscribeEventTask(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            final String url = ApplicationUtils.SERV_ADDR + "/subscribe";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, eventDto, EventDto.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
