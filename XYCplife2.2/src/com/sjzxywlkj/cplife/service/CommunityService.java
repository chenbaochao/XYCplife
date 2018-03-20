package com.sjzxywlkj.cplife.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.dao.CommunityMapper;
import com.sjzxywlkj.cplife.pojo.Community;

@Service
public class CommunityService {
	@Autowired
	private CommunityMapper communityMapper;

	public int addCommunity(Community record) {
		return communityMapper.addCommunity(record);
	}

	// 修改
	public int modifyCommunityById(Community record) throws AlipayApiException {
		// TODO Auto-generated method stub
		return communityMapper.modifyCommunityById(record);
	}
	public List<Community> selectAll(String communityId, String merchantPid, String communityName, String districtCode,
			String communityAddress, String hotline, String status, String erviceExpires, String qrCodeImage,
			String qrCodeExpires, String account) {
		return communityMapper.selectAll(communityId, merchantPid, communityName, districtCode, communityAddress,
				hotline, status, erviceExpires, qrCodeImage, qrCodeExpires, account);
	}
	public void modifyServiceExpires(Community community) {
		// TODO Auto-generated method stub
		communityMapper.modifyServiceExpires(community);
	}

	public void modifyCommunityByStatus(Community community) {
		// TODO Auto-generated method stub
		communityMapper.modifyCommunityByStatus(community);
	}

	public List<Community> selectMerchantPid(Community community) {
		// TODO Auto-generated method stub
		return communityMapper.selectMerchantPid(community);
	}

	public void modifyCommunityQrCodeImageById(Community community) {
		// TODO Auto-generated method stub
		communityMapper.modifyCommunityQrCodeImageById(community);
	}

	public String selectAccountById(Community community) {
		// TODO Auto-generated method stub
		return communityMapper.selectAccountById(community);
	}

	// 地级市编码
	public String cityCode(String districtCode) {
		districtCode = districtCode.substring(0, 4);
		StringBuilder cityCode = new StringBuilder();
		cityCode.append(districtCode);
		cityCode.append("00");
		return cityCode.toString();

	}

	// 省级编码
	public String provinceCode(String districtCode) {
		districtCode = districtCode.substring(0, 2);
		StringBuilder provinceCode = new StringBuilder();
		provinceCode.append(districtCode);
		provinceCode.append("0000");
		return provinceCode.toString();

	}
	
	public Community selectByPrimaryKey(String communityId) {
		return communityMapper.selectByPrimaryKey(communityId);
	}
}
