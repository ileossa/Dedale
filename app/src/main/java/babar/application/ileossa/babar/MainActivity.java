package babar.application.ileossa.babar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import babar.application.ileossa.babar.camera.PhotoIntentActivity;
import babar.application.ileossa.babar.gallery.CustomGridView;
import babar.application.ileossa.babar.gallery.Gallerie;
import babar.application.ileossa.babar.shareData.ShareIntent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showGalleryBtn = (Button) findViewById(R.id.btn_show_gallery);
        showGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(MainActivity.this, Gallerie.class);
                startActivity(galleryIntent);
            }
        });


        Button showCustomView = (Button) findViewById(R.id.btn_show_custom_view);
        showCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(MainActivity.this, CustomGridView.class);
                startActivity(galleryIntent);
            }
        });


        Button showShareIntent = (Button) findViewById(R.id.btn_show_share_intent);
        showShareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(MainActivity.this, ShareIntent.class);
                startActivity(galleryIntent);
            }
        });


        Button showCameraIntent = (Button) findViewById(R.id.btn_show_camera_intent);
        showCameraIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraActivity = new Intent(MainActivity.this, PhotoIntentActivity.class);
                startActivity(cameraActivity);
            }
        });


        Button homeActivity = (Button) findViewById(R.id.btn_show_home_activity);
        homeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeA = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeA);
            }
        });

    }
}