package babar.application.ileossa.babar.uploadGallery.Service;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import babar.application.ileossa.babar.shareData.ShareIntent;
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

    private static final boolean tryPing = true;
    private static final String BASE_URL_PING = " http://14717bf0.ngrok.io";
    private static final String BASE_URL_UPLOAD = BASE_URL_PING + "/v1/upload";

    private static final String TAG_UPLOAD_SERVICE = "UPLOAD SERVCIE";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void uploadFiles(ArrayList<String> selectedItems){
        Log.d(TAG_UPLOAD_SERVICE, "Ping server " + String.valueOf(pingServer(BASE_URL_PING)));
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

    private String  pingServer(String urlparam){
        if( tryPing == false){ return "ping desactive, please enable ping to test " + BASE_URL_PING; }
        try {
            URL url = new URL(urlparam);

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application:10");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
            urlc.connect();

            if (urlc.getResponseCode() == 200) {
                Log.d(TAG_UPLOAD_SERVICE, "getResponseCode == 200");
                return String.valueOf(new Boolean(true));
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(new Boolean(false));
    }


    private void uploadImage(File image, String imageName) throws IOException {
        Log.d(TAG_UPLOAD_SERVICE, "Ping server " + String.valueOf(pingServer(BASE_URL_PING)));
        MediaType MEDIA_TYPE = getFileExtension(image).endsWith("png") ? MediaType.parse("image/png") : MediaType.parse("image/jpeg");

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", imageName, RequestBody.create(MEDIA_TYPE, image))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL_UPLOAD)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            Log.e(TAG_UPLOAD_SERVICE, "Error when upload file, unexpected code " + response.toString());
            throw new IOException("Unexpected code " + response);
        }

    }


    private static String getFileExtension(File file) throws IOException {
        if (file == null) {
            throw new IOException("Error load file, can't find extension");
        }
        String dotSeparator = ".";
        String name = file.getName();
        int extIndex = name.lastIndexOf(dotSeparator);

        if (extIndex == -1) {
            return "";
        } else {
            return name.substring(extIndex + 1);
        }
    }
}