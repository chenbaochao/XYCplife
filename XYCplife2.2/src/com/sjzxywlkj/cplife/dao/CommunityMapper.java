package com.sjzxywlkj.cplife.dao;

import com.sjzxywlkj.cplife.pojo.Community;
import com.sjzxywlkj.cplife.pojo.CommunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommunityMapper {
	
	int countByExample(CommunityExample example);

	int deleteByExample(CommunityExample example);

	int deleteByPrimaryKey(String communityId);

	int insert(Community record);

	int insertSelective(Community record);

	List<Community> selectByExample(CommunityExample example);

	Community selectByPrimaryKey(String communityId);

	int updateByExampleSelective(@Param("record") Community record, @Param("example") CommunityExample example);

	int updateByExample(@Param("record") Community record, @Param("example") CommunityExample example);

	int updateByPrimaryKeySelective(Community record);

	int updateByPrimaryKey(Community record);

	int addCommunity(Community record);

	List<Community> selectByCommunityName(String communityName);

	List<Community> selectByDistrictCode(String districtCode);

	List<Community> selectByStatus(String status);

	List<Community> selectByCommunityId(String communityId);

	int modifyCommunityById(Community record);

	void modifyServiceExpires(Community community);

	void modifyCommunityByStatus(Community community);

	List<Community> selectMerchantPid(Community community);

	void modifyCommunityQrCodeImageById(Community community);
	
	List<Community> selectAll(@Param("communityId") String communityId, @Param("merchantPid") String merchantPid,
			@Param("communityName") String communityName, @Param("districtCode") String districtCode,
			@Param("communityAddress") String communityAddress, @Param("hotline") String hotline,
			@Param("status") String status, @Param("erviceExpires") String erviceExpires,
			@Param("qrCodeImage") String qrCodeImage, @Param("qrCodeExpires") String qrCodeExpires,
			@Param("account") String account);

	String selectAccountById(Community community);
}