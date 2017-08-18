package babar.application.ileossa.babar.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import babar.application.ileossa.babar.MainActivity;
import babar.application.ileossa.babar.R;
import babar.application.ileossa.babar.gallery.cutomGridView.CustomGridViewAdapter;
import babar.application.ileossa.babar.gallery.cutomGridView.Items;
import babar.application.ileossa.babar.gallery.cutomGridView.SingleImageActivity;

/**
 * Created by ileossa on 18/08/2017.
 */

public class CustomGridView extends AppCompatActivity {

    private GridView gridView;
    private static final String title[] = { "toto", "tata", "thth", "gdgdg"};
    private static final Integer Images[]={
            R.drawable.ic_cloud_off_red,
            R.drawable.ic_cloud_off_red,
            R.drawable.ic_cloud_off_red,
            R.drawable.ic_cloud_off_red
    };
    ArrayList<Items> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_grid_view);
        gridView = (GridView) findViewById(R.id.gridView1);

        arrayList = new ArrayList<Items>();
        for (int i = 0; i<Images.length; ++i){
            arrayList.add(new Items(title[i], Images[i]));
        }

        CustomGridViewAdapter adapter = new CustomGridViewAdapter(CustomGridView.this, arrayList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomGridView.this, SingleImageActivity.class);
                intent.putExtra("image", arrayList.get(position).getImage());
                startActivity(intent);
            }
        });
    }
}
