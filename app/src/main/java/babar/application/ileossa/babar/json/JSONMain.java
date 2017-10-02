package babar.application.ileossa.babar.json;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import babar.application.ileossa.babar.R;

public class JSONMain extends Activity {

    TextView uid;
    TextView name1;
    TextView tag1;
    TextView classpath1;
    Button Btngetdata;

    //URL to get JSON Array
    private static String url = "http://059742cd.ngrok.io/v1/all";

    //JSON Node Names
    private static final String TAG_USER = "user";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_TAG = "tag";
    private static final String TAG_CLASSPATH = "classpath";

    JSONArray user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jsonmain);
        Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new JSONParse().execute();

            }
        });

    }

    private class JSONParse extends AsyncTask<Object, Object, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uid = (TextView)findViewById(R.id.uid);
            name1 = (TextView)findViewById(R.id.name);
            tag1 = (TextView)findViewById(R.id.tag);
            classpath1 = (TextView)findViewById(R.id.classpath);
            pDialog = new ProgressDialog(JSONMain.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected String doInBackground(Object... params) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            String res = jParser.getJSONFromUrl(url);
            return res;
        }


        @Override
        protected void onPostExecute(String json) {
            pDialog.dismiss();
            try {

                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i < json.length(); i++){
                    // Getting JSON Array
    //                user = json.getJSONArray(TAG_USER);
                    JSONObject c = jsonArray.getJSONObject(i);

                    // Storing  JSON item in a Variable
                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String tag = c.getString(TAG_TAG);
                    String classpath = c.getString(TAG_CLASSPATH);

                    //Set JSON Data in TextView
                    uid.setText(id);
                    name1.setText(name);
                    tag1.setText(tag);
                    classpath1.setText(classpath);
                }

            } catch (JSONException e) {
                Log.e("JSONExecption", "Error: " + e.toString());
            }

        }
    }

}
