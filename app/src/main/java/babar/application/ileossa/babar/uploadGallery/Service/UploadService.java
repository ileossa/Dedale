package babar.application.ileossa.babar.uploadGallery.Service;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by ileossa on 27/09/2017.
 */

public class UploadService {

    private static final String BASE_URL = "http://66de0217.ngrok.io/";

    private static final String TAG_UPLOAD_SERVICE = "UPLOAD SERVCIE";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void uploadFiles(ArrayList<String> selectedItems){
        Log.d(TAG_UPLOAD_SERVICE, "Ping server " + String.valueOf(pingServer(BASE_URL)));
        Log.d(TAG_UPLOAD_SERVICE, "toto caca vincent " + selectedItems.toString());
        for (String pathItem : selectedItems){
            String filename = pathItem.substring(pathItem.lastIndexOf("/")+1);

            File file = new File(pathItem);
            try {
                uploadImage(file, filename);
            } catch (IOException e) {
                Log.e(TAG_UPLOAD_SERVICE, "Cannot upload file " + filename + " from " + pathItem );
            }
        }
    }

    private boolean pingServer(String urlparam){
        try {
            URL url = new URL(urlparam);

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application:10");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
            urlc.connect();

            if (urlc.getResponseCode() == 200) {
                Log.d(TAG_UPLOAD_SERVICE, "getResponseCode == 200");
                return new Boolean(true);
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Boolean(false);
    }


    private void uploadImage(File image, String imageName) throws IOException {
        Log.d(TAG_UPLOAD_SERVICE, "Ping server " + String.valueOf(pingServer(BASE_URL)));
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", imageName, RequestBody.create(MEDIA_TYPE_PNG, image))
                .build();

        Request request = new Request.Builder().url(BASE_URL+"/v1/upload")
                .post(requestBody).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            Log.e(TAG_UPLOAD_SERVICE, "Error when upload file, unexpected code " + response.toString());
            throw new IOException("Unexpected code " + response);
        }

    }
}