package cn.akgang.entity;

import java.io.Serializable;
import java.util.Date;

public class Store implements Serializable {
    private Long id;

    private String name;

    private String pos;

    private Double latitude;

    private Double longitude;

    private Date createdAt;

    private Date updatedAt;
    private String monthSaleCount;
    private String distance;
    private String deliveryTime;
    private String deliverFee;
    private String avgPrice;
    private String discount;
    private String minDeliverFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos == null ? null : pos.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setMonthSaleCount(String monthSaleCount) {
        this.monthSaleCount = monthSaleCount;
    }

    public String getMonthSaleCount() {
        return monthSaleCount;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliverFee(String deliverFee) {
        this.deliverFee = deliverFee;
    }

    public String getDeliverFee() {
        return deliverFee;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", monthSaleCount='" + monthSaleCount + '\'' +
                ", distance='" + distance + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", deliverFee='" + deliverFee + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }

    public void setMinDeliverFee(String minDeliverFee) {
        this.minDeliverFee = minDeliverFee;
    }

    public String getMinDeliverFee() {
        return minDeliverFee;
    }
}