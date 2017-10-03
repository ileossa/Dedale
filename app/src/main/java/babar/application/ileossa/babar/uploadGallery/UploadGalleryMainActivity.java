package babar.application.ileossa.babar.uploadGallery;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

import babar.application.ileossa.babar.R;
import babar.application.ileossa.babar.camera.PhotoIntentActivity;
import babar.application.ileossa.babar.gallery.SpaceGalleryActivity;


public class UploadGalleryMainActivity extends AppCompatActivity implements View.OnClickListener {


    private static Button openCustomGallery;
    private static GridView selectedImageGridView;

    private static final int CustomGallerySelectId = 1;//Set Intent Id
    public static final String CustomGalleryIntentKey = "ImageArray";//Set Intent Key Value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_activity_main);

        //menu bottom
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initImageLoader(getApplicationContext());
        initViews();
        setListeners();
        getSharedImages();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openCustomGallery:
                //Start Custom Gallery Activity by passing intent id
                startActivityForResult(new Intent(UploadGalleryMainActivity.this, CustomGallery_Activity.class), CustomGallerySelectId);
                break;
        }
    }


    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_gallery:
//                    mTextMessage.setText(R.string.title_gallery);
                    startActivity(new Intent(UploadGalleryMainActivity.this, SpaceGalleryActivity.class));
                    return true;
                case R.id.navigation_camera:
//                    mTextMessage.setText(R.string.title_camera);
                    startActivity(new Intent(UploadGalleryMainActivity.this, PhotoIntentActivity.class));
                    return true;
                case R.id.navigation_upload:
//                    mTextMessage.setText(R.string.title_upload);
//                    startActivity(new Intent(UploadGalleryMainActivity.this, UploadGalleryMainActivity.class));
                    return true;
            }
            return false;
        }

    };


    //get actual path of uri
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    //Initiate Image Loader Configuration
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

    }

    // Call when i return from gallery picker
    protected void onActivityResult(int requestcode, int resultcode,
                                    Intent imagereturnintent) {
        super.onActivityResult(requestcode, resultcode, imagereturnintent);
        if(requestcode > 0){
            Toast.makeText(UploadGalleryMainActivity.this, "Upload complet", Toast.LENGTH_LONG).show();
            startActivity(new Intent(UploadGalleryMainActivity.this, SpaceGalleryActivity.class));
        }
        // if i can show all image selected uncommet
//        switch (requestcode) {
//            case CustomGallerySelectId:
//                if (resultcode == RESULT_OK) {
//                    String imagesArray = imagereturnintent.getStringExtra(CustomGalleryIntentKey);//get Intent data
//                    //Convert string array into List by splitting by ',' and substring after '[' and before ']'
//                    List<String> selectedImages = Arrays.asList(imagesArray.substring(1, imagesArray.length() - 1).split(", "));
//                    loadGridView(new ArrayList<String>(selectedImages));//call load gridview method by passing converted list into arrayList
//                }
//                break;
//
//        }
    }

    //Load GridView
    private void loadGridView(ArrayList<String> imagesArray) {
        GridView_Adapter adapter = new GridView_Adapter(UploadGalleryMainActivity.this, imagesArray, false);
        selectedImageGridView.setAdapter(adapter);
    }

    //Read Shared Images
    private void getSharedImages() {
        //If Intent Action equals then proceed
        if (Intent.ACTION_SEND_MULTIPLE.equals(getIntent().getAction())
                && getIntent().hasExtra(Intent.EXTRA_STREAM)) {
            ArrayList<Parcelable> list =
                    getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);//get Parcelabe list
            ArrayList<String> selectedImages = new ArrayList<>();

            //Loop to all parcelable list
            for (Parcelable parcel : list) {
                Uri uri = (Uri) parcel;//get URI
                String sourcepath = getPath(uri);//Get Path of URI
                selectedImages.add(sourcepath);//add images to arraylist
            }
            loadGridView(selectedImages);//call load gridview
        }
    }


    //Init all views
    private void initViews() {
        openCustomGallery = (Button) findViewById(R.id.openCustomGallery);
        selectedImageGridView = (GridView) findViewById(R.id.selectedImagesGridView);
    }

    //set Listeners
    private void setListeners() {
        openCustomGallery.setOnClickListener(this);
    }

}
