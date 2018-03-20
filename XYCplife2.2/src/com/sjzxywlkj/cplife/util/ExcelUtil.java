package com.sjzxywlkj.cplife.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sjzxywlkj.cplife.bean.BillShow;
import com.sjzxywlkj.cplife.pojo.Bill;
import com.sjzxywlkj.cplife.pojo.Roominfo;


public class ExcelUtil {

	public static Set<Bill> uploadBill(InputStream inputStream,String community_id) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Set<Bill> bSets = new HashSet<Bill>();
		// 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
		// 注意表格索引从 0 开始！
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 开始循环表格数据,表格的行索引从 0 开始
		// employees.xlsx 第一行是标题行，我们从第二行开始, 对应的行索引是 1
		// sheet.getLastRowNum() : 获取当前表格中最后一行数据对应的行索引
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
		// XSSFRow 代表一行数据
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
		continue;
		}
		Bill bill = new Bill();
		bill.setBill_entry_id(NumUtil.getBill_entry_id());
		bill.setOut_room_id(row.getCell(0).toString());
		bill.setCost_type(row.getCell(2).toString());
		bill.setBill_entry_amount(row.getCell(3).toString());
		bill.setAcct_period(row.getCell(4).toString());
		bill.setRelease_day(row.getCell(5).toString());
		bill.setDeadline(row.getCell(6).toString());
		bill.setCommunity_id(community_id);
		bill.setStatus("待缴费");
		bSets.add(bill);
		}
		// 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
		workbook.close(); 	
		return bSets;
	}
	public static ByteArrayOutputStream billCaseDown(List<Roominfo> rooms,String path) throws IOException {
		/*System.out.println(new File("WebRoot\\WEB-INF\\excel\\账单上传模板.xlsx").getAbsolutePath());*/
		FileInputStream excelFileInputStream = new FileInputStream(path+"\\添加账单模板.xlsx");
		// XSSFWorkbook 就代表一个 Excel 文件
		// 创建其对象，就打开这个 Excel 文件
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
		// 输入流使用后，及时关闭！这是文件流操作中极好的一个习惯！
		excelFileInputStream.close();
		// XSSFSheet 代表 Excel 文件中的一张表格
		// 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
		// 注意表格索引从 0 开始！
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int currentLastRowIndex = sheet.getLastRowNum();

		int newRowIndex = currentLastRowIndex + 1;
		
		XSSFRow newRow = null;

		Iterator<Roominfo> iterator = rooms.iterator();
		while (iterator.hasNext()) {
			Roominfo room = (Roominfo) iterator.next();
			
			newRow = sheet.createRow(newRowIndex);
			//开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
			int cellIndex = 0;
			// 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
			XSSFCell out_room_id = newRow.createCell(cellIndex++);
			out_room_id.setCellValue(room.getOutRoomId());
			XSSFCell address = newRow.createCell(cellIndex++);
			address.setCellValue(room.getAddress());
			newRowIndex++;
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		workbook.close();
		return os;
	}
	
	public static ByteArrayOutputStream billDown(List<BillShow> bShows,String path) throws IOException {
		/*System.out.println(new File("WebRoot\\WEB-INF\\excel\\账单上传模板.xlsx").getAbsolutePath());*/
		FileInputStream excelFileInputStream = new FileInputStream(path+"\\账单更新删除查看模板.xlsx");
		// XSSFWorkbook 就代表一个 Excel 文件
		// 创建其对象，就打开这个 Excel 文件
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
		// 输入流使用后，及时关闭！这是文件流操作中极好的一个习惯！
		excelFileInputStream.close();
		// XSSFSheet 代表 Excel 文件中的一张表格
		// 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
		// 注意表格索引从 0 开始！
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int currentLastRowIndex = sheet.getLastRowNum();

		int newRowIndex = currentLastRowIndex + 1;
		
		XSSFRow newRow = null;

		Iterator<BillShow> iterator = bShows.iterator();
		while (iterator.hasNext()) {
			BillShow billShow = iterator.next();
			newRow = sheet.createRow(newRowIndex);
			//开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
			int cellIndex = 0;
			// 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
			XSSFCell bill_entry_id = newRow.createCell(cellIndex++);
			bill_entry_id.setCellValue(billShow.getBill_entry_id());
			XSSFCell community_id = newRow.createCell(cellIndex++);
			community_id.setCellValue(billShow.getCommunity_id());
			XSSFCell community_name = newRow.createCell(cellIndex++);
			community_name.setCellValue(billShow.getCommunity_name());
			XSSFCell out_room_id = newRow.createCell(cellIndex++);
			out_room_id.setCellValue(billShow.getOut_room_id());
			XSSFCell room_address = newRow.createCell(cellIndex++);
			room_address.setCellValue(billShow.getRoom_address());
			XSSFCell cost_type = newRow.createCell(cellIndex++);
			cost_type.setCellValue(billShow.getCost_type());
			XSSFCell acct_period = newRow.createCell(cellIndex++);
			acct_period.setCellValue(billShow.getAcct_period());
			XSSFCell bill_entry_amount = newRow.createCell(cellIndex++);
			bill_entry_amount.setCellValue(billShow.getBill_entry_amount());
			XSSFCell release_day = newRow.createCell(cellIndex++);
			release_day.setCellValue(billShow.getRelease_day());
			XSSFCell deadline = newRow.createCell(cellIndex++);
			deadline.setCellValue(billShow.getDeadline());
			XSSFCell status = newRow.createCell(cellIndex++);
			status.setCellValue(billShow.getStatus());
			newRowIndex++;
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		workbook.close();
		return os;
	}
	public static Set<String> billDel(InputStream inputStream) throws IOException {
		Set<String> set = new HashSet<String>();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		// 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
		// 注意表格索引从 0 开始！
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 开始循环表格数据,表格的行索引从 0 开始
		// employees.xlsx 第一行是标题行，我们从第二行开始, 对应的行索引是 1
		// sheet.getLastRowNum() : 获取当前表格中最后一行数据对应的行索引
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
		// XSSFRow 代表一行数据
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
		continue;
		}
		String id = row.getCell(0).toString();
		set.add(id);
		}
		// 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
		workbook.close(); 	
		return set;
	}
}
