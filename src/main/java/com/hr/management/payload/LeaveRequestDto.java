package com.hr.management.payload;

import java.time.LocalDate;

public class LeaveRequestDto {

	private LocalDate startDate;
	private LocalDate endDate;
	public LeaveRequestDto(LocalDate startDate, LocalDate enddate) {
		super();
		this.startDate = startDate;
		this.endDate = enddate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}

