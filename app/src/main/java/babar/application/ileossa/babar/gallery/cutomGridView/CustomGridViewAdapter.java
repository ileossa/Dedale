package babar.application.ileossa.babar.gallery.cutomGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import babar.application.ileossa.babar.R;

/**
 * Created by ileossa on 18/08/2017.
 */

public class CustomGridViewAdapter extends BaseAdapter {
    private ArrayList<Items> arrayList;
    private LayoutInflater inflater;
    private ViewHolder holder = null;

    public CustomGridViewAdapter(Context context, ArrayList<Items> arrayList) {
        this.arrayList = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View view, ViewGroup root) {

        if (view == null) {

            view = inflater.inflate(R.layout.adapter_custom_grid_view, root, false);
            holder = new ViewHolder();

            holder.image = (ImageView) view.findViewById(R.id.imageView);
            holder.text = (TextView) view.findViewById(R.id.textView);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Setting image and title from arraylist
        holder.image.setBackgroundResource(arrayList.get(pos).getImage());
        holder.text.setText(arrayList.get(pos).getTitle());

        return view;
    }

    // View holder class to hold the views
    public class ViewHolder {
        ImageView image;
        TextView text;
    }
}
