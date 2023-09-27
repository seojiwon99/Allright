package com.ar.lighthouse.admin.service;

import java.util.Date;

import lombok.Data;

@Data
public class SuspendVO {
	int suspendCode;
	int declareType;
	int declareCode;
	String memberId;
	String suspReason;
	Date suspRegdate;
	Date suspEnddate;
	int suspStatus;
	int suspDate;
}
