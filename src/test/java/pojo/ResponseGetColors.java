package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseGetColors {

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    private List<DataItem> data;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("support")
    private Support support;

    public int getPerPage( ) {
        return perPage;
    }

    public int getTotal( ) {
        return total;
    }

    public List<DataItem> getData( ) {
        return data;
    }

    public int getPage( ) {
        return page;
    }

    public int getTotalPages( ) {
        return totalPages;
    }

    public Support getSupport( ) {
        return support;
    }


    @Override
    public String toString( ) {
        return "ResponseGetColors{" +
                "perPage=" + perPage +
                ", total=" + total +
                ", data=" + data.toString( ) +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", support=" + support.toString( ) +
                '}';
    }
}