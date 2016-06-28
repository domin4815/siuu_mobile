package siuu.projekt.siuuklient.map;

import android.location.Location;

import java.util.List;

import siuu.projekt.siuuklient.User;

/**
 * Android application <-> Leaflet map communication interface
 *
 */

public interface ILeafletMapConnector {

    /**
     *
     * @param location passes current location from Android app to Leaflet
     */
    void onLocationFound(Location location);

    void onOtherUsersUpdate(List<User> others);

    void onFirstLocation(siuu.projekt.siuuklient.Location location);




}
