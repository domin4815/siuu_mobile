package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.dto.Venue;
import siuu.projekt.siuuklient.dto.VenueQueryData;
import siuu.projekt.siuuklient.preferences.EventDto;

public class GetVenueDataTask extends AsyncTask<Void, Void, Void> {
    private VenueQueryData query;

    public GetVenueDataTask(VenueQueryData query) {
        this.query = query;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            final String url = ApplicationUtils.SERV_ADDR + "/venues";
            //todo
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //Venue[] venues = restTemplate.postForObject(url, query, VenueQueryData.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
