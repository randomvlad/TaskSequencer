package com.blogspot.evilnerdyowl.tasksequence;

import java.util.List;

public class Process {

	private String name;
	private List<Task> tasks;
	
	public Process( String name, List<Task> tasks ) {
		this.setName( name );
		this.setTasks( tasks );
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks( List<Task> tasks ) {
		this.tasks = tasks;
	}
}
