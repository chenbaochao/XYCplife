package com.sjzxywlkj.cplife.util;

public class JsonMsg {
		//自定义返回编码 
		private Integer code;
		//自定义返回信息
		private String msg;
		
		public JsonMsg(){}

		public JsonMsg(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
		
		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
}
