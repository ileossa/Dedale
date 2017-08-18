package babar.application.ileossa.babar.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import babar.application.ileossa.babar.R;

/**
 * Created by ileossa on 18/08/2017.
 */

public class Gallerie extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerie);
        gridView = (GridView) findViewById(R.id.gridView1);

        // String Array for setting data over gridview
        String data[] = { "A", "B", "C", "D", "F", "G", "H", "I" };

        // Array adapter for holding the data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Gallerie.this, android.R.layout.simple_list_item_1, data);

        // Setting adapter over gridview
        gridView.setAdapter(adapter);

        // Notifying adapter
        adapter.notifyDataSetChanged();

        // Setting click listener on items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Showing clicked item position
                Toast.makeText(Gallerie.this,
                        "Clicked Position : " + position, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
