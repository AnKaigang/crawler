package cn.akgang.entity;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class JuliPage {

    private Integer hasMore;

    private Integer total;

    private Integer pageSize;

    private String list;

    public Integer getHasMore() {
        return hasMore;
    }

    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JuliPage{" +
                "hasMore=" + hasMore +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
