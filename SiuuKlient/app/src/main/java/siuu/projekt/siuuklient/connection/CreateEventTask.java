package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.User;
import siuu.projekt.siuuklient.preferences.Event;

public class CreateEventTask extends AsyncTask<Void, Void, Void> {
    private Event event;

    public CreateEventTask(Event event) {
        this.event = event;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            final String url = ApplicationUtils.SERV_ADDR + "/event";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, event, Event.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
