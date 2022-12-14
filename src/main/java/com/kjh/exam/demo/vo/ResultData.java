package com.kjh.exam.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	// S-1, F-1, F-2 ...
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;
	@Getter
	private DT data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		return rd;
	}
	
	public boolean isSuccess() {
		// S로 시작하는 문자가 온다면 성공했다라는 것을 알려주기 위한 것
		return this.resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		// S로 시작하는 문자가 온다면 실패했다라는 것을 알려주기 위한 것
		return isSuccess() == false;
	}

	public void setData2(String data2Name, Object data2) {
		this.data2Name = data2Name;
		this.data2 = data2;
	}

	
	
}
