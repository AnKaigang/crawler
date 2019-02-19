package cn.akgang.entity;

/**
 * @author ankaigang@baoyinxiaofei.com
 * @date 2018-08-14 11:48
 * @desc
 */
public class HiCall {
    private String PeerAddress;

    public String getPeerAddress() {
        return PeerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        PeerAddress = peerAddress;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getCallLogId() {
        return CallLogId;
    }

    public void setCallLogId(String callLogId) {
        CallLogId = callLogId;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    private String DeviceID;
    private String StartTime;
    private String relationId;
    private String Duration;
    private String CallLogId;
    private String Direction;

    @Override
    public String toString() {
        return "HiCall{" +
                "PeerAddress='" + PeerAddress + '\'' +
                ", DeviceID='" + DeviceID + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", relationId='" + relationId + '\'' +
                ", Duration='" + Duration + '\'' +
                ", CallLogId='" + CallLogId + '\'' +
                ", Direction='" + Direction + '\'' +
                '}';
    }
}
