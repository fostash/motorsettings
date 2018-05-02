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
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        final SettingsData c = getItem(position);
        if (c != null) {
            convertView.<TextView>findViewById(R.id.compression_value).setText(c.getCompression());
            convertView.<TextView>findViewById(R.id.rebound_value).setText(c.getRebound());
            convertView.<TextView>findViewById(R.id.preload_value).setText(c.getPreload());
            convertView.<TextView>findViewById(R.id.pignon_value).setText(c.getPignon());
            convertView.<TextView>findViewById(R.id.crown_value).setText(c.getCrown());
            convertView.<TextView>findViewById(R.id.date_value).setText(sdf.format(c.getDate()));
        }
        return convertView;
    }
}
