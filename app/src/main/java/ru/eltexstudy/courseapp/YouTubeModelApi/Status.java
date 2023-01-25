package ru.eltexstudy.courseapp.YouTubeModelApi;

public class Status {
    private String license;

    private String privacyStatus;

    private String uploadStatus;

    private String publicStatsViewable;

    private String embeddable;

    private String madeForKids;

    public String getLicense ()
    {
        return license;
    }

    public void setLicense (String license)
    {
        this.license = license;
    }

    public String getPrivacyStatus ()
    {
        return privacyStatus;
    }

    public void setPrivacyStatus (String privacyStatus)
    {
        this.privacyStatus = privacyStatus;
    }

    public String getUploadStatus ()
    {
        return uploadStatus;
    }

    public void setUploadStatus (String uploadStatus)
    {
        this.uploadStatus = uploadStatus;
    }

    public String getPublicStatsViewable ()
    {
        return publicStatsViewable;
    }

    public void setPublicStatsViewable (String publicStatsViewable)
    {
        this.publicStatsViewable = publicStatsViewable;
    }

    public String getEmbeddable ()
    {
        return embeddable;
    }

    public void setEmbeddable (String embeddable)
    {
        this.embeddable = embeddable;
    }

    public String getMadeForKids ()
    {
        return madeForKids;
    }

    public void setMadeForKids (String madeForKids)
    {
        this.madeForKids = madeForKids;
    }

    @Override
    public String toString() {
        return "Status{" +
                "license='" + license + '\'' +
                ", privacyStatus='" + privacyStatus + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                ", publicStatsViewable='" + publicStatsViewable + '\'' +
                ", embeddable='" + embeddable + '\'' +
                ", madeForKids='" + madeForKids + '\'' +
                '}';
    }
}
