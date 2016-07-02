package siuu.projekt.siuuklient.connection;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;
import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.map.LeafletConnectorImpl;

public class UpdatesRequester {
    private Timer timer = new Timer();
    private HttpConnector connector = new HttpConnector();
    private LeafletConnectorImpl map;

    public UpdatesRequester(Context parent, LeafletConnectorImpl leafletMap){
        this.map = leafletMap;
        timer.scheduleAtFixedRate(new CyclicUpdatesTask(),
                                    5000,
                                    ApplicationUtils.UPDATES_INTERVAL);
    }

    public void stop(){
        timer.cancel();
        timer.purge();
    }

    private class CyclicUpdatesTask extends TimerTask {

        @Override
        public void run() {
            //new UpdateLocationTask(ApplicationUtils.user).execute();
           // new FindMatchingUsersTask(ApplicationUtils.user, 10000, map).execute();
          //  new GetAllPreferencesList().execute();
            new GetEventsTask(map).execute();
        }

    }
}
