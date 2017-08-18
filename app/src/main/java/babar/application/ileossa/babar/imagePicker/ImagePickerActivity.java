package babar.application.ileossa.babar.imagePicker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import babar.application.ileossa.babar.R;

/**
 * Created by ileossa on 18/08/2017.
 */

public class ImagePickerActivity extends AppCompatActivity {
    private final int photoSelected = 1;
    private static ImageView imageGallery;
    private static TextView uriPath, realPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        imageGallery = (ImageView) findViewById(R.id.gallery_imageview);
        uriPath = (TextView) findViewById(R.id.uri_path);
        realPath = (TextView) findViewById(R.id.real_path);
        findViewById(R.id.change_image).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Call intent gallery
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivity(intent);
                    }
                }
        );
    }


    protected void onActivityResult(int request, int result, Intent returnImageToIntent){
        super.onActivityResult(request, result, returnImageToIntent);
        switch (request){
            case photoSelected:
                if(result == RESULT_OK){
                    try{
                        Uri imageUri = returnImageToIntent.getData();
                        uriPath.setText("URI path: " + imageUri.toString());
                        String fullPathImage = getRealPathFromURI(ImagePickerActivity.this, imageUri);
                        realPath.setText("Real Path: " + fullPathImage);

                        uriPath.setVisibility(View.VISIBLE);
                        realPath.setVisibility(View.VISIBLE);
                        // call static method
                        Bitmap bitmap = decodeUri(ImagePickerActivity.this, imageUri, 300);

                        if(bitmap != null){
                            imageGallery.setImageBitmap(bitmap);
                        }else{
                            Toast.makeText(ImagePickerActivity.this, "Error while decoding image", Toast.LENGTH_LONG).show();
                        }
                    }catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(ImagePickerActivity.this, "File not found", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }



    public static String getRealPathFromURI(Context context, Uri uri){
        Cursor cursor = null;
        try{
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, proj, null, null, null, null);
            int columId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columId);
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }


    /**
     * Prevent Exception by large image scale. The method compress and scale image
     * @param context
     * @param uri
     * @param requiredSize
     * @return
     * @throws FileNotFoundException
     */
    static Bitmap decodeUri(Context context, Uri uri,
                            final int requiredSize) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(uri), null, o2);
    }

}
