package babar.application.ileossa.babar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import babar.application.ileossa.babar.gallery.CustomGridView;
import babar.application.ileossa.babar.gallery.Gallerie;
import babar.application.ileossa.babar.imagePicker.ImagePickerActivity;


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

        Button showImagePicker = (Button) findViewById(R.id.btn_show_picker_image);
        showImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(MainActivity.this, ImagePickerActivity.class);
                startActivity(galleryIntent);
            }
        });


    }
}