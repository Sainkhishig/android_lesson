package mobile.asterisk.lesson;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Bus> {

    Context context;
    int layoutResourceId;
    Bus data[] = null;

    public CustomAdapter(Context context, int layoutResourceId, Bus[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.txvId = (TextView)row.findViewById(R.id.txv_id);
            holder.txvName = (TextView)row.findViewById(R.id.txv_name);
            row.setTag(holder);
        }
        else {
            holder = (DataHolder) row.getTag();
        }

        Bus item = data[position];
        if(item != null) {
            if(layoutResourceId == R.layout.bus_row) {
                holder.txvId.setText(item.getId()+"");
                holder.txvName.setText(item.getName());
            }
        }
        return row;
    }

    static class DataHolder
    {
        TextView txvId;
        TextView txvName;
    }
}