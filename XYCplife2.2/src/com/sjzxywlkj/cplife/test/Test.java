package com.sjzxywlkj.cplife.test;

import java.io.IOException;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.sjzxywlkj.cplife.alipay.request.entity.BillBatchquery;
import com.sjzxywlkj.cplife.alipay.response.entity.BillBatchqueryResult;
import com.sjzxywlkj.cplife.alipay.result.BillAlipay;


public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BillBatchquery billBatchquery = new BillBatchquery("AR3837GV84403", null, null, null, null, null, null, null, null);
		Map<String, Object> map = null;
		try {
			map = BillAlipay.batchQuery(billBatchquery);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BillBatchqueryResult billBatchqueryResult =	(BillBatchqueryResult) map.get("billBatchqueryResult");
		System.out.println(billBatchqueryResult.getBill_result_set());
	}
}
