package fr.epita.Project.EpitrelloModel;

import java.util.ArrayList;
import java.util.List;

public class Task {

	private String listname;
	private String taskName;
	private int priority;
	private int estimatedTime;
	private String description;
	private List<User> UserTask;
	private boolean isCompleted;

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
	/**
	 * Constructor
	 * @param listname
	 * @param taskName
	 * @param estimatedTime
	 * @param priority
	 * @param description
	 */
	public Task(String listname, String taskName, int estimatedTime, int priority, String description) {

		this.listname = listname;
		this.taskName = taskName;
		this.priority = priority;
		this.estimatedTime = estimatedTime;
		this.description = description;
		this.UserTask = new ArrayList<User>();
		this.isCompleted = false;
	}

	public List<User> getUserTask() {
		return UserTask;
	}

	public void assingnToUser(User user) {
		UserTask.add(user);
	}

}
