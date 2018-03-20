package com.sjzxywlkj.cplife.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CommunityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommunityExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andCommunityIdIsNull() {
            addCriterion("community_id is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIsNotNull() {
            addCriterion("community_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdEqualTo(String value) {
            addCriterion("community_id =", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotEqualTo(String value) {
            addCriterion("community_id <>", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThan(String value) {
            addCriterion("community_id >", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThanOrEqualTo(String value) {
            addCriterion("community_id >=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThan(String value) {
            addCriterion("community_id <", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThanOrEqualTo(String value) {
            addCriterion("community_id <=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLike(String value) {
            addCriterion("community_id like", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotLike(String value) {
            addCriterion("community_id not like", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIn(List<String> values) {
            addCriterion("community_id in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotIn(List<String> values) {
            addCriterion("community_id not in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdBetween(String value1, String value2) {
            addCriterion("community_id between", value1, value2, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotBetween(String value1, String value2) {
            addCriterion("community_id not between", value1, value2, "communityId");
            return (Criteria) this;
        }

        public Criteria andMerchantPidIsNull() {
            addCriterion("merchant_pid is null");
            return (Criteria) this;
        }

        public Criteria andMerchantPidIsNotNull() {
            addCriterion("merchant_pid is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantPidEqualTo(String value) {
            addCriterion("merchant_pid =", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidNotEqualTo(String value) {
            addCriterion("merchant_pid <>", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidGreaterThan(String value) {
            addCriterion("merchant_pid >", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_pid >=", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidLessThan(String value) {
            addCriterion("merchant_pid <", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidLessThanOrEqualTo(String value) {
            addCriterion("merchant_pid <=", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidLike(String value) {
            addCriterion("merchant_pid like", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidNotLike(String value) {
            addCriterion("merchant_pid not like", value, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidIn(List<String> values) {
            addCriterion("merchant_pid in", values, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidNotIn(List<String> values) {
            addCriterion("merchant_pid not in", values, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidBetween(String value1, String value2) {
            addCriterion("merchant_pid between", value1, value2, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andMerchantPidNotBetween(String value1, String value2) {
            addCriterion("merchant_pid not between", value1, value2, "merchantPid");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIsNull() {
            addCriterion("community_name is null");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIsNotNull() {
            addCriterion("community_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityNameEqualTo(String value) {
            addCriterion("community_name =", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotEqualTo(String value) {
            addCriterion("community_name <>", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameGreaterThan(String value) {
            addCriterion("community_name >", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameGreaterThanOrEqualTo(String value) {
            addCriterion("community_name >=", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLessThan(String value) {
            addCriterion("community_name <", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLessThanOrEqualTo(String value) {
            addCriterion("community_name <=", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLike(String value) {
            addCriterion("community_name like", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotLike(String value) {
            addCriterion("community_name not like", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIn(List<String> values) {
            addCriterion("community_name in", values, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotIn(List<String> values) {
            addCriterion("community_name not in", values, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameBetween(String value1, String value2) {
            addCriterion("community_name between", value1, value2, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotBetween(String value1, String value2) {
            addCriterion("community_name not between", value1, value2, "communityName");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeIsNull() {
            addCriterion("district_code is null");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeIsNotNull() {
            addCriterion("district_code is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeEqualTo(String value) {
            addCriterion("district_code =", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeNotEqualTo(String value) {
            addCriterion("district_code <>", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeGreaterThan(String value) {
            addCriterion("district_code >", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeGreaterThanOrEqualTo(String value) {
            addCriterion("district_code >=", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeLessThan(String value) {
            addCriterion("district_code <", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeLessThanOrEqualTo(String value) {
            addCriterion("district_code <=", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeLike(String value) {
            addCriterion("district_code like", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeNotLike(String value) {
            addCriterion("district_code not like", value, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeIn(List<String> values) {
            addCriterion("district_code in", values, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeNotIn(List<String> values) {
            addCriterion("district_code not in", values, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeBetween(String value1, String value2) {
            addCriterion("district_code between", value1, value2, "districtCode");
            return (Criteria) this;
        }

        public Criteria andDistrictCodeNotBetween(String value1, String value2) {
            addCriterion("district_code not between", value1, value2, "districtCode");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressIsNull() {
            addCriterion("community_address is null");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressIsNotNull() {
            addCriterion("community_address is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressEqualTo(String value) {
            addCriterion("community_address =", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressNotEqualTo(String value) {
            addCriterion("community_address <>", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressGreaterThan(String value) {
            addCriterion("community_address >", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressGreaterThanOrEqualTo(String value) {
            addCriterion("community_address >=", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressLessThan(String value) {
            addCriterion("community_address <", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressLessThanOrEqualTo(String value) {
            addCriterion("community_address <=", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressLike(String value) {
            addCriterion("community_address like", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressNotLike(String value) {
            addCriterion("community_address not like", value, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressIn(List<String> values) {
            addCriterion("community_address in", values, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressNotIn(List<String> values) {
            addCriterion("community_address not in", values, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressBetween(String value1, String value2) {
            addCriterion("community_address between", value1, value2, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andCommunityAddressNotBetween(String value1, String value2) {
            addCriterion("community_address not between", value1, value2, "communityAddress");
            return (Criteria) this;
        }

        public Criteria andHotlineIsNull() {
            addCriterion("hotline is null");
            return (Criteria) this;
        }

        public Criteria andHotlineIsNotNull() {
            addCriterion("hotline is not null");
            return (Criteria) this;
        }

        public Criteria andHotlineEqualTo(String value) {
            addCriterion("hotline =", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineNotEqualTo(String value) {
            addCriterion("hotline <>", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineGreaterThan(String value) {
            addCriterion("hotline >", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineGreaterThanOrEqualTo(String value) {
            addCriterion("hotline >=", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineLessThan(String value) {
            addCriterion("hotline <", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineLessThanOrEqualTo(String value) {
            addCriterion("hotline <=", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineLike(String value) {
            addCriterion("hotline like", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineNotLike(String value) {
            addCriterion("hotline not like", value, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineIn(List<String> values) {
            addCriterion("hotline in", values, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineNotIn(List<String> values) {
            addCriterion("hotline not in", values, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineBetween(String value1, String value2) {
            addCriterion("hotline between", value1, value2, "hotline");
            return (Criteria) this;
        }

        public Criteria andHotlineNotBetween(String value1, String value2) {
            addCriterion("hotline not between", value1, value2, "hotline");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresIsNull() {
            addCriterion("ervice_expires is null");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresIsNotNull() {
            addCriterion("ervice_expires is not null");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresEqualTo(Date value) {
            addCriterionForJDBCDate("ervice_expires =", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresNotEqualTo(Date value) {
            addCriterionForJDBCDate("ervice_expires <>", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresGreaterThan(Date value) {
            addCriterionForJDBCDate("ervice_expires >", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ervice_expires >=", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresLessThan(Date value) {
            addCriterionForJDBCDate("ervice_expires <", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ervice_expires <=", value, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresIn(List<Date> values) {
            addCriterionForJDBCDate("ervice_expires in", values, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresNotIn(List<Date> values) {
            addCriterionForJDBCDate("ervice_expires not in", values, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ervice_expires between", value1, value2, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andErviceExpiresNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ervice_expires not between", value1, value2, "erviceExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageIsNull() {
            addCriterion("qr_code_image is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageIsNotNull() {
            addCriterion("qr_code_image is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageEqualTo(String value) {
            addCriterion("qr_code_image =", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageNotEqualTo(String value) {
            addCriterion("qr_code_image <>", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageGreaterThan(String value) {
            addCriterion("qr_code_image >", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code_image >=", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageLessThan(String value) {
            addCriterion("qr_code_image <", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageLessThanOrEqualTo(String value) {
            addCriterion("qr_code_image <=", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageLike(String value) {
            addCriterion("qr_code_image like", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageNotLike(String value) {
            addCriterion("qr_code_image not like", value, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageIn(List<String> values) {
            addCriterion("qr_code_image in", values, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageNotIn(List<String> values) {
            addCriterion("qr_code_image not in", values, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageBetween(String value1, String value2) {
            addCriterion("qr_code_image between", value1, value2, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeImageNotBetween(String value1, String value2) {
            addCriterion("qr_code_image not between", value1, value2, "qrCodeImage");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresIsNull() {
            addCriterion("qr_code_expires is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresIsNotNull() {
            addCriterion("qr_code_expires is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresEqualTo(Date value) {
            addCriterionForJDBCDate("qr_code_expires =", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresNotEqualTo(Date value) {
            addCriterionForJDBCDate("qr_code_expires <>", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresGreaterThan(Date value) {
            addCriterionForJDBCDate("qr_code_expires >", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("qr_code_expires >=", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresLessThan(Date value) {
            addCriterionForJDBCDate("qr_code_expires <", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("qr_code_expires <=", value, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresIn(List<Date> values) {
            addCriterionForJDBCDate("qr_code_expires in", values, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresNotIn(List<Date> values) {
            addCriterionForJDBCDate("qr_code_expires not in", values, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("qr_code_expires between", value1, value2, "qrCodeExpires");
            return (Criteria) this;
        }

        public Criteria andQrCodeExpiresNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("qr_code_expires not between", value1, value2, "qrCodeExpires");
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