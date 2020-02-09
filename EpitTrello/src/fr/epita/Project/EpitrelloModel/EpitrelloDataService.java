package fr.epita.Project.EpitrelloModel;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class EpitrelloDataService {
	
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	   static final String USER = "sa"; 
	   static final String PASS = "1234"; 
	   
	private static final String INSERT_QUERY = "INSERT INTO EPITRELLO_USERS(USERNAME) VALUES (?)";
	static String filename = "Epitrello.txt";
	private List<User> users = new ArrayList<User>();
	private List<Task> tasks = new ArrayList<Task>();
	private List<list> lists = new ArrayList<list>();
	public PrintWriter pw;
	

	/**
	 * addUser();
	 * @param username
	 * @return success if it's a valid user with unique name
	 */
	public String addUser(String username) {

		if (!checkUser(username)) {
			users.add(new User(username));
			return "success";
		} else {
			return "User already Exists";
		}
	}

	/**
	 * Check User
	 * check the user if it already exists. 
	 * @param user
	 * @return 
	 */
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
	public String printTask(String name) throws IOException {
		Task TempTask = getTask(name);
		String FormatedString = TempTask.getTaskName() + "\n" + TempTask.getDescription() + "\n" + "Priority: "
				+ TempTask.getPriority() + "\n" + "Estimated Time: " + TempTask.getEstimatedTime() + "\n";

		for (User tempUser : TempTask.getUserTask()) {
			FormatedString += "Assigned to: " + tempUser.getUsername() + "\n";

		}
		if (TempTask.getUserTask().size() == 0) {
			FormatedString += "Unassigned";
		}
		pw = new PrintWriter(new FileWriter(filename),true);
		pw.println(FormatedString);
		
		return FormatedString;

	}

	//Complete Task
	public String completeTask(String taskname) {
		Task TempTask = getTask(taskname);
		if (TempTask != null) {
			TempTask.setCompleted(true);
			return "success";
		}
		return "Task Doesn't Exists";
	}

	//Print User By Performance
	public String printUsersByPerformance() throws IOException {

		List<Task> DescendingUser = tasks.stream().sorted(Comparator.comparing(Task::getEstimatedTime).reversed())
				.collect(Collectors.toList());
		String usersBytime = "";
		for (Task userlist : DescendingUser) {
			for (User user : userlist.getUserTask()) {
				usersBytime += user.getUsername() + "\n";
			}
		}
		pw.println(usersBytime);
		return usersBytime;
	}
	
	//Print User by WorkLoad
	public String printUsersByWorkload() throws IOException {

		List<Task> AscendingUser = tasks.stream().sorted(Comparator.comparing(Task::getEstimatedTime))
				.collect(Collectors.toList());
		String usersBytime = "";
		for (Task userlist : AscendingUser) {
			for (User user : userlist.getUserTask()) {
				usersBytime += user.getUsername() + "\n";
			}
		}
		pw.println(usersBytime);
		return usersBytime;

	}
	
	/***
	 * 
	 * @return: All unassigned Task by priority
	 * @throws IOException 
	 */
	public String printUnassignedTasksByPriority() throws IOException {
		String formatedString = "";
		for (Task task : tasks) {
			if (task.getUserTask().size() == 0) {
				formatedString += task.getPriority() + " | " + task.getTaskName() + " | " + "Unassigned" + " | "
						+ task.getEstimatedTime() + "h \n";
			}
		}
		pw.println(formatedString);
		return formatedString;

	}
	
	/**
	 * delete task form List
	 * @param taskname
	 * @return success if list exists and deleted.
	 */
	public String deleteTask(String taskname) {
		Task Temptask = getTask(taskname);
		if (Temptask != null) {
			tasks.remove(Temptask);
			return "success";
		}
		return "Task Doesn't Available";
	}
	
	
	/**
	 * print All Unfinished Tasks By Priority.
	 * It sort the task by proirity
	 * @return all task that are unassigned to the user
	 * @throws IOException 
	 */
	public String printAllUnfinishedTasksByPriority() throws IOException {
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
		pw.println(formatedString);
		return formatedString;
	}
	
	/**
	 * moveTask() it moves the task to another List.
	 * @param task
	 * @param list
	 * @return success if task & list exists and move.
	 */
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
	
	/**
	 * it get the required list from the lists
	 * @param listName
	 * @return required list
	 */
	public list getList(String listName) {
		for (list availablelist : lists) {
			if (availablelist.getListName().equals(listName)) {
				return availablelist;
			}
		}
		return null;
	}

	/**
	 * printList() it prints all the specific task from the list
	 * @param list
	 * @return formattedstring that contains alltask in list.
	 * @throws IOException 
	 */
	public String printList(String list) throws IOException {

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
		pw.println(FormatedString);
		return FormatedString;

	}
	
	/**
	 * it prints all the existing lists and their tasks.
	 * @return
	 * @throws IOException 
	 */
	public String printAllLists() throws IOException {
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
		pw.println(FormatedString);
		return FormatedString;

	}
	
	/**
	 * prints all user that are assigned task
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public String printUserTasks(String username) throws IOException {
		User TempUser = getUser(username);

		String AlluserswithTask = "";
		if (TempUser != null) {
			for (Task tempTask : tasks) {

				for (User Tuser : tempTask.getUserTask()) {
					if (Tuser.getUsername().equals(username)) {
						AlluserswithTask += tempTask.getPriority() + " | " + tempTask.getTaskName() + " | ";
						AlluserswithTask += Tuser.getUsername() + " | ";
						AlluserswithTask += tempTask.getEstimatedTime() + "h \n";
					}
				}

			}
		}
		pw.println(AlluserswithTask);
		pw.flush();
		pw.close();
		return AlluserswithTask;

	}
	
	public int Insert() {
		
		try {
			Class.forName(JDBC_DRIVER);
			
			 //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	        boolean status = conn == null ? false : true;
	        System.out.println("Database connected: "+status);
	        PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY);
	        for (User user : users) {
	        	preparedStatement.setString(1,user.getUsername());
	        	 preparedStatement.executeUpdate();
			}
	    
	       

			
		} catch (Exception e) {
			//throw a custom exception
			e.printStackTrace();
		}
		return 0;

	}


}