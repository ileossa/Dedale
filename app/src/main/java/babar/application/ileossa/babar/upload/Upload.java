package babar.application.ileossa.babar.upload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import babar.application.ileossa.babar.R;
import babar.application.ileossa.babar.shareData.ShareIntent;

import static android.R.attr.data;
import static android.R.attr.switchMinWidth;

/**
 * Created by ileossa on 18/08/2017.
 */

public class Upload extends AppCompatActivity implements View.OnClickListener {

    private static Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        // init button comportement
        btnUpload = (Button) findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                //call service upload
                UploadService uploadService = new UploadService();
//                uploadService.uploadImage();
                break;

        }
    }
}
