package cn.akgang.entity;

public class RequestQueue {
    private Long id;

    private Long requestId;

    private String url;

    private Long headerId;

    private Long paramId;

    private String hFrom;

    private String hTo;

    private String pFrom;

    private String pTo;

    private Integer sortNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String gethFrom() {
        return hFrom;
    }

    public void sethFrom(String hFrom) {
        this.hFrom = hFrom == null ? null : hFrom.trim();
    }

    public String gethTo() {
        return hTo;
    }

    public void sethTo(String hTo) {
        this.hTo = hTo == null ? null : hTo.trim();
    }

    public String getpFrom() {
        return pFrom;
    }

    public void setpFrom(String pFrom) {
        this.pFrom = pFrom == null ? null : pFrom.trim();
    }

    public String getpTo() {
        return pTo;
    }

    public void setpTo(String pTo) {
        this.pTo = pTo == null ? null : pTo.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}