package com.fdu.capstone.models;

import java.sql.Date;

public class Project {
	private String owner;
	private String name;
    private Date startedDate;
    private Date expectedDueDate;
    private int difficult;
    
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartedDate() {
		return startedDate;
	}
	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
	public Date getExpectedDueDate() {
		return expectedDueDate;
	}
	public void setExpectedDueDate(Date expectedDueDate) {
		this.expectedDueDate = expectedDueDate;
	}
	public int getDifficult() {
		return difficult;
	}
	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}
}
