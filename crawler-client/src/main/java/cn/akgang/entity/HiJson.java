package cn.akgang.entity;

import java.util.List;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class HiJson {

    private Integer TotalCount;

    private List<HiCall> CallLogInfos;

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.TotalCount = totalCount;
    }

    public List<HiCall> getCallLogInfos() {
        return CallLogInfos;
    }

    public void setCallLogInfos(List<HiCall> callLogInfos) {
        this.CallLogInfos = callLogInfos;
    }

    @Override
    public String toString() {
        return "JuliJson{" +
                "TotalCount=" + TotalCount +
                ", CallLogInfos='" + CallLogInfos + '\'' +
                '}';
    }
}
