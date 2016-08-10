package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.model.FacebookUser;
import siuu.projekt.siuuklient.TokenString;

public class GetFriendsTask extends AsyncTask<Void, Void, Void> {
    private String accessToken;

    public GetFriendsTask(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(accessToken != null) {
            try {
                String url= ApplicationUtils.SERV_ADDR + "/friends";
                TokenString requestBody = new TokenString(accessToken);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                FacebookUser[] friends = restTemplate.postForObject(url, requestBody, FacebookUser[].class);
                ApplicationUtils.friends.clear();
                for (int i = 0; i < friends.length; i++){
                    ApplicationUtils.friends.add(friends[i]);
                    System.out.println("friend: " + friends[i].toString());
                }

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }

        return null;
    }
}
