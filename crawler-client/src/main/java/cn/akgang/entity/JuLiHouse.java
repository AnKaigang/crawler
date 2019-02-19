package cn.akgang.entity;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class JuLiHouse {

    private String houseTypeId;

    private String summary;

    private String roomType;

    private String status;

    private String offerPrice;

    private String acreage;

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    @Override
    public String toString() {
        return "JuLiHouse{" +
                "houseTypeId='" + houseTypeId + '\'' +
                ", summary='" + summary + '\'' +
                ", roomType='" + roomType + '\'' +
                ", status='" + status + '\'' +
                ", offerPrice='" + offerPrice + '\'' +
                ", acreage='" + acreage + '\'' +
                '}';
    }
}
