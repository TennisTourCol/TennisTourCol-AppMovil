package com.tennistourcol;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tennistourcol.model.Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Match> implements View.OnClickListener{

    private ArrayList<Match> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView playerOne;
        TextView playerTwo;
        TextView date;
    }

    public CustomAdapter(ArrayList<Match> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Match match=(Match)object;

        switch (v.getId())
        {
            case R.id.fecha_proximo_partido:
                Snackbar.make(v, "Release date " +match.getPlayer1(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Match match = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.date = (TextView) convertView.findViewById(R.id.fecha_proximo_partido);
            viewHolder.playerOne = (TextView) convertView.findViewById(R.id.player_one);
            viewHolder.playerTwo = (TextView) convertView.findViewById(R.id.player_two);

//            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
//            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.date.setText(format.format(match.getDate())+" "+match.getHour());
        viewHolder.playerOne.setText(match.getPlayer1());
        viewHolder.playerTwo.setText(match.getPlayer2());
//        viewHolder.txtType.setText(match.getType());
//        viewHolder.txtVersion.setText(match.getVersion_number());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}