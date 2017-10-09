package cn.akgang.entity;

import java.util.ArrayList;
import java.util.List;

public class CFDAExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CFDAExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(String value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(String value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(String value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(String value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(String value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(String value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLike(String value) {
            addCriterion("store_id like", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotLike(String value) {
            addCriterion("store_id not like", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<String> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<String> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(String value1, String value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(String value1, String value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceIsNull() {
            addCriterion("store_province is null");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceIsNotNull() {
            addCriterion("store_province is not null");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceEqualTo(String value) {
            addCriterion("store_province =", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceNotEqualTo(String value) {
            addCriterion("store_province <>", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceGreaterThan(String value) {
            addCriterion("store_province >", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("store_province >=", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceLessThan(String value) {
            addCriterion("store_province <", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceLessThanOrEqualTo(String value) {
            addCriterion("store_province <=", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceLike(String value) {
            addCriterion("store_province like", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceNotLike(String value) {
            addCriterion("store_province not like", value, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceIn(List<String> values) {
            addCriterion("store_province in", values, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceNotIn(List<String> values) {
            addCriterion("store_province not in", values, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceBetween(String value1, String value2) {
            addCriterion("store_province between", value1, value2, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreProvinceNotBetween(String value1, String value2) {
            addCriterion("store_province not between", value1, value2, "storeProvince");
            return (Criteria) this;
        }

        public Criteria andStoreNoIsNull() {
            addCriterion("store_no is null");
            return (Criteria) this;
        }

        public Criteria andStoreNoIsNotNull() {
            addCriterion("store_no is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNoEqualTo(String value) {
            addCriterion("store_no =", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotEqualTo(String value) {
            addCriterion("store_no <>", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoGreaterThan(String value) {
            addCriterion("store_no >", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoGreaterThanOrEqualTo(String value) {
            addCriterion("store_no >=", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoLessThan(String value) {
            addCriterion("store_no <", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoLessThanOrEqualTo(String value) {
            addCriterion("store_no <=", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoLike(String value) {
            addCriterion("store_no like", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotLike(String value) {
            addCriterion("store_no not like", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoIn(List<String> values) {
            addCriterion("store_no in", values, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotIn(List<String> values) {
            addCriterion("store_no not in", values, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoBetween(String value1, String value2) {
            addCriterion("store_no between", value1, value2, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotBetween(String value1, String value2) {
            addCriterion("store_no not between", value1, value2, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNull() {
            addCriterion("store_address is null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIsNotNull() {
            addCriterion("store_address is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAddressEqualTo(String value) {
            addCriterion("store_address =", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotEqualTo(String value) {
            addCriterion("store_address <>", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThan(String value) {
            addCriterion("store_address >", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressGreaterThanOrEqualTo(String value) {
            addCriterion("store_address >=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThan(String value) {
            addCriterion("store_address <", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLessThanOrEqualTo(String value) {
            addCriterion("store_address <=", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressLike(String value) {
            addCriterion("store_address like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotLike(String value) {
            addCriterion("store_address not like", value, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressIn(List<String> values) {
            addCriterion("store_address in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotIn(List<String> values) {
            addCriterion("store_address not in", values, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressBetween(String value1, String value2) {
            addCriterion("store_address between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreAddressNotBetween(String value1, String value2) {
            addCriterion("store_address not between", value1, value2, "storeAddress");
            return (Criteria) this;
        }

        public Criteria andStoreRangeIsNull() {
            addCriterion("store_range is null");
            return (Criteria) this;
        }

        public Criteria andStoreRangeIsNotNull() {
            addCriterion("store_range is not null");
            return (Criteria) this;
        }

        public Criteria andStoreRangeEqualTo(String value) {
            addCriterion("store_range =", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeNotEqualTo(String value) {
            addCriterion("store_range <>", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeGreaterThan(String value) {
            addCriterion("store_range >", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeGreaterThanOrEqualTo(String value) {
            addCriterion("store_range >=", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeLessThan(String value) {
            addCriterion("store_range <", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeLessThanOrEqualTo(String value) {
            addCriterion("store_range <=", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeLike(String value) {
            addCriterion("store_range like", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeNotLike(String value) {
            addCriterion("store_range not like", value, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeIn(List<String> values) {
            addCriterion("store_range in", values, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeNotIn(List<String> values) {
            addCriterion("store_range not in", values, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeBetween(String value1, String value2) {
            addCriterion("store_range between", value1, value2, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStoreRangeNotBetween(String value1, String value2) {
            addCriterion("store_range not between", value1, value2, "storeRange");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateIsNull() {
            addCriterion("strore_open_date is null");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateIsNotNull() {
            addCriterion("strore_open_date is not null");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateEqualTo(String value) {
            addCriterion("strore_open_date =", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateNotEqualTo(String value) {
            addCriterion("strore_open_date <>", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateGreaterThan(String value) {
            addCriterion("strore_open_date >", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateGreaterThanOrEqualTo(String value) {
            addCriterion("strore_open_date >=", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateLessThan(String value) {
            addCriterion("strore_open_date <", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateLessThanOrEqualTo(String value) {
            addCriterion("strore_open_date <=", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateLike(String value) {
            addCriterion("strore_open_date like", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateNotLike(String value) {
            addCriterion("strore_open_date not like", value, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateIn(List<String> values) {
            addCriterion("strore_open_date in", values, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateNotIn(List<String> values) {
            addCriterion("strore_open_date not in", values, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateBetween(String value1, String value2) {
            addCriterion("strore_open_date between", value1, value2, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStroreOpenDateNotBetween(String value1, String value2) {
            addCriterion("strore_open_date not between", value1, value2, "stroreOpenDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateIsNull() {
            addCriterion("store_expire_date is null");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateIsNotNull() {
            addCriterion("store_expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateEqualTo(String value) {
            addCriterion("store_expire_date =", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateNotEqualTo(String value) {
            addCriterion("store_expire_date <>", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateGreaterThan(String value) {
            addCriterion("store_expire_date >", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateGreaterThanOrEqualTo(String value) {
            addCriterion("store_expire_date >=", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateLessThan(String value) {
            addCriterion("store_expire_date <", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateLessThanOrEqualTo(String value) {
            addCriterion("store_expire_date <=", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateLike(String value) {
            addCriterion("store_expire_date like", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateNotLike(String value) {
            addCriterion("store_expire_date not like", value, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateIn(List<String> values) {
            addCriterion("store_expire_date in", values, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateNotIn(List<String> values) {
            addCriterion("store_expire_date not in", values, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateBetween(String value1, String value2) {
            addCriterion("store_expire_date between", value1, value2, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreExpireDateNotBetween(String value1, String value2) {
            addCriterion("store_expire_date not between", value1, value2, "storeExpireDate");
            return (Criteria) this;
        }

        public Criteria andStoreBackupIsNull() {
            addCriterion("store_backup is null");
            return (Criteria) this;
        }

        public Criteria andStoreBackupIsNotNull() {
            addCriterion("store_backup is not null");
            return (Criteria) this;
        }

        public Criteria andStoreBackupEqualTo(String value) {
            addCriterion("store_backup =", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupNotEqualTo(String value) {
            addCriterion("store_backup <>", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupGreaterThan(String value) {
            addCriterion("store_backup >", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupGreaterThanOrEqualTo(String value) {
            addCriterion("store_backup >=", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupLessThan(String value) {
            addCriterion("store_backup <", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupLessThanOrEqualTo(String value) {
            addCriterion("store_backup <=", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupLike(String value) {
            addCriterion("store_backup like", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupNotLike(String value) {
            addCriterion("store_backup not like", value, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupIn(List<String> values) {
            addCriterion("store_backup in", values, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupNotIn(List<String> values) {
            addCriterion("store_backup not in", values, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupBetween(String value1, String value2) {
            addCriterion("store_backup between", value1, value2, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreBackupNotBetween(String value1, String value2) {
            addCriterion("store_backup not between", value1, value2, "storeBackup");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionIsNull() {
            addCriterion("store_attention is null");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionIsNotNull() {
            addCriterion("store_attention is not null");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionEqualTo(String value) {
            addCriterion("store_attention =", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionNotEqualTo(String value) {
            addCriterion("store_attention <>", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionGreaterThan(String value) {
            addCriterion("store_attention >", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionGreaterThanOrEqualTo(String value) {
            addCriterion("store_attention >=", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionLessThan(String value) {
            addCriterion("store_attention <", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionLessThanOrEqualTo(String value) {
            addCriterion("store_attention <=", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionLike(String value) {
            addCriterion("store_attention like", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionNotLike(String value) {
            addCriterion("store_attention not like", value, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionIn(List<String> values) {
            addCriterion("store_attention in", values, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionNotIn(List<String> values) {
            addCriterion("store_attention not in", values, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionBetween(String value1, String value2) {
            addCriterion("store_attention between", value1, value2, "storeAttention");
            return (Criteria) this;
        }

        public Criteria andStoreAttentionNotBetween(String value1, String value2) {
            addCriterion("store_attention not between", value1, value2, "storeAttention");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}