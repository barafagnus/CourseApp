package ru.eltexstudy.courseapp.YouTubeModelApi;

import java.util.List;

public class Items {
    private Snippet snippet;

    private String kind;

    private String etag;

    private String id;

    private ContentDetails contentDetails;

    private Status status;

    private Statistics statistics;

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    public Snippet getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (Snippet snippet)
    {
        this.snippet = snippet;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }

    public Status getStatus ()
    {
        return status;
    }

    public void setStatus (Status status)
    {
        this.status = status;
    }

    public Statistics getStatistics ()
    {
        return statistics;
    }

    public void setStatistics (Statistics statistics)
    {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Items{" +
                "snippet=" + snippet +
                ", kind='" + kind + '\'' +
                ", etag='" + etag + '\'' +
                ", id='" + id + '\'' +
                ", contentDetails=" + contentDetails +
                ", status=" + status +
                ", statistics=" + statistics +
                '}';
    }
}
