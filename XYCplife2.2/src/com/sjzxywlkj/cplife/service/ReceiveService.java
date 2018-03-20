package com.sjzxywlkj.cplife.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sjzxywlkj.cplife.alipay.config.AlipayConfig;


@Service("receiveService")
public class ReceiveService implements IReceiveService {

	@Override
	public String Receive(Map<String,String> params) {
		// TODO Auto-generated method stub
		String bizContent = null;
		String result = null;
		try {
		    //验签
		    boolean checkResult =  AlipaySignature.rsaCheckV2(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);       
		    if (checkResult) {//验签成功
		        bizContent = "{\"econotify\":\"success\"}";
		    } else {
		        // 验签失败, 日志记录, 并反馈支付宝技术支持小二
		        /*LOG.error("物业缴费异步通知验签失败");*/
		    }
		} catch (Exception e) {
		    // 异常, 日志记录, 并反馈支付宝技术支持小二
		    /*LOG.error("物业缴费异步通知处理失败", e);*/
		}
		 
		// 不加密报文，加签后直接回送接收结果。
		// 仅表明已成功接收通知,支付宝不再重复发送通知。
		try {
			result = AlipaySignature.encryptAndSign(bizContent, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.RSA_PRIVATE_KEY,AlipayConfig.CHARSET,false,true, AlipayConfig.SIGNTYPE);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		//注意：开发者后续自行异步处理销账逻辑，业务处理成功与否不要影响上面回传成功接收通知的报文。
		return result;
	}

}
