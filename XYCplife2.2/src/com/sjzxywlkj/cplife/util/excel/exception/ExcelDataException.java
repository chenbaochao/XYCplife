package com.sjzxywlkj.cplife.util.excel.exception;

public class ExcelDataException extends Exception {

	public ExcelDataException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		ExcelDataException dataException=new ExcelDataException("hello world!");
		System.out.println(dataException.getMessage());
		
	}
}
