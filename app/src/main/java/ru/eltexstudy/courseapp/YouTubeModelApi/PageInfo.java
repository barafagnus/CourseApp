package ru.eltexstudy.courseapp.YouTubeModelApi;

public class PageInfo {
    private String totalResults;

    private String resultsPerPage;

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public String getResultsPerPage ()
    {
        return resultsPerPage;
    }

    public void setResultsPerPage (String resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "totalResults='" + totalResults + '\'' +
                ", resultsPerPage='" + resultsPerPage + '\'' +
                '}';
    }
}
