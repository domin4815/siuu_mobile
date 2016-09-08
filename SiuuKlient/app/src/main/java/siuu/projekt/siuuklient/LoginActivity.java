package siuu.projekt.siuuklient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.HashSet;

import siuu.projekt.siuuklient.connection.RegisterUserTask;
import siuu.projekt.siuuklient.model.Location;
import siuu.projekt.siuuklient.preferences.PreferedActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText serverAddress;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button logoutButton;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());


        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Button button = (Button) findViewById(R.id.button);
        serverAddress = (EditText) findViewById(R.id.editText);
        //button.setOnClickListener(this);

        final AccessToken accessToken = AccessToken.getCurrentAccessToken();

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                AccessToken.setCurrentAccessToken(accessToken);
                System.out.println("Token: " + accessToken.getToken());
                onLoggedIn(accessToken);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(accessToken != null && !accessToken.isExpired()) {
                    onLoggedIn(accessToken);
                }
            }
        });
    }

    /*@Override
    public void onClick(View v) {
        ApplicationUtils.user = new User(userName.getText().toString(), password.getText().toString(), new Location(), new HashSet<PreferedActivity>());
        ApplicationUtils.SERV_ADDR = serverAddress.getText().toString();
        User user = ApplicationUtils.user;
        user.setId(userName.getText().toString());
        new RegisterUserTask(ApplicationUtils.user).execute();

        //set password
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }*/

    public void onLoggedIn(AccessToken accessToken) {
        ApplicationUtils.user = new User(accessToken.getUserId(), accessToken.getUserId(), new Location(), new HashSet<PreferedActivity>());
        ApplicationUtils.SERV_ADDR = serverAddress.getText().toString();
        User user = ApplicationUtils.user;
        new RegisterUserTask(user).execute();

        Log.i("", "Token: " + accessToken.getToken());

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
