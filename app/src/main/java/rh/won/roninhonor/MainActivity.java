package rh.won.roninhonor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.adjust.sdk.webbridge.AdjustBridge;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class MainActivity extends AppCompatActivity {

    private GlobalWebView webapp;
    private FirebaseRemoteConfig remoteConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalWebView contentView = findViewById(R.id.web_view);



        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();


        remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.fetchAndActivate().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d("FirebaseCFG", "Loading Successful");

                GlobalConfig.urlAPI = remoteConfig.getString("urlAPI");
                GlobalConfig.gameURL = GlobalConfig.urlAPI + "?appid=" + GlobalConfig.appCode;
                contentView.loadUrl(GlobalConfig.gameURL);

                AdjustBridge.registerAndGetInstance(getApplication(), contentView);

                // Continue with your code here, u  sing the 'endPoint' variable
                // For example, you can make network requests or load the URL into 'webapp'
            } else {
                Log.e("FirebaseCFG", "Loading Failed", task.getException());
                Toast.makeText(this, "Failed to fetch remote config", Toast.LENGTH_LONG).show();
            }
        });


    }
}
