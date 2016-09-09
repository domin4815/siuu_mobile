package siuu.projekt.siuuklient.map;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.NewEventActivity;
import siuu.projekt.siuuklient.SubscribeEventActivity;
import siuu.projekt.siuuklient.User;
import siuu.projekt.siuuklient.dto.Venue;
import siuu.projekt.siuuklient.model.FacebookUser;
import siuu.projekt.siuuklient.preferences.EventDto;

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
                    String friendName = "Not my friend";
                    for (FacebookUser facebookUser : ApplicationUtils.friends){
                        if(facebookUser.getId().equals(u.getId())){
                            friendName = facebookUser.getName();
                            break;
                        }
                    }

                    map.loadUrl("javascript:addMarker("+u.getLocation().getLat()+
                            ", "+u.getLocation().getLon()+", '"+u.getId()+"', "+u.getName()+", 'USER')");
                }

            }
        });
    }
    public void onVenuesUpdate(final List<Venue> venues) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");
                map.loadUrl("javascript:removeAllVenues()");

                for (Venue u : venues) {
                    String venueId = u.getLat()+u.getLon()+"";

                    map.loadUrl("javascript:addMarker("+u.getLat()+
                            ", "+u.getLon()+", '"+venueId+"', '"
                            +u.getName() + " : "+u.getCategory()+"', 'VENUE')");

                }


            }
        });
    }

    public void onEventsUpdate(final List<EventDto> others1) {
        final List<EventDto> others = new LinkedList<>();
        Set<String> cats = ApplicationUtils.user.getCategories();

        for (EventDto u : others1) {

            if (cats.contains(u.getCategory())){
                others.add(u);
            }
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");
                map.loadUrl("javascript:removeAllEventsMarkers()");

                for (EventDto u : others) {
                    String description = "";
                    description += (u.getCategory() != null)  ? "Category: "+u.getCategory() : "";
                    description += (u.getComment() != null)  ? "<br> Description: "+u.getComment() : "";
                    description += (u.getStartTimeDate() != null)  ? "<br> Starts: "+dateFormat.format(u.getStartTimeDate()) : "";
                    description += (u.getEndTime() != null)  ? "<br> Ends: "+dateFormat.format(u.getEndTime()) : "";

                    map.loadUrl("javascript:addMarker("+u.getLocation().getLat()+
                            ", "+u.getLocation().getLon()+", '"+u.getId().hashCode()+"', '"
                            +description+"', 'EVENT')");

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
        String coords[] = message.split(" ");
        System.out.println(message);
        Intent intent = new Intent(activity, NewEventActivity.class);
        intent.putExtra("LAT", coords[0]);
        intent.putExtra("LON", coords[1]);
        activity.startActivity(intent);
        return null;
    }

    @JavascriptInterface
    public String eventClicked(String message){
        System.out.println(message);
        Intent intent = new Intent(activity, SubscribeEventActivity.class);
        intent.putExtra("EVENT", message);
        activity.startActivity(intent);
        return null;
    }

}
