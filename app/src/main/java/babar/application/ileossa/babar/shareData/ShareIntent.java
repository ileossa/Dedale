package babar.application.ileossa.babar.shareData;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import babar.application.ileossa.babar.R;

/**
 * Created by ileossa on 18/08/2017.
 */

public class ShareIntent extends AppCompatActivity implements View.OnClickListener {
    private static Button selectImage, shareImage, shareText;
    private static ImageView imageView;
    private static EditText textToShare;
    private static Uri imageUri = null;
    private final int selecPhoto = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_intent);
        init();
        setListener();
    }

    private void setListener() {
        selectImage.setOnClickListener(this);
        shareImage.setOnClickListener(this);
        shareText.setOnClickListener(this);

    }

    private void init() {
        selectImage = (Button) findViewById(R.id.select_image);
        shareImage = (Button) findViewById(R.id.share_image);
        shareText = (Button) findViewById(R.id.btnTextShare);
        imageView = (ImageView) findViewById(R.id.share_imageview);
        textToShare = (EditText) findViewById(R.id.text_share);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_image:
                // Intent to gallery
                Intent in = new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivityForResult(in, selecPhoto);
                break;
            case R.id.share_image:
                // share image
                shareImage(imageUri);
                break;
            case R.id.btnTextShare:
                // Share text
                String getText = textToShare.getText().toString();
                if (!getText.equals("") && getText.length() != 0)
                    shareText(getText);
                else
                    Toast.makeText(ShareIntent.this,"Please enter something to share.", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void shareImage(Uri imagePath) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sharingIntent.setType("image/*");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
        startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));
    }


    private void shareText(String text) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");// Plain format text

        // You can add subject also
		/*
		 * sharingIntent.putExtra( android.content.Intent.EXTRA_SUBJECT,
		 * "Subject Here");
		 */
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharingIntent, "Share Text Using"));
    }
}
