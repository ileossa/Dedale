package babar.application.ileossa.babar.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Space;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import babar.application.ileossa.babar.MainActivity;
import babar.application.ileossa.babar.R;
import babar.application.ileossa.babar.camera.PhotoIntentActivity;
import babar.application.ileossa.babar.json.HttpHandler;
import babar.application.ileossa.babar.uploadGallery.UploadGalleryMainActivity;


/**
 * Created by ileossa on 16/08/2017.
 */
public class SpaceGalleryActivity extends AppCompatActivity{

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog progressDialog;
    private String url = "http://81b125bf.ngrok.io/v1/all";

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_gallery:
//                    mTextMessage.setText(R.string.title_gallery);
//                    startActivity(new Intent(SpaceGalleryActivity.this, SpaceGalleryActivity.class));
                    return true;
                case R.id.navigation_camera:
//                    mTextMessage.setText(R.string.title_camera);
                    startActivity(new Intent(SpaceGalleryActivity.this, PhotoIntentActivity.class));
                    return true;
                case R.id.navigation_upload:
//                    mTextMessage.setText(R.string.title_upload);
                    startActivity(new Intent(SpaceGalleryActivity.this, UploadGalleryMainActivity.class));
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_spacec_gallery);

        //menu bottom
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        new GetInfosFromJson().execute();

    }




    private class GetInfosFromJson extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showing progress dialog
            progressDialog = new ProgressDialog(SpaceGalleryActivity.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            HttpHandler httpHandler = new HttpHandler();

            String jsonStr = httpHandler.makeServiceCall(url);
            Log.i(TAG, "Response: " + jsonStr);
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String  result) {
            List<DataInfos> datas = new ArrayList<>();
            progressDialog.dismiss();
            if (result != null) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // Getting JSON Array
                        JSONObject c = jsonArray.getJSONObject(i);

                        // Storing  JSON item in a Variable
                        DataInfos dataInfos = new DataInfos();
                        dataInfos.setId(c.getInt("id"));
                        dataInfos.setName(c.getString("name"));
                        dataInfos.setTag(c.getString("tag"));
                        dataInfos.setClasspath(c.getString("classpath"));
                        datas.add(dataInfos);
                    }

                    // Setup and push data to recylerview
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SpaceGalleryActivity.this, 2);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);

                    SpaceGalleryActivity.ImageGalleryAdapter adapter = new SpaceGalleryActivity.ImageGalleryAdapter(SpaceGalleryActivity.this, datas);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.e("JSONException", e.toString());
                }
            }

        }
    }




    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        private List<DataInfos> listDatasInfos;
        private Context mContext;


        public ImageGalleryAdapter( Context mContext, List<DataInfos> listDatasInfos) {
            this.listDatasInfos = listDatasInfos;
            this.mContext = mContext;
        }


        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the layout
            View photoView = inflater.inflate(R.layout.gallery_item_photo, parent, false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            DataInfos datasInfo = listDatasInfos.get(position);
            ImageView imageView = holder.mPhotoImageView;

            Glide.with(mContext)
                    .load(datasInfo.getClasspath())
                    .placeholder(R.drawable.ic_cloud_off_red)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (listDatasInfos.size());
        }


        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    DataInfos dataInfos = listDatasInfos.get(position);

                    Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                    intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, dataInfos);
                    startActivity(intent);
                }
            }
        }
    }
}
