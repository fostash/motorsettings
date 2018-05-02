package fostash.motorsettings;

import java.io.Serializable;
import java.util.Date;

public class SettingsData implements Serializable {

    public static final long serialVersionUID = 1L;

    private final String compression;
    private final String rebound;
    private final String preload;
    private final String pignon;
    private final String crown;
    private final Date date;

    public SettingsData(String compression, String rebound, String preload, String pignon, String crown, Date date) {
        this.compression = compression;
        this.rebound = rebound;
        this.preload = preload;
        this.pignon = pignon;
        this.crown = crown;
        this.date = date;
    }

    public static SettingsData of(String compression, String rebound, String preload, String pignon, String crown, Date date) {
        return new SettingsData(compression, rebound, preload, pignon, crown, date);
    }

    public String getCompression() {
        return compression;
    }

    public String getRebound() {
        return rebound;
    }

    public String getPreload() {
        return preload;
    }

    public String getPignon() {
        return pignon;
    }

    public String getCrown() {
        return crown;
    }

    public Date getDate() {
        return date;
    }
}
