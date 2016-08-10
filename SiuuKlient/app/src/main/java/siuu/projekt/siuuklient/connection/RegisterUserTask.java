package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.User;

/**
 * Created by prolativ on 6/4/16.
 */
public class RegisterUserTask extends AsyncTask<Void, Void, Void> {
    private User user;

    public RegisterUserTask(User user) {
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            final String url = ApplicationUtils.SERV_ADDR + "/user";
            User requestBody = user;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url, requestBody, User.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
