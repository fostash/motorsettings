package fostash.motorsettings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SettingsAdapter extends ArrayAdapter<SettingsData> {

    public SettingsAdapter(@NonNull Context context, int resource, @NonNull List<SettingsData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.settings_adapter, null);
        final TextView compression = convertView.findViewById(R.id.compression_value);
        final TextView rebound = convertView.findViewById(R.id.rebound_value);
        final TextView preload = convertView.findViewById(R.id.preload_value);
        final TextView pignon = convertView.findViewById(R.id.pignon_value);
        final TextView crown = convertView.findViewById(R.id.crown_value);
        final TextView date = convertView.findViewById(R.id.date_value);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        final SettingsData c = getItem(position);
        if (c != null) {
            rebound.setText(String.valueOf(c.getRebound()));
            compression.setText(String.valueOf(c.getCompression()));
            preload.setText(String.valueOf(c.getPreload()));
            pignon.setText(String.valueOf(c.getPignon()));
            crown.setText(String.valueOf(c.getCrown()));
            System.out.println("date item " + c.getDate());
            date.setText(sdf.format(c.getDate()));
        }
        return convertView;
    }
}
