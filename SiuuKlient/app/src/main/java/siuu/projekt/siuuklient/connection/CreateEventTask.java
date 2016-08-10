package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.preferences.EventDto;

public class CreateEventTask extends AsyncTask<Void, Void, Void> {
    private EventDto eventDto;

    public CreateEventTask(EventDto eventDto) {
        this.eventDto = eventDto;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            System.out.println("EEEE ");
            final String url = ApplicationUtils.SERV_ADDR + "/event";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, eventDto, EventDto.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
