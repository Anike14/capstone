package com.fdu.capstone.models;

public class TaskPrerequisite {
	private String owner;
	private String taskId;
	private String taskName;
	private boolean achieved;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public boolean isAchieved() {
		return achieved;
	}
	public void setAchieved(boolean achieved) {
		this.achieved = achieved;
	}
}
