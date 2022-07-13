package com.fdu.capstone.models;

import java.sql.Date;
import java.util.List;

public class Project extends ObjectBase {
	
	private String owner;
	private String name;
    private Date startedDate;
    private Date expectedDueDate;
    private int difficult;
    private int status;
	private List<Task> tasks;
	
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
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
