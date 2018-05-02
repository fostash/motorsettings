package fostash.motorsettings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SettingsAdapter extends ArrayAdapter<SettingsData> {

    public SettingsAdapter(@NonNull Context context, int resource, @NonNull List<SettingsData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.settings_adapter, null);
        TextView compression = (TextView)convertView.findViewById(R.id.compression_value);
        TextView rebound = (TextView)convertView.findViewById(R.id.rebound_value);
        TextView preload = (TextView)convertView.findViewById(R.id.preload_value);
        TextView pignon = (TextView)convertView.findViewById(R.id.pignon_value);
        TextView crown = (TextView)convertView.findViewById(R.id.crown_value);
        SettingsData c = getItem(position);
        rebound.setText(c.getRebound()+"");
        compression.setText(c.getCompression()+"");
        preload.setText(c.getPreload()+"");
        pignon.setText(c.getPignon()+"");
        crown.setText(c.getCrown()+"");
        return convertView;
    }
}
