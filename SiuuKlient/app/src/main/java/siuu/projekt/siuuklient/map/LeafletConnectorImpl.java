package siuu.projekt.siuuklient.map;

import android.app.Activity;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.util.List;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.User;

/**
 * Created by domin4815 on 15.05.16.
 */
public class LeafletConnectorImpl implements ILeafletMapConnector {

    private WebView map;
    private Activity activity;

    public LeafletConnectorImpl(Activity activity, WebView map) {
        this.map = map;
        this.activity = activity;
    }

    @Override
    public void onLocationFound(final Location location) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:onLocationFound("
                    + location.getLatitude()
                    + "," + location.getLongitude()
                    + "," + location.getAccuracy()
                    + ")");

            }
        });

    }

    @Override
    public void onOtherUsersUpdate(final List<User> others) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:removeAllMarkers()");

                for (User u : others) {
                    map.loadUrl("javascript:addMarker(" + u.getLocation().getLat() + ", " + u.getLocation().getLon() + ", '" + u.getId() + "', '" + u.getName() + ", 'USER'')");

                }

            }
        });
    }

    @Override
    public void onFirstLocation(final siuu.projekt.siuuklient.Location location) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:onLocationFound("
                        + location.getLat()
                        + "," + location.getLon()
                        + "," + 20
                        + ")");

            }
        });
    }

    /*
* JavaScript Interface. Web code can access methods in here
* (as long as they have the @JavascriptInterface annotation)
*/
    @JavascriptInterface
    public String makeToast(String message, String lengthLong){
        return null;
    }

}
