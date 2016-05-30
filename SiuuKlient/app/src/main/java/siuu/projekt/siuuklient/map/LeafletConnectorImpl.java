package siuu.projekt.siuuklient.map;

import android.app.Activity;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.util.List;

import siuu.projekt.siuuklient.connection.dto.UserDto;
import siuu.projekt.siuuklient.map.ILeafletMapConnector;

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
    public void onOtherUsersUpdate(final List<UserDto> others) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:removeAllMarkers()");

                for (UserDto u : others){
                    map.loadUrl("javascript:addMarker("+u.getLat()+", "+u.getLng()+", "+u.getId()+", '"+u.getName()+"')");

                }

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
