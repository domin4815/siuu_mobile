package siuu.projekt.siuuklient.map;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.R;
import siuu.projekt.siuuklient.connection.UpdatesRequester;
import siuu.projekt.siuuklient.location.ILocationListener;
import siuu.projekt.siuuklient.map.LeafletConnectorImpl;


public class LeafletMapFragment extends Fragment implements ILocationListener {

    private View view;
    private WebView mapWebView;
    private LeafletConnectorImpl leafletMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_map3, container, false);
        mapWebView = (WebView) view.findViewById(R.id.webMapView);
        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        mapWebView.loadUrl("file:///android_asset/map.html");
        final Activity parent = getActivity();
        if (parent == null){
            //TODO: obsługa wyjątku
        }

        //Bind application with Leaflet map
        leafletMap = new LeafletConnectorImpl(parent, mapWebView);
        UpdatesRequester updatesRequester = new UpdatesRequester(getActivity(), leafletMap);
        mapWebView.addJavascriptInterface(leafletMap, "app");

        //leafletMap.onFirstLocation(ApplicationUtils.user.getLocation());


        return view;
    }

    @Override
    public void onLocationChanged(final android.location.Location location) {

        ApplicationUtils.user.getLocation().setLat(location.getLatitude());
        ApplicationUtils.user.getLocation().setLon(location.getLongitude());

        final Activity parent = getActivity();
        if (parent == null){
            return;
        }
        leafletMap.onLocationFound(location);

    }


}
