package br.edu.dtscrum.entidades;

public class Task {

	private String name;
	private int timeTask;
	
	public Task(String name, int timeTask) {
		this.name = name;
		this.timeTask = timeTask;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimeTask() {
		return timeTask;
	}
	public void setTimeTask(int timeTask) {
		this.timeTask = timeTask;
	}	
}
