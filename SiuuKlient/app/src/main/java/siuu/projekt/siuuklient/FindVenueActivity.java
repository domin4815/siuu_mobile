package siuu.projekt.siuuklient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

import siuu.projekt.siuuklient.connection.GetEventsTask;
import siuu.projekt.siuuklient.connection.GetVenueDataTask;
import siuu.projekt.siuuklient.dto.VenueQueryData;
import siuu.projekt.siuuklient.map.LeafletMapFragment;

public class FindVenueActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner category;
    private Button search;
    private String spinnerCatSelected = "";
    private EditText radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_venue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        category = (Spinner) findViewById(R.id.cat);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        radius = (EditText) findViewById(R.id.editText2);

        List<String> prefs = new LinkedList<>();
        prefs.addAll(ApplicationUtils.allPreferencesList);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, prefs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(dataAdapter);
        category.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    @Override
    public void onClick(View v) {
        VenueQueryData vq = new VenueQueryData();
        vq.setLat(ApplicationUtils.user.getLocation().getLat());
        vq.setLon(ApplicationUtils.user.getLocation().getLon());
        vq.setCategory(spinnerCatSelected);
        vq.setDist(Double.parseDouble(radius.getText().toString()));
        new GetVenueDataTask(vq, LeafletMapFragment.leafletMap).execute();

        finish();
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            spinnerCatSelected = parent.getItemAtPosition(pos).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

            // TODO Auto-generated method stub
        }

    }

}
