package siuu.projekt.siuuklient;

import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.LinkedList;
import java.util.List;

public class NewEventActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText desc;
    private EditText fromDate;
    private EditText fromTime;
    private EditText toDate;
    private EditText toTime;
    private EditText min;
    private EditText max;
    private TextView latText;
    private TextView lonText;
    private Spinner category;
    private Button createl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        desc = (EditText) findViewById(R.id.desc);
        desc.setText("...");
        fromDate = (EditText) findViewById(R.id.from);
        fromTime = (EditText) findViewById(R.id.fromTime);

        toDate = (EditText) findViewById(R.id.to);
        toTime = (EditText) findViewById(R.id.toTimeEdit);

        min = (EditText) findViewById(R.id.minNum);
        max = (EditText) findViewById(R.id.maxNum);

        latText = (TextView) findViewById(R.id.latText);
        lonText = (TextView) findViewById(R.id.lon);

        category = (Spinner) findViewById(R.id.spinner);

        Bundle extras = getIntent().getExtras();
        String userName;
        createl = (Button) findViewById(R.id.button2);
        createl.setOnClickListener(this);

        if (extras != null) {
            String latStr = extras.getString("LAT");
            String lonStr = extras.getString("LON");
            latText.setText(latStr);
            lonText.setText(lonStr);
        }

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

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }
}
