package cn.akgang.entity;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class JuliHouseSimple {

    private String projectId;

    private String name;

    private String projectType;

    private String status;

    private String tradeAreaDesc;

    private String seeNum;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeAreaDesc() {
        return tradeAreaDesc;
    }

    public void setTradeAreaDesc(String tradeAreaDesc) {
        this.tradeAreaDesc = tradeAreaDesc;
    }

    public String getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(String seeNum) {
        this.seeNum = seeNum;
    }

    @Override
    public String toString() {
        return "JuliHouseSimple{" +
                "projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", projectType='" + projectType + '\'' +
                ", status='" + status + '\'' +
                ", tradeAreaDesc='" + tradeAreaDesc + '\'' +
                ", seeNum='" + seeNum + '\'' +
                '}';
    }
}
