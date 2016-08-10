package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.model.Location;
import siuu.projekt.siuuklient.User;

/**
 * Created by prolativ on 6/3/16.
 */
public class UpdateLocationTask extends AsyncTask<Void, Void, Void> {
    private User user;

    public UpdateLocationTask(User user) {
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String url = ApplicationUtils.SERV_ADDR + "/user/{id}/location";
            Map<String, String> urlParams = new HashMap<String, String>();
            urlParams.put("id", user.getId());
            Location requestBody = user.getLocation();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, requestBody, Void.class, urlParams);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
