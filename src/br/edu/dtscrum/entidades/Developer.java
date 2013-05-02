package br.edu.dtscrum.entidades;

import java.util.ArrayList;

public class Developer {

	private String name;
	private Seniority senior;
	private ArrayList<Task> tasks;
	
	public Developer(String name, Seniority senior) {
		this.name = name;
		this.senior = senior;
		tasks = new ArrayList<Task>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Seniority getSenior() {
		return senior;
	}

	public void setSenior(Seniority senior) {
		this.senior = senior;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public void removeTask(Task task){
		this.tasks.remove(task);
	}
	
}
