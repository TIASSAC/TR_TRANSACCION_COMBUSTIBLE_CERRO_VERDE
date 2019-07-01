package com.example.generartransaccioncombustible.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.generartransaccioncombustible.R;
import com.example.generartransaccioncombustible.entities.StationORMEntity;

import java.util.List;


public class StationAdapter extends BaseAdapter {

    private Context context;
    private List<StationORMEntity> items;

    public StationAdapter(Context context, List<StationORMEntity> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_station,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtStation = (TextView)convertView.findViewById(R.id.TextViewStationSpinner);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String stationName = items.get(i).getStationName();
        if (!stationName.isEmpty() || viewHolder != null){
            viewHolder.txtStation.setText(stationName);
        }


        return convertView;
    }

    public static class ViewHolder{
        TextView txtStation;
    }
}
