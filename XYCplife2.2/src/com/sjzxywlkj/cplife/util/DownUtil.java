package com.sjzxywlkj.cplife.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownUtil {
	
	

	public static String processFileName(HttpServletRequest request, String fileNames) {
		   String codedfilename = null;
		   try {
		      String agent = request.getHeader("USER-AGENT");
		      if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
		            && -1 != agent.indexOf("Trident"))
		      {//ie
		         String name = java.net.URLEncoder.encode(fileNames, "UTF8");
		         codedfilename = name;
		      } else if (null != agent && -1 != agent.indexOf("Mozilla")) 
		      {// 火狐,chrome等
		         codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
		      }
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return codedfilename;
		}

		public static void down(String fileName,ByteArrayOutputStream out,HttpServletRequest request,HttpServletResponse response) throws Exception {
			byte[] b = out.toByteArray();
	    	ByteArrayInputStream in = new ByteArrayInputStream(b);
	        //设置响应头和客户端保存文件名
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("multipart/form-data");
	        fileName = processFileName( request,  fileName);
	        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
	        try {
	            //打开本地文件流
	            InputStream inputStream = in;
	            //激活下载操作
	            OutputStream os = response.getOutputStream();

	            //循环写入输出流
	            byte[] b1 = new byte[2048];
	            int length;
	            while ((length = inputStream.read(b1)) > 0) {
	                os.write(b1, 0, length);
	            }

	            // 这里主要关闭。
	            os.close();
	            inputStream.close();
	        } catch (Exception e){
	            throw e;
	        }
		}
}
