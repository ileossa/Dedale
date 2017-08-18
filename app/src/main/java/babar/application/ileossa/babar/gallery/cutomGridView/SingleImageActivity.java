package babar.application.ileossa.babar.gallery.cutomGridView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import babar.application.ileossa.babar.R;

/**
 * Created by ileossa on 18/08/2017.
 */

public class SingleImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        //get intent from customGridView
        int imagePath = getIntent().getIntExtra("image", 0);
        ImageView imageView = (ImageView) findViewById(R.id.singleImage);
        imageView.setBackgroundResource(imagePath);
    }
}
