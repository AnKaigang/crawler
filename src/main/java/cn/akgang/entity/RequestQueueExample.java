package cn.akgang.entity;

import java.util.ArrayList;
import java.util.List;

public class RequestQueueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RequestQueueExample() {
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

        public Criteria andRequestIdIsNull() {
            addCriterion("request_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNotNull() {
            addCriterion("request_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIdEqualTo(Long value) {
            addCriterion("request_id =", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotEqualTo(Long value) {
            addCriterion("request_id <>", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThan(Long value) {
            addCriterion("request_id >", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThanOrEqualTo(Long value) {
            addCriterion("request_id >=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThan(Long value) {
            addCriterion("request_id <", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThanOrEqualTo(Long value) {
            addCriterion("request_id <=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdIn(List<Long> values) {
            addCriterion("request_id in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotIn(List<Long> values) {
            addCriterion("request_id not in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdBetween(Long value1, Long value2) {
            addCriterion("request_id between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotBetween(Long value1, Long value2) {
            addCriterion("request_id not between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andHeaderIdIsNull() {
            addCriterion("header_id is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIdIsNotNull() {
            addCriterion("header_id is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderIdEqualTo(Long value) {
            addCriterion("header_id =", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotEqualTo(Long value) {
            addCriterion("header_id <>", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdGreaterThan(Long value) {
            addCriterion("header_id >", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("header_id >=", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdLessThan(Long value) {
            addCriterion("header_id <", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdLessThanOrEqualTo(Long value) {
            addCriterion("header_id <=", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdIn(List<Long> values) {
            addCriterion("header_id in", values, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotIn(List<Long> values) {
            addCriterion("header_id not in", values, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdBetween(Long value1, Long value2) {
            addCriterion("header_id between", value1, value2, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotBetween(Long value1, Long value2) {
            addCriterion("header_id not between", value1, value2, "headerId");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNull() {
            addCriterion("param_id is null");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNotNull() {
            addCriterion("param_id is not null");
            return (Criteria) this;
        }

        public Criteria andParamIdEqualTo(Long value) {
            addCriterion("param_id =", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotEqualTo(Long value) {
            addCriterion("param_id <>", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThan(Long value) {
            addCriterion("param_id >", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("param_id >=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThan(Long value) {
            addCriterion("param_id <", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThanOrEqualTo(Long value) {
            addCriterion("param_id <=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdIn(List<Long> values) {
            addCriterion("param_id in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotIn(List<Long> values) {
            addCriterion("param_id not in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdBetween(Long value1, Long value2) {
            addCriterion("param_id between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotBetween(Long value1, Long value2) {
            addCriterion("param_id not between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andHFromIsNull() {
            addCriterion("h_from is null");
            return (Criteria) this;
        }

        public Criteria andHFromIsNotNull() {
            addCriterion("h_from is not null");
            return (Criteria) this;
        }

        public Criteria andHFromEqualTo(String value) {
            addCriterion("h_from =", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromNotEqualTo(String value) {
            addCriterion("h_from <>", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromGreaterThan(String value) {
            addCriterion("h_from >", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromGreaterThanOrEqualTo(String value) {
            addCriterion("h_from >=", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromLessThan(String value) {
            addCriterion("h_from <", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromLessThanOrEqualTo(String value) {
            addCriterion("h_from <=", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromLike(String value) {
            addCriterion("h_from like", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromNotLike(String value) {
            addCriterion("h_from not like", value, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromIn(List<String> values) {
            addCriterion("h_from in", values, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromNotIn(List<String> values) {
            addCriterion("h_from not in", values, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromBetween(String value1, String value2) {
            addCriterion("h_from between", value1, value2, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHFromNotBetween(String value1, String value2) {
            addCriterion("h_from not between", value1, value2, "hFrom");
            return (Criteria) this;
        }

        public Criteria andHToIsNull() {
            addCriterion("h_to is null");
            return (Criteria) this;
        }

        public Criteria andHToIsNotNull() {
            addCriterion("h_to is not null");
            return (Criteria) this;
        }

        public Criteria andHToEqualTo(String value) {
            addCriterion("h_to =", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToNotEqualTo(String value) {
            addCriterion("h_to <>", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToGreaterThan(String value) {
            addCriterion("h_to >", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToGreaterThanOrEqualTo(String value) {
            addCriterion("h_to >=", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToLessThan(String value) {
            addCriterion("h_to <", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToLessThanOrEqualTo(String value) {
            addCriterion("h_to <=", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToLike(String value) {
            addCriterion("h_to like", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToNotLike(String value) {
            addCriterion("h_to not like", value, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToIn(List<String> values) {
            addCriterion("h_to in", values, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToNotIn(List<String> values) {
            addCriterion("h_to not in", values, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToBetween(String value1, String value2) {
            addCriterion("h_to between", value1, value2, "hTo");
            return (Criteria) this;
        }

        public Criteria andHToNotBetween(String value1, String value2) {
            addCriterion("h_to not between", value1, value2, "hTo");
            return (Criteria) this;
        }

        public Criteria andPFromIsNull() {
            addCriterion("p_from is null");
            return (Criteria) this;
        }

        public Criteria andPFromIsNotNull() {
            addCriterion("p_from is not null");
            return (Criteria) this;
        }

        public Criteria andPFromEqualTo(String value) {
            addCriterion("p_from =", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromNotEqualTo(String value) {
            addCriterion("p_from <>", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromGreaterThan(String value) {
            addCriterion("p_from >", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromGreaterThanOrEqualTo(String value) {
            addCriterion("p_from >=", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromLessThan(String value) {
            addCriterion("p_from <", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromLessThanOrEqualTo(String value) {
            addCriterion("p_from <=", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromLike(String value) {
            addCriterion("p_from like", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromNotLike(String value) {
            addCriterion("p_from not like", value, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromIn(List<String> values) {
            addCriterion("p_from in", values, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromNotIn(List<String> values) {
            addCriterion("p_from not in", values, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromBetween(String value1, String value2) {
            addCriterion("p_from between", value1, value2, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPFromNotBetween(String value1, String value2) {
            addCriterion("p_from not between", value1, value2, "pFrom");
            return (Criteria) this;
        }

        public Criteria andPToIsNull() {
            addCriterion("p_to is null");
            return (Criteria) this;
        }

        public Criteria andPToIsNotNull() {
            addCriterion("p_to is not null");
            return (Criteria) this;
        }

        public Criteria andPToEqualTo(String value) {
            addCriterion("p_to =", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToNotEqualTo(String value) {
            addCriterion("p_to <>", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToGreaterThan(String value) {
            addCriterion("p_to >", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToGreaterThanOrEqualTo(String value) {
            addCriterion("p_to >=", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToLessThan(String value) {
            addCriterion("p_to <", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToLessThanOrEqualTo(String value) {
            addCriterion("p_to <=", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToLike(String value) {
            addCriterion("p_to like", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToNotLike(String value) {
            addCriterion("p_to not like", value, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToIn(List<String> values) {
            addCriterion("p_to in", values, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToNotIn(List<String> values) {
            addCriterion("p_to not in", values, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToBetween(String value1, String value2) {
            addCriterion("p_to between", value1, value2, "pTo");
            return (Criteria) this;
        }

        public Criteria andPToNotBetween(String value1, String value2) {
            addCriterion("p_to not between", value1, value2, "pTo");
            return (Criteria) this;
        }

        public Criteria andSortNumIsNull() {
            addCriterion("sort_num is null");
            return (Criteria) this;
        }

        public Criteria andSortNumIsNotNull() {
            addCriterion("sort_num is not null");
            return (Criteria) this;
        }

        public Criteria andSortNumEqualTo(Integer value) {
            addCriterion("sort_num =", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotEqualTo(Integer value) {
            addCriterion("sort_num <>", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumGreaterThan(Integer value) {
            addCriterion("sort_num >", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_num >=", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumLessThan(Integer value) {
            addCriterion("sort_num <", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumLessThanOrEqualTo(Integer value) {
            addCriterion("sort_num <=", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumIn(List<Integer> values) {
            addCriterion("sort_num in", values, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotIn(List<Integer> values) {
            addCriterion("sort_num not in", values, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumBetween(Integer value1, Integer value2) {
            addCriterion("sort_num between", value1, value2, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_num not between", value1, value2, "sortNum");
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