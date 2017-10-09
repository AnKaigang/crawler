package cn.akgang.entity;

public class CFDA {
    private Long id;

    private String storeId;

    private String storeName;

    private String storeProvince;

    private String storeNo;

    private String storeAddress;

    private String storeRange;

    private String stroreOpenDate;

    private String storeExpireDate;

    private String storeBackup;

    private String storeAttention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreProvince() {
        return storeProvince;
    }

    public void setStoreProvince(String storeProvince) {
        this.storeProvince = storeProvince == null ? null : storeProvince.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getStoreRange() {
        return storeRange;
    }

    public void setStoreRange(String storeRange) {
        this.storeRange = storeRange == null ? null : storeRange.trim();
    }

    public String getStroreOpenDate() {
        return stroreOpenDate;
    }

    public void setStroreOpenDate(String stroreOpenDate) {
        this.stroreOpenDate = stroreOpenDate == null ? null : stroreOpenDate.trim();
    }

    public String getStoreExpireDate() {
        return storeExpireDate;
    }

    public void setStoreExpireDate(String storeExpireDate) {
        this.storeExpireDate = storeExpireDate == null ? null : storeExpireDate.trim();
    }

    public String getStoreBackup() {
        return storeBackup;
    }

    public void setStoreBackup(String storeBackup) {
        this.storeBackup = storeBackup == null ? null : storeBackup.trim();
    }

    public String getStoreAttention() {
        return storeAttention;
    }

    public void setStoreAttention(String storeAttention) {
        this.storeAttention = storeAttention == null ? null : storeAttention.trim();
    }
}