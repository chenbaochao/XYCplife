package com.sjzxywlkj.cplife.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.alipay.request.entity.BasicserviceInitialize;
import com.sjzxywlkj.cplife.alipay.request.entity.BasicserviceModify;
import com.sjzxywlkj.cplife.alipay.request.entity.CommunityBatchquery;
import com.sjzxywlkj.cplife.alipay.request.entity.CommunityCreate;
import com.sjzxywlkj.cplife.alipay.request.entity.CommunityDetailsQuery;
import com.sjzxywlkj.cplife.alipay.request.entity.CommunityModify;
import com.sjzxywlkj.cplife.alipay.response.entity.BasicserviceInitializeResult;
import com.sjzxywlkj.cplife.alipay.response.entity.BasicserviceModifyResult;
import com.sjzxywlkj.cplife.alipay.response.entity.CommunityBatchqueryResult;
import com.sjzxywlkj.cplife.alipay.response.entity.CommunityCreateResult;
import com.sjzxywlkj.cplife.alipay.response.entity.CommunityDetailsQueryResult;
import com.sjzxywlkj.cplife.alipay.response.entity.CommunityModifyResult;
import com.sjzxywlkj.cplife.alipay.response.entity.PublicParameters;
import com.sjzxywlkj.cplife.alipay.result.BasicserviceAlipay;
import com.sjzxywlkj.cplife.alipay.result.CommunityAlipay;
import com.sjzxywlkj.cplife.pojo.Community;
import com.sjzxywlkj.cplife.service.CommunityService;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @ClassName :CommunityController
 * @date 2018/3/10
 */
@Controller
@RequestMapping(value = "/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityName
	 * @param communityAddress
	 * @param districtCode
	 * @param communityLocations
	 * @param hotline
	 * @param merchantPid
	 * @param erviceExpires
	 * @return String JSON
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCommunity")
	@ResponseBody
	public String addCommunity(
			@RequestParam(value = "communityName", required = false, defaultValue = "") String communityName,
			@RequestParam(value = "communityAddress", required = false, defaultValue = "") String communityAddress,
			@RequestParam(value = "districtCode", required = false, defaultValue = "") String districtCode,
			@RequestParam(value = "communityLocations", required = false, defaultValue = "") String communityLocations,
			@RequestParam(value = "hotline", required = false, defaultValue = "") String hotline,
			@RequestParam(value = "merchantPid", required = false, defaultValue = "") String merchantPid,
			@RequestParam(value = "erviceExpires", required = false, defaultValue = "") String erviceExpires)
			throws Exception {
		Community community = new Community();
		CommunityCreate communityCreate = new CommunityCreate();
		communityCreate.setCommunity_name(communityName);
		communityCreate.setCommunity_address(communityAddress);
		String provinceCode = communityService.provinceCode(districtCode);
		communityCreate.setProvince_code(provinceCode);
		String cityCode = communityService.cityCode(districtCode);
		communityCreate.setCity_code(cityCode);
		communityCreate.setDistrict_code(districtCode);
		Set<String> locationsSet = new HashSet<String>();
		locationsSet.add(communityLocations);
		communityCreate.setCommunity_locations(locationsSet);
		communityCreate.setAssociated_pois(null);
		communityCreate.setOut_community_id(null);
		communityCreate.setHotline(hotline);
		CommunityAlipay communityAlipay = new CommunityAlipay();
		Map<String, Object> map = communityAlipay.create(communityCreate);
		if (map != null) {
			CommunityCreateResult communityCreateResult = (CommunityCreateResult) map.get("createResult");
			String communityId = communityCreateResult.getCommunity_id();
			if (communityId != null) {
				String status = communityCreateResult.getStatus();
				community.setCommunityId(communityId);
				community.setMerchantPid(merchantPid);
				community.setCommunityName(communityName);
				community.setDistrictCode(districtCode);
				community.setCommunityAddress(communityAddress);
				community.setHotline(hotline);
				community.setStatus(status);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				community.setErviceExpires(sdf.parse(erviceExpires));
				communityService.addCommunity(community);
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(communityId);
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("添加小区失败，请重新添加小区！");
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @param communityName
	 * @param communityAddress
	 * @param districtCode
	 * @param communityLocations
	 * @param hotline
	 * @return String JSON
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyCommunityById")
	@ResponseBody
	public String modifyCommunityById(
			@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId,
			@RequestParam(value = "communityName", required = false, defaultValue = "") String communityName,
			@RequestParam(value = "communityAddress", required = false, defaultValue = "") String communityAddress,
			@RequestParam(value = "districtCode", required = false, defaultValue = "") String districtCode,
			@RequestParam(value = "communityLocations", required = false, defaultValue = "") String communityLocations,
			@RequestParam(value = "hotline", required = false, defaultValue = "") String hotline) throws Exception {
		CommunityModify communityModify = new CommunityModify();
		Community community = new Community();
		communityModify.setCommunity_id(communityId);
		communityModify.setCommunity_name(communityName);
		communityModify.setCommunity_address(communityAddress);
		String provinceCode = communityService.provinceCode(districtCode);
		communityModify.setProvince_code(provinceCode);
		String cityCode = communityService.cityCode(districtCode);
		communityModify.setCity_code(cityCode);
		communityModify.setDistrict_code(districtCode);
		Set<String> locationsSet = new HashSet<String>();
		locationsSet.add(communityLocations);
		communityModify.setCommunity_locations(locationsSet);
		communityModify.setAssociated_pois(null);
		communityModify.setHotline(hotline);
		communityModify.setOut_community_id(null);
		CommunityAlipay communityAlipay = new CommunityAlipay();
		/*ModelAndView mav = new ModelAndView("/communityModifylist.jsp");*/
		Map<String, Object> map = communityAlipay.modify(communityModify);
		if (map != null) {
			CommunityModifyResult modifyResult = (CommunityModifyResult) map.get("modifyResult");
			if (modifyResult.getStatus() != null) {
				community.setCommunityId(communityId);
				community.setCommunityName(communityName);
				community.setDistrictCode(districtCode);
				community.setCommunityAddress(communityAddress);
				community.setHotline(hotline);
				community.setStatus(modifyResult.getStatus());
				communityService.modifyCommunityById(community);
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString("成功！");
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("修改小区信息失败，请重新修改小区信息！");
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @return String JSON
	 * @throws AlipayApiException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectByCommunityId")
	@ResponseBody
	public String selectByCommunityId(@RequestParam("communityId") String communityId)
			throws AlipayApiException, JsonGenerationException, JsonMappingException, IOException {
		/*CommunityAlipay communityAlipay = new CommunityAlipay();
		CommunityDetailsQuery communityDetailsQuery = new CommunityDetailsQuery();
		communityDetailsQuery.setCommunity_id(communityId);
		Map<String, Object> map = communityAlipay.query(communityDetailsQuery);
		if (map != null) {
			CommunityDetailsQueryResult communityDetailsQueryResult = (CommunityDetailsQueryResult) map
					.get("detailsQueryResult");
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(communityDetailsQueryResult);
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("查询小区信息失败，您查询的小区信息不存在！");
		}*/
		List<Community> cList = new ArrayList<Community>();
		Community community = communityService.selectByPrimaryKey(communityId);
		cList.add(community);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(cList);
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @param serviceType
	 * @param externalInvokeAddress
	 * @param accountType
	 * @param account
	 * @param serviceExpires
	 * @return String JSON
	 * @throws AlipayApiException
	 * @throws ParseException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addInitializationCommunity")
	@ResponseBody
	public String addInitializationCommunity(
			@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId,
			@RequestParam(value = "serviceType", required = false, defaultValue = "") String serviceType,
			@RequestParam(value = "externalInvokeAddress", required = false, defaultValue = "") String externalInvokeAddress,
			@RequestParam(value = "accountType", required = false, defaultValue = "") String accountType,
			@RequestParam(value = "account", required = false, defaultValue = "") String account,
			@RequestParam(value = "serviceExpires", required = false, defaultValue = "") String serviceExpires)
			throws AlipayApiException, ParseException, JsonGenerationException, JsonMappingException, IOException {
		BasicserviceInitialize basicserviceInitialize = new BasicserviceInitialize();
		basicserviceInitialize.setCommunity_id(communityId);
		basicserviceInitialize.setService_type(serviceType);
		basicserviceInitialize.setExternal_invoke_address(externalInvokeAddress);
		basicserviceInitialize.setAccount_type(accountType);
		basicserviceInitialize.setAccount(account);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = sdf.parse(serviceExpires);
		basicserviceInitialize.setService_expires(date);
		BasicserviceAlipay basicserviceAlipay = new BasicserviceAlipay();
		Map<String, Object> map = basicserviceAlipay.initialize(basicserviceInitialize);

		if (map != null) {
			BasicserviceInitializeResult basicserviceInitializeResult = (BasicserviceInitializeResult) map
					.get("basicserviceInitializeResult");
			if (basicserviceInitializeResult.getStatus() != null) {
				Community community = new Community();
				community.setCommunityId(communityId);
				community.setErviceExpires(sdf.parse(serviceExpires));
				community.setAccount(account);
				communityService.modifyServiceExpires(community);
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(basicserviceInitializeResult);
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("初始化小区服务失败，请重新初始化小区服务！");
		}
	}
	
	@RequestMapping(value = "/line")
	@ResponseBody
	public String line(@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId)
			throws AlipayApiException, JsonGenerationException, JsonMappingException, IOException {
		CommunityAlipay communityAlipay = new CommunityAlipay();
		CommunityDetailsQuery communityDetailsQuery = new CommunityDetailsQuery();
		communityDetailsQuery.setCommunity_id(communityId);
		Map<String, Object> map = communityAlipay.query(communityDetailsQuery);
		if (map != null) {
			CommunityDetailsQueryResult communityDetailsQueryResult = (CommunityDetailsQueryResult) map
					.get("detailsQueryResult");
			String status = communityDetailsQueryResult.getCommunity_status();
			BasicserviceAlipay basicserviceAlipay = new BasicserviceAlipay();
			BasicserviceModify basicserviceModify = new BasicserviceModify();
			basicserviceModify.setCommunity_id(communityId);
			basicserviceModify.setService_type("PROPERTY_PAY_BILL_MODE");
			Community community = new Community();
			if (status.equals("OFFLINE") || status.equals("PENDING_ONLINE")) {
				basicserviceModify.setStatus("ONLINE");

				Map<String, Object> statusMap = BasicserviceAlipay.modify(basicserviceModify);
				if (statusMap != null) {
					BasicserviceModifyResult basicserviceModifyResult = (BasicserviceModifyResult) statusMap
							.get("basicserviceModifyResult");
					System.out.println(basicserviceModifyResult);
					System.out.println(basicserviceModifyResult.getStatus());
					System.out.println(basicserviceModifyResult.getNext_action());
					if (basicserviceModifyResult.getStatus() != null) {
						community.setCommunityId(communityId);
						community.setStatus("ONLINE");
						communityService.modifyCommunityByStatus(community);
						System.out.println("上线成功");
						ObjectMapper objectMapper = new ObjectMapper();
						return objectMapper.writeValueAsString("上线成功！");
					} else {
						ObjectMapper objectMapper = new ObjectMapper();
						return objectMapper.writeValueAsString("上线失败！");
					}
				}
			} else if (status.equals("ONLINE")) {
				basicserviceModify.setStatus("OFFLINE");
				Map<String, Object> statusMap = BasicserviceAlipay.modify(basicserviceModify);
				if (statusMap != null) {
					BasicserviceModifyResult basicserviceModifyResult = (BasicserviceModifyResult) statusMap
							.get("basicserviceModifyResult");
					if (basicserviceModifyResult.getStatus() != null) {
						community.setCommunityId(communityId);
						community.setStatus("OFFLINE");
						communityService.modifyCommunityByStatus(community);
						System.out.println("下线成功");
						ObjectMapper objectMapper = new ObjectMapper();
						return objectMapper.writeValueAsString("下线成功！");
					} else {
						ObjectMapper objectMapper = new ObjectMapper();
						return objectMapper.writeValueAsString("下线失败！");
					}
				}
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString("查询小区信息失败，您查询的小区信息不存在！");
	}
	
	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @param serviceType
	 * @param status
	 * @param externalInvokeAddress
	 * @param accountType
	 * @param account
	 * @param serviceExpires
	 * @return String JSON
	 * @throws ParseException
	 * @throws AlipayApiException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/basicserviceModify")
	@ResponseBody
	public String basicserviceModify(
			@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId,
			@RequestParam(value = "serviceType", required = false, defaultValue = "") String serviceType,
			@RequestParam(value = "status", required = false, defaultValue = "") String status,
			@RequestParam(value = "externalInvokeAddress", required = false, defaultValue = "") String externalInvokeAddress,
			@RequestParam(value = "accountType", required = false, defaultValue = "") String accountType,
			@RequestParam(value = "account", required = false, defaultValue = "") String account,
			@RequestParam(value = "serviceExpires", required = false, defaultValue = "") String serviceExpires)
			throws ParseException, AlipayApiException, JsonGenerationException, JsonMappingException, IOException {
		BasicserviceModify basicserviceModify = new BasicserviceModify();
		basicserviceModify.setCommunity_id(communityId);
		basicserviceModify.setService_type(serviceType);
		basicserviceModify.setStatus(status);
		basicserviceModify.setExternal_invoke_address(externalInvokeAddress);
		basicserviceModify.setAccount_type(accountType);
		basicserviceModify.setAccount(account);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(serviceExpires);
		basicserviceModify.setService_expires(date);
		BasicserviceAlipay basicserviceAlipay = new BasicserviceAlipay();
		Map<String, Object> map = basicserviceAlipay.modify(basicserviceModify);
		if (map != null) {
			BasicserviceModifyResult basicserviceModifyResult = (BasicserviceModifyResult) map
					.get("basicserviceModifyResult");
			if (basicserviceModifyResult.getStatus() != null) {
				Community community = new Community();
				community.setCommunityId(communityId);
				community.setStatus(basicserviceModifyResult.getStatus());
				communityService.modifyCommunityByStatus(community);
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(basicserviceModifyResult);
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("修改初始化小区服务失败，您要修改的初始化小区不存在！");
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param merchantPid
	 * @return String JSON
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectMerchantPid")
	@ResponseBody
	public String selectMerchantPid(
			@RequestParam(value = "merchantPid", required = false, defaultValue = "") String merchantPid)
			throws JsonGenerationException, JsonMappingException, IOException {
		Community community = new Community();
		community.setMerchantPid(merchantPid);
		List<Community> list = communityService.selectMerchantPid(community);
		if (list != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(list);
		} else {
			return "该支付宝账户下没有物业小区！";
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @return String JSON
	 * @throws AlipayApiException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/qrCodeModify")
	@ResponseBody
	public String qrCodeModify(
			@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId)
			throws AlipayApiException, JsonGenerationException, JsonMappingException, IOException {
		CommunityAlipay communityAlipay = new CommunityAlipay();
		CommunityDetailsQuery communityDetailsQuery = new CommunityDetailsQuery();
		communityDetailsQuery.setCommunity_id(communityId);
		Map<String, Object> map = communityAlipay.query(communityDetailsQuery);
		if (map != null) {
			CommunityDetailsQueryResult communityDetailsQueryResult = (CommunityDetailsQueryResult) map
					.get("detailsQueryResult");
			if (communityDetailsQueryResult.getQr_code_image() != null) {
				Community community = new Community();
				community.setCommunityId(communityId);
				community.setQrCodeImage(communityDetailsQueryResult.getQr_code_image());
				communityService.modifyCommunityQrCodeImageById(community);
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(communityDetailsQueryResult.getQr_code_image());
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("您要更新的用户不存在，请重新进行查找用户，进行二维码的更新！");
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return String JSON
	 * @throws AlipayApiException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/communityIdList")
	@ResponseBody
	public String communityIdList(@RequestParam(value = "status", required = false, defaultValue = "") String status,
			@RequestParam(value = "pageNum", required = false, defaultValue = "") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "") Integer pageSize)
			throws AlipayApiException, JsonGenerationException, JsonMappingException, IOException {
		CommunityBatchquery communityBatchquery = new CommunityBatchquery();
		communityBatchquery.setStatus(status);
		communityBatchquery.setPage_num(pageNum);
		communityBatchquery.setPage_size(pageSize);
		CommunityAlipay communityAlipay = new CommunityAlipay();
		Map<String, Object> map = communityAlipay.batchquery(communityBatchquery);
		if (map != null) {
			CommunityBatchqueryResult communityBatchqueryResult = (CommunityBatchqueryResult) map
					.get("communityBatchqueryResult");
			if (communityBatchqueryResult.getCommunity_list() != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(communityBatchqueryResult);
			} else {
				PublicParameters parameters = (PublicParameters) map.get("parameters");
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(parameters.getSub_msg());
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString("批量查询支付宝小区编号失败！");
		}
	}

	/**
	 * Fuction description goes here.
	 * 
	 * @param communityId
	 * @return String JSON
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/selectAccountById")
	@ResponseBody
	public String selectAccountById(
			@RequestParam(value = "communityId", required = false, defaultValue = "") String communityId)
			throws JsonGenerationException, JsonMappingException, IOException {
		Community community = new Community();
		community.setCommunityId(communityId);
		String list = communityService.selectAccountById(community);
		if (list != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(list);
		} else {
			return "您查询的物业小区不存在，请重新进行查询！";
		}
	}
	
	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public String selectAll(String communityId, String merchantPid, String communityName, String districtCode,
			String communityAddress, String hotline, String status, String erviceExpires, String qrCodeImage,
			String qrCodeExpires, String account) throws JsonGenerationException, JsonMappingException, IOException {

		List<Community> list = communityService.selectAll(communityId, merchantPid, communityName, districtCode,
				communityAddress, hotline, status, erviceExpires, qrCodeImage, qrCodeExpires, account);
		if (list != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(list);
		} else {
			return null;
		}
	}

}