package siuu.projekt.siuuklient.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import siuu.projekt.siuuklient.ApplicationUtils;
import siuu.projekt.siuuklient.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button  = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText3);

        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer val = (Integer.parseInt(editText.getText().toString()));
                if (val<1000){
                    Toast.makeText(context, "You kidding me...", Toast.LENGTH_LONG).show();
                    return;
                }
                ApplicationUtils.UPDATES_INTERVAL = val;
            }
        });

    }

}
