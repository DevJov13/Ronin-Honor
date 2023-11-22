package rh.won.roninhonor;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private ImageView splash; // Declare the ImageView variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); // Replace "your_layout" with your actual layout XML file



        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        // Initialize the ImageView



        // Using a Handler to delay starting the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent); // Use 'intent' instead of 'Intent'
                finish();
            }
        }, 3000); // Delay for 3000 milliseconds (3 seconds)
    }
}
