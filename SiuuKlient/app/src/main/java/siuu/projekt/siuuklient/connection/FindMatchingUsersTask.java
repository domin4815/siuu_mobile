package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.preferences.PreferedActivity;
import siuu.projekt.siuuklient.User;
import siuu.projekt.siuuklient.map.LeafletConnectorImpl;

/**
 * Created by prolativ on 6/3/16.
 */
public class FindMatchingUsersTask extends AsyncTask<Void, Void, Void> {
    private User user;
    private double distance;
    private LeafletConnectorImpl map;

    public FindMatchingUsersTask(User user, double distance, LeafletConnectorImpl map) {
        this.user = user;
        this.distance = distance;
        this.map = map;
    }



    @Override
    protected Void doInBackground(Void... params) {
        try {
            String url= UriComponentsBuilder.fromUriString(ApplicationUtils.SERV_ADDR)
                    .path("/users/find")
                    .queryParam("lon", user.getLocation().getLon() + "")
                    .queryParam("lat", user.getLocation().getLat() + "")
                    .queryParam("dist", distance + "")
                    .build()
                    .toString();
            List<String> requestBody = new LinkedList<String>();
            for(PreferedActivity activity: user.getPreferedActivities()){
                requestBody.add(activity.getCategory());
            }

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            User[] matchingUsers = restTemplate.postForObject(url, requestBody, User[].class);
            map.onOtherUsersUpdate(Arrays.asList(matchingUsers));

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
