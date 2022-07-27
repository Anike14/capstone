package com.fdu.capstone.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Task extends ObjectBase {
	
	private String owner;
	private String name;
    private Date startedDate;
    private Date expectedDueDate;
    private int difficult;
    private int status;
    private int progress;
    private List<TaskPrerequisite> prerequisites;
	
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
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public List<TaskPrerequisite> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(List<TaskPrerequisite> prerequisites) {
		this.prerequisites = prerequisites;
	}
}
