package cn.akgang.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author akgang
 */
@Document
public class Store implements Serializable {

    private String id;

    private String storeName;

    private String score;

    private Date createdAt;

    private Date updatedAt;

    private String monthCount;

    private String startPrice;

    private String deliverPrice;

    private String deliverTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String name) {
        this.storeName = name == null ? null : name.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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

    public void setMonthCount(String monthCount) {
        this.monthCount = monthCount;
    }

    public String getMonthCount() {
        return monthCount;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setDeliverPrice(String deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", score='" + score + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", monthCount='" + monthCount + '\'' +
                ", startPrice='" + startPrice + '\'' +
                ", deliverPrice='" + deliverPrice + '\'' +
                ", deliverTime='" + deliverTime + '\'' +
                '}';
    }
}