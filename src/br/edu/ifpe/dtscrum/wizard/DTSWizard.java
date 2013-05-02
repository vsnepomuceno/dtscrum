package br.edu.ifpe.dtscrum.wizard;

import org.eclipse.jface.wizard.Wizard;

public class DTSWizard extends Wizard {

	protected DTSWizard_0 zero;
	protected DTSWizard_1 one;
	protected DTSWizard_2 two;
	protected DTSWizard_3 three;

	public DTSWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		zero = new DTSWizard_0();
		one = new DTSWizard_1();
		two = new DTSWizard_2();
		three = new DTSWizard_3();
		addPage(zero);
		addPage(one);
		addPage(two);
		addPage(three);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
