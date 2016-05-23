package siuu.projekt.siuuklient;

import android.app.Activity;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by domin4815 on 15.05.16.
 */
public class LeafletConnectorImpl implements ILeafletMapConnector{

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

    /*
* JavaScript Interface. Web code can access methods in here
* (as long as they have the @JavascriptInterface annotation)
*/
    @JavascriptInterface
    public String makeToast(String message, String lengthLong){
        return null;
    }

}
