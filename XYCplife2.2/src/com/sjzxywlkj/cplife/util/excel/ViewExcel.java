package com.sjzxywlkj.cplife.util.excel;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class ViewExcel extends AbstractXlsView  {

	@Override
	public void buildExcelDocument(Map<String, Object> arg0, Workbook book,
			HttpServletRequest arg2, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		 String fileName = "下载列表.xls";  
	        response.setCharacterEncoding("UTF-8");  
	        response.setContentType("application/ms-excel");// 文件下载  
	        response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));  
	        OutputStream outputStream = response.getOutputStream();  
	  	  
	        book.write(outputStream);  
	        outputStream.flush();  
	        outputStream.close();  
		
	}

	
}
