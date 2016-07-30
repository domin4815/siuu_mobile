package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.preferences.PreferedActivity;
import siuu.projekt.siuuklient.User;

/**
 * Created by prolativ on 6/3/16.
 */
public class UpdatePreferedActivitiesTask extends AsyncTask<Void, Void, Void> {
    private User user;

    public UpdatePreferedActivitiesTask(User user){
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String url = ApplicationUtils.SERV_ADDR + "/user/{id}/activities";
            Map<String, String> urlParams = new HashMap<String, String>();
            urlParams.put("id", user.getId());
            Set<PreferedActivity> requestBody = user.getPreferedActivities();
            RestTemplate restTemplate = new RestTemplate();
            Log.i("", "sending update");
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, requestBody, Void.class, urlParams);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
