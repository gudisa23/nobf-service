package com.att.api.nobf.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleInstallation {

	private Date installationDate;
	
	private String arrivalTimeFrame;

	private String installInfo;
}
