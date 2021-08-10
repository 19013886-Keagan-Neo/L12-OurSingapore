package sg.edu.rp.c346.id19013886.l12_oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Island> islandList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Island> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        islandList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        TextView tvSquarekm = rowView.findViewById(R.id.tvSquarekm);
        RatingBar rbStars = rowView.findViewById(R.id.rbStars);
        ImageView wowImg = rowView.findViewById(R.id.ivNew);

        // Obtain the Android Version information based on the position
        Island currentObj = islandList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentObj.getName());
        tvSquarekm.setText(String.valueOf(currentObj.getSquarekm()));
        rbStars.setRating(currentObj.getStars());
        tvDescription.setText(currentObj.getDescription());
        wowImg.setImageResource(R.drawable.wow_image);

        if (currentObj.getSquarekm() >= 3) {
            wowImg.setVisibility(View.VISIBLE);
        } else {
            wowImg.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

}