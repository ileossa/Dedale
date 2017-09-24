package babar.application.ileossa.babar.upload;

import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import babar.application.ileossa.babar.shareData.ShareIntent;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ileossa on 23/09/2017.
 */

public class UploadService {

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void uploadImage(File image, String imageName) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file", imageName, RequestBody.create(MEDIA_TYPE_PNG, image))
                    .build();

            Request request = new Request.Builder().url("http://localhost:8080/v1/upload")
                    .post(requestBody).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() == false) {
                throw new IOException("Unexpected code " + response);
                Toast.makeText(ShareIntent.this,"Unexpected code " + response, Toast.LENGTH_SHORT).show();
            }
        }catch (IOException e){
            Toast.makeText(ShareIntent.this,"StackTrace " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
