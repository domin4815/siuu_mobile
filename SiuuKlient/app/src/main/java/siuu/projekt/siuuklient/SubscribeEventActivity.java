package siuu.projekt.siuuklient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import siuu.projekt.siuuklient.connection.SubscribeEventTask;
import siuu.projekt.siuuklient.preferences.EventDto;

public class SubscribeEventActivity extends AppCompatActivity implements View.OnClickListener{

    private EventDto event;

    private TextView desc;
    private TextView startDate;
    private Button subButton;
    private TextView subs;
    private TextView subsList;

    private DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd hh:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        desc = (TextView) findViewById(R.id.desc);
        startDate = (TextView) findViewById(R.id.whenEvent);
        subButton = (Button) findViewById(R.id.subscribe);
        subs = (TextView) findViewById(R.id.subs);
        subsList = (TextView) findViewById(R.id.subsList);


        subButton.setOnClickListener(this);
        if (extras != null) {
            String eventIdHash = extras.getString("EVENT");
            for (EventDto e : ApplicationUtils.repository.getEvents()){
                if (Integer.parseInt(eventIdHash) == e.getId().hashCode()){
                    event = e;
                    break;
                }
            }
            if (event != null){
                desc.setText(event.getCategory()+ ": "+event.getComment());
                if (event.getStartTimeDate() != null){
                    startDate.setText(dateFormat.format(event.getStartTimeDate()));
                }
                if (event.getParticipants() != null){
                    subs.setText(""+event.getParticipants().size());
                    String subs = "";
                    for (String s : event.getParticipants()){
                        subs += s + ",\n";
                    }
                    subsList.setText(subs);

                } else {
                    subs.setText("0");

                }
               // startDate.setText(""+ event.getStartTimeDate().toString());
            }

        }
    }

    @Override
    public void onClick(View v) {
        new SubscribeEventTask(event).execute();
        finish();
    }
}
