package siuu.projekt.siuuklient.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.util.List;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.NewEventActivity;
import siuu.projekt.siuuklient.User;
import siuu.projekt.siuuklient.preferences.Event;

/**
 * Created by domin4815 on 15.05.16.
 */
public class LeafletConnectorImpl {

    private WebView map;
    private Activity activity;

    public LeafletConnectorImpl(Activity activity, WebView map) {
        this.map = map;
        this.activity = activity;
    }

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

    public void onOtherUsersUpdate(final List<User> others) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:removeAllUsersMarkers()");

                for (User u : others) {
                    if (u.getId().equals(ApplicationUtils.user.getId())){
                        continue;
                    }
                    map.loadUrl("javascript:addMarker("+u.getLocation().getLat()+
                            ", "+u.getLocation().getLon()+", '"+u.getId()+"', 'null', 'USER')");
                }

            }
        });
    }

    public void onEventsUpdate(final List<Event> others) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                map.loadUrl("javascript:removeAllEventsMarkers()");
                for (Event u : others) {

                    map.loadUrl("javascript:addMarker("+u.getLocation().getLat()+
                            ", "+u.getLocation().getLon()+", '"+u.getId()+"', '"
                            +"Category: "+u.getCategory()+"<br>"+u.getName()+u.getComment()+"', 'EVENT')");
                }


            }
        });
    }

    /*
* JavaScript Interface. Web code can access methods in here
* (as long as they have the @JavascriptInterface annotation)
*/
    @JavascriptInterface
    public String mapClicked(String message){
        System.out.println(message);
        Intent intent = new Intent(activity, NewEventActivity.class);
        activity.startActivity(intent);
        return null;
    }

    @JavascriptInterface
    public String eventClicked(String message){
        System.out.println(message);
        Intent intent = new Intent(activity, NewEventActivity.class);
        activity.startActivity(intent);
        return null;
    }

}
