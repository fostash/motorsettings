package fostash.motorsettings;

import java.io.Serializable;

public class SettingsData implements Serializable {

    public static final long serialVersionUID = 1L;

    private final Long compression;
    private final Long rebound;
    private final Long preload;
    private final Long pignon;
    private final Long crown;

    public SettingsData(Long compression, Long rebound, Long preload, Long pignon, Long crown) {
        this.compression = compression;
        this.rebound = rebound;
        this.preload = preload;
        this.pignon = pignon;
        this.crown = crown;
    }

    public static SettingsData of(Long compression, Long rebound, Long preload, Long pignon, Long crown) {
        return new SettingsData(compression, rebound, preload, pignon, crown);
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
}
