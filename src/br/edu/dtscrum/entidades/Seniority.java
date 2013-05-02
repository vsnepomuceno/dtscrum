package br.edu.dtscrum.entidades;

public enum Seniority {

	INTERN (1), TREINEE (2), JUNIOR_ANALYST(3), ANALYST (4), SENIOR_ANALYST (5);
		
	private int senior;

	Seniority(int senior) {
		this.senior = senior;
	}
	
	
	public int getSeniority() {
		return this.senior;
	}
	
}
