package fostash.motorsettings;

import java.io.Serializable;
import java.util.Date;

public class SettingsData implements Serializable {

    public static final long serialVersionUID = 1L;

    private final Long compression;
    private final Long rebound;
    private final Long preload;
    private final Long pignon;
    private final Long crown;
    private final Date date;

    public SettingsData(Long compression, Long rebound, Long preload, Long pignon, Long crown, Date date) {
        this.compression = compression;
        this.rebound = rebound;
        this.preload = preload;
        this.pignon = pignon;
        this.crown = crown;
        this.date = date;
    }

    public static SettingsData of(Long compression, Long rebound, Long preload, Long pignon, Long crown, Date date) {
        return new SettingsData(compression, rebound, preload, pignon, crown, date);
    }

    public Long getCompression() {
        return compression;
    }

    public Long getRebound() {
        return rebound;
    }

    public Long getPreload() {
        return preload;
    }

    public Long getPignon() {
        return pignon;
    }

    public Long getCrown() {
        return crown;
    }

    public Date getDate() {
        return date;
    }
}
