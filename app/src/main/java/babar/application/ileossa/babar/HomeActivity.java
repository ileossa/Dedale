package babar.application.ileossa.babar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import babar.application.ileossa.babar.camera.PhotoIntentActivity;
import babar.application.ileossa.babar.gallery.SpaceGalleryActivity;
import babar.application.ileossa.babar.uploadGallery.UploadGalleryMainActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_gallery);
                    startActivity(new Intent(HomeActivity.this, SpaceGalleryActivity.class));
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_camera);
                    startActivity(new Intent(HomeActivity.this, PhotoIntentActivity.class));
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_upload);
                    startActivity(new Intent(HomeActivity.this, UploadGalleryMainActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




}
















