package babar.application.ileossa.babar.json;

import android.util.Log;
import android.util.StringBuilderPrinter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import babar.application.ileossa.babar.MainActivity;

import static babar.application.ileossa.babar.R.id.url;

/**
 * Created by ileossa on 02/10/2017.
 */

public class HttpHandler {

    private String TAG = MainActivity.class.getSimpleName();

    static InputStream is = null;
    private String response;

    // constructor
    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {

        // Making HTTP request
        try {
            // defaultHttpClient
            URL url = new URL(reqUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            is = new BufferedInputStream( urlConnection.getInputStream());
            response = convertStreamToString(is);

        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncodingExc", e.toString());
        } catch (ClientProtocolException e) {
            Log.e("ClientProtocolException", e.toString());
        } catch (MalformedURLException e){
            Log.e("MalformedURLException", e.toString());
        } catch (IOException e) {
            Log.e("IOException", e.toString());
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e){
            Log.e(TAG, "Error convert stream to string : " + e.toString());
        }
        return stringBuilder.toString();
    }
}
