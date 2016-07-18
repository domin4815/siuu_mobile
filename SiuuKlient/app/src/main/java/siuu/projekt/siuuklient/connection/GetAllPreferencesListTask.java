package siuu.projekt.siuuklient.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

import siuu.projekt.siuuklient.ApplicationUtils;

/**
 * Created by domin4815 on 28.06.16.
 */
public class GetAllPreferencesListTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String url= ApplicationUtils.SERV_ADDR + "/categories";
            List<String> requestBody = new LinkedList<String>();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String[] preferences = restTemplate.getForObject(url, String[].class);
            ApplicationUtils.allPreferencesList.clear();
            for (int i = 0; i < preferences.length; i++){
                ApplicationUtils.allPreferencesList.add(preferences[i]);
            }

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
