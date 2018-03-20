package com.sjzxywlkj.cplife.pojo;

import java.util.Date;

public class Community {
	private String communityId;

	private String merchantPid;

	private String communityName;

	private String districtCode;

	private String communityAddress;

	private String hotline;

	private String status;

	private Date erviceExpires;

	private String qrCodeImage;

	private Date qrCodeExpires;

	private String account;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId == null ? null : communityId.trim();
	}

	public String getMerchantPid() {
		return merchantPid;
	}

	public void setMerchantPid(String merchantPid) {
		this.merchantPid = merchantPid == null ? null : merchantPid.trim();
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName == null ? null : communityName.trim();
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode == null ? null : districtCode.trim();
	}

	public String getCommunityAddress() {
		return communityAddress;
	}

	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress == null ? null : communityAddress.trim();
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline == null ? null : hotline.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getErviceExpires() {
		return erviceExpires;
	}

	public void setErviceExpires(Date erviceExpires) {
		this.erviceExpires = erviceExpires;
	}

	public String getQrCodeImage() {
		return qrCodeImage;
	}

	@Override
	public String toString() {
		return "Community [communityId=" + communityId + ", merchantPid=" + merchantPid + ", communityName="
				+ communityName + ", districtCode=" + districtCode + ", communityAddress=" + communityAddress
				+ ", hotline=" + hotline + ", status=" + status + ", erviceExpires=" + erviceExpires + ", qrCodeImage="
				+ qrCodeImage + ", qrCodeExpires=" + qrCodeExpires + "]";
	}

	public void setQrCodeImage(String qrCodeImage) {
		this.qrCodeImage = qrCodeImage == null ? null : qrCodeImage.trim();
	}

	public Date getQrCodeExpires() {
		return qrCodeExpires;
	}

	public void setQrCodeExpires(Date qrCodeExpires) {
		this.qrCodeExpires = qrCodeExpires;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}