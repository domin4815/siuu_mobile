package siuu.projekt.siuuklient.connection;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.connection.dto.UserDto;
import siuu.projekt.siuuklient.map.ILeafletMapConnector;

public class UpdatesRequester {
    private Timer timer = new Timer();
    private HttpConnector connector = new HttpConnector();
    private ILeafletMapConnector map;

    public UpdatesRequester(Context parent, ILeafletMapConnector leafletMap){
        this.map = leafletMap;
        timer.scheduleAtFixedRate(new UpdatesTask(),
                                    5000,
                                    ApplicationUtils.UPDATES_INTERVAL);
    }

    public void stop(){
        timer.cancel();
        timer.purge();
    }

    private class UpdatesTask extends TimerTask {

        @Override
        public void run() {
            try {
                String response = connector.doPost(ApplicationUtils.SERV_ADDR, "/user"
                                +"?id="+ApplicationUtils.user.getId()
                                + "&lat="+ApplicationUtils.user.getLat()
                                + "&lng="+ApplicationUtils.user.getLng(), "");
                System.out.println(response);

            } catch (IOException e) {
                e.printStackTrace();
            }


            //Users position updates todo
/*            String response = connector.doGet(ApplicationUtils.SERV_ADDR + "/users");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<UserDto>>() {
            }.getType();
            List<UserDto> users = gson.fromJson(response, listType);*/
        }

    }
}
