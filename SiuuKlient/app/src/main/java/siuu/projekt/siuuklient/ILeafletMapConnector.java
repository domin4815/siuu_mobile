package siuu.projekt.siuuklient;

import android.location.Location;

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



}
