package siuu.projekt.siuuklient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import siuu.projekt.siuuklient.connection.CreateEventTask;
import siuu.projekt.siuuklient.model.Location;
import siuu.projekt.siuuklient.preferences.EventDto;

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
    private String spinnerCatSelected = "";

    private Calendar calendar = Calendar.getInstance();
    private DateFormat dateFormat  = new SimpleDateFormat("yyyy-mm-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        desc = (EditText) findViewById(R.id.desc);
        desc.setText("");
        fromDate = (EditText) findViewById(R.id.from);
        fromDate.setText(dateFormat.format(new Date()));
        fromTime = (EditText) findViewById(R.id.fromTime);
        fromTime.setText("12:00");

        toDate = (EditText) findViewById(R.id.to);
        toDate.setText(dateFormat.format(new Date()));

        toTime = (EditText) findViewById(R.id.toTimeEdit);
        toTime.setText("13:00");

        min = (EditText) findViewById(R.id.minNum);
        min.setText("2");
        max = (EditText) findViewById(R.id.maxNum);
        max.setText("10");

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


        EventDto eventDto = new EventDto();
        eventDto.setCategory(spinnerCatSelected);
        eventDto.setComment(desc.getText().toString());
        Date dateFrom = new Date();
        Date dateTo = new Date((new Date()).getTime() + 1000*60*1);
        //todo: set time
        try {
            dateFrom = dateFormat.parse(fromDate.getText().toString());
            dateTo = dateFormat.parse(toDate.getText().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateTo = new Date((dateTo).getTime() + 1000*60*1);
        eventDto.setMaxPeople(Integer.parseInt(max.getText().toString()));
        eventDto.setMinPeople(Integer.parseInt(min.getText().toString()));

        eventDto.setStartTime(dateFrom);
        eventDto.setEndTime(dateTo);
        Location location = new Location();
        location.setLat(Double.parseDouble(latText.getText().toString()));
        location.setLon(Double.parseDouble(lonText.getText().toString()));
        eventDto.setLocation(location);

        new CreateEventTask(eventDto).execute();
        finish();

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
    /*        Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();*/

            spinnerCatSelected = parent.getItemAtPosition(pos).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

            // TODO Auto-generated method stub
        }

    }
}
