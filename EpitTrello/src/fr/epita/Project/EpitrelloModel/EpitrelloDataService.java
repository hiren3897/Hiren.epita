package fr.epita.Project.EpitrelloModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EpitrelloDataService {

	private List<User> users = new ArrayList<User>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<list> lists = new ArrayList<list>();

	// Add User
	public String addUser(String username) {

		if (!checkUser(username)) {
			users.add(new User(username));
			return "success";
		} else {
			return "User already Exists";
		}
	}

	// Check User
	public boolean checkUser(String user) {
		boolean isDuplicate = false;
		for (User tempUser : users) {
			if (tempUser.getUsername().equals(user)) {
				return !isDuplicate;
			}
		}
		return isDuplicate;

	}

	// Add List
	public String addList(String listname) {
		if (!checkList(listname)) {
			lists.add(new list(listname));
			return "success";
		} else {
			return "List Already exists";
		}

	}

	// check List
	public boolean checkList(String list) {
		boolean isDuplicate = false;
		for (list tempList : lists) {
			if (tempList.getListName().equals(list)) {
				return !isDuplicate;
			}
		}
		return isDuplicate;

	}

	// Add Task
	public String addTask(String listname, String taskname, int priority, int time, String description) {
		if (!checkList(taskname)) {
			tasks.add(new Task(listname, taskname, priority, time, description));
			return "success";
		} else {
			return "Task Already exists";
		}

	}

	// check List
	public boolean checkTask(String task) {
		boolean isDuplicate = false;
		for (Task tempTask : tasks) {
			if (tempTask.getTaskName().equals(task)) {
				return !isDuplicate;
			}
		}
		return isDuplicate;
	}

	// Edit Task
	public String editTask(String Task, int time, int priority, String description) {
		for (Task check : tasks) {
			if (check.getTaskName().equals(Task)) {
				check.setPriority(priority);
				check.setDescription(description);
				check.setEstimatedTime(time);
				return "success";
			}
		}
		return "Task Doesn't Exists";
	}

	// Assign Task
	public String assignTask(String task, String user) {
		Task TempTask = getTask(task);
		User TempUser = getUser(user);
		if (TempTask == null) {
			return "Task Doesn't exists";
		} else if (TempUser == null) {
			return "User Doesn't exists";
		} else {
			TempTask.assingnToUser(TempUser);
			return "success";
		}
	}

	// Get task By Name
	public Task getTask(String taskName) {
		for (Task availableTask : tasks) {
			if (availableTask.getTaskName().equals(taskName)) {
				return availableTask;
			}
		}
		return null;
	}

	// Get User By Name
	public User getUser(String UserName) {
		for (User availableUser : users) {
			if (availableUser.getUsername().equals(UserName)) {
				return availableUser;
			}
		}
		return null;
	}

	// Print Task and their details
	public String printTask(String name) {
		Task TempTask = getTask(name);
		String FormatedString = TempTask.getTaskName() + "\n" + TempTask.getDescription() + "\n" + "Priority: "
				+ TempTask.getPriority() + "\n" + "Estimated Time: " + TempTask.getEstimatedTime() + "\n";

		for (User tempUser : TempTask.getUserTask()) {
			FormatedString += "Assigned to: " + tempUser.getUsername() + "\n";

		}
		if (TempTask.getUserTask().size() == 0) {
			FormatedString += "Unassigned";
		}
		return FormatedString;

	}

	//
	public String completeTask(String taskname) {
		Task TempTask = getTask(taskname);
		if (TempTask != null) {
			TempTask.setCompleted(true);
			return "success";
		}
		return "Task Doesn't Exists";
	}

	public String printUsersByPerformance() {

		List<Task> DescendingUser = tasks.stream().sorted(Comparator.comparing(Task::getEstimatedTime).reversed())
				.collect(Collectors.toList());
		String usersBytime = "";
		for (Task userlist : DescendingUser) {
			for (User user : userlist.getUserTask()) {
				usersBytime += user.getUsername() + "\n";
			}
		}

		return usersBytime;
	}

	public String printUsersByWorkload() {

		List<Task> AscendingUser = tasks.stream().sorted(Comparator.comparing(Task::getEstimatedTime))
				.collect(Collectors.toList());
		String usersBytime = "";
		for (Task userlist : AscendingUser) {
			for (User user : userlist.getUserTask()) {
				usersBytime += user.getUsername() + "\n";
			}
		}

		return usersBytime;

	}

	public String printUnassignedTasksByPriority() {
		String formatedString = "";
		for (Task task : tasks) {
			if (task.getUserTask().size() == 0) {
				formatedString += task.getPriority() + " | " + task.getTaskName() + " | " + "Unassigned" + " | "
						+ task.getEstimatedTime() + "h \n";
			}
		}

		return formatedString;

	}

	public String deleteTask(String taskname) {
		Task Temptask = getTask(taskname);
		if (Temptask != null) {
			tasks.remove(Temptask);
			return "success";
		}
		return "Task Doesn't Available";
	}

	public String printAllUnfinishedTasksByPriority() {
		List<Task> AscendbyPriority = tasks.stream().sorted(Comparator.comparing(Task::getPriority))
				.collect(Collectors.toList());
		String formatedString = "";
		for (Task tempTask : AscendbyPriority) {
			if (tempTask.isCompleted() == false) {
				formatedString += tempTask.getPriority() + " | " + tempTask.getTaskName() + " | ";
				for (User user : tempTask.getUserTask()) {
					formatedString += user.getUsername() + " | ";
				}
				if (tempTask.getUserTask().size() == 0) {
					formatedString += "Unassigned" + " | ";
				}
				formatedString += tempTask.getEstimatedTime() + "h \n";
			}
		}
		return formatedString;
	}

	public String moveTask(String task, String list) {
		Task tempTask = getTask(task);
		list tempList = getList(list);

		if (tempTask == null) {
			return "task doesn't exists";
		} else if (tempList == null) {
			return "List Doesn't Exists";
		} else {
			tempTask.setListname(list);
			return "success";
		}

	}

	public list getList(String listName) {
		for (list availablelist : lists) {
			if (availablelist.getListName().equals(listName)) {
				return availablelist;
			}
		}
		return null;
	}

	public String printList(String list) {

		list tempList = getList(list);
		String FormatedString = "List " + tempList.getListName() + "\n";
		for (Task TempTask : tasks) {
			if (TempTask.getListname().equals(list)) {
				FormatedString += TempTask.getPriority() + " | " + TempTask.getTaskName() + " | ";
				for (User user : TempTask.getUserTask()) {
					FormatedString += user.getUsername() + " | ";
				}
				if (TempTask.getUserTask().size() == 0) {
					FormatedString += "Unassigned" + " | ";
				}
				FormatedString += TempTask.getEstimatedTime() + "h \n";
			}
		}
		return FormatedString;

	}

	public String printAllLists() {
		String FormatedString = "";
		for (list templist : lists) {
			FormatedString += "List " + templist.getListName() + "\n";
			for (Task TempTask : tasks) {
				if (TempTask.getListname().equals(templist.getListName())) {
					FormatedString += TempTask.getPriority() + " | " + TempTask.getTaskName() + " | ";
					for (User user : TempTask.getUserTask()) {
						FormatedString += user.getUsername() + " | ";
					}
					if (TempTask.getUserTask().size() == 0) {
						FormatedString += "Unassigned" + " | ";
					}
					FormatedString += TempTask.getEstimatedTime() + "h \n";
				}
			}
			FormatedString += "\n";
		}
		return FormatedString;

	}
	
	public String printUserTasks(String username) {
		User TempUser = getUser(username);
		
		String AlluserswithTask ="";
		if(TempUser != null) {
			for (Task tempTask : tasks) {
				
				for(User Tuser : tempTask.getUserTask()) {
					if(Tuser.getUsername().equals(username)){
					 AlluserswithTask += tempTask.getPriority() + " | " + tempTask.getTaskName() + " | ";
					 AlluserswithTask += Tuser.getUsername() + " | ";
					 AlluserswithTask += tempTask.getEstimatedTime() + "h \n";	
				}
				}
				
			}
		}
		return AlluserswithTask;		
		
	}
	


}
