package siuu.projekt.siuuklient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

import siuu.projekt.siuuklient.connection.RegisterUserTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText serverAddress;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.button);
        serverAddress = (EditText) findViewById(R.id.editText);
        userName = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ApplicationUtils.user = new User(userName.getText().toString(), password.getText().toString(), new Location(), new HashSet<PreferedActivity>());
        ApplicationUtils.SERV_ADDR = serverAddress.getText().toString();
        User user = ApplicationUtils.user;
        user.setId(userName.getText().toString());
        new RegisterUserTask(ApplicationUtils.user).execute();

        //set password
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
