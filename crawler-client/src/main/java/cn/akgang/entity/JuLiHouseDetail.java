package cn.akgang.entity;

/**
 * @author akgang
 * @date 2018/3/27.
 */
public class JuLiHouseDetail {

    private String projectId;

    private String name;

    private String address;

    private String developer;

    private String lat;

    private String lng;

    private String house;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "JuLiHouseDetail{" +
                "projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", developer='" + developer + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
