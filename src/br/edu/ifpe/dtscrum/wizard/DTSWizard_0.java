package br.edu.ifpe.dtscrum.wizard;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DTSWizard_0 extends WizardPage {

	private Composite container;
	private ArrayList<String> seniorities;

	Text internText;
	Text treineeText;
	Text juniorText;
	Text analystText;
	Text seniorText;

	/*
	 * 1,25 x dias executada por um Treinee; 1,5x por um estagiário; x dias por
	 * um Junior; 0,75x por um Pleno; e 0,5x por um Sênior
	 */
	public DTSWizard_0() {
		super("DTSWizard");
		setTitle("Development time by seniority.");
		setDescription("Put the development production time according with its seniority.");
		this.seniorities = new ArrayList<String>();
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;

		GridData gridData = new GridData();
		gridData.widthHint = 50;

		Label label1 = new Label(container, SWT.NULL);
		label1.setText("Intern:    ");
		// Intern
		internText = new Text(container, SWT.BORDER | SWT.SINGLE);
		internText.setLayoutData(gridData);
		internText.setText("1.0");
		addKeyListener(internText);

		// Treinee
		gridData = new GridData();
		gridData.widthHint = 50;

		Label labelTreinee = new Label(container, SWT.NULL);
		labelTreinee.setText("Treinee:  ");
		treineeText = new Text(container, SWT.BORDER | SWT.SINGLE);
		treineeText.setLayoutData(gridData);
		treineeText.setText("0.85");
		addKeyListener(treineeText);

		// junior
		gridData = new GridData();
		gridData.widthHint = 50;

		Label labelJunior = new Label(container, SWT.NULL);
		labelJunior.setText("Junior Analyst:  ");
		juniorText = new Text(container, SWT.BORDER | SWT.SINGLE);
		juniorText.setLayoutData(gridData);
		juniorText.setText("0.65");
		addKeyListener(juniorText);

		// Analyst
		gridData = new GridData();
		gridData.widthHint = 50;

		Label labelAnalyst = new Label(container, SWT.NULL);
		labelAnalyst.setText("Analyst:  ");
		analystText = new Text(container, SWT.BORDER | SWT.SINGLE);
		analystText.setLayoutData(gridData);
		analystText.setText("0.5");
		addKeyListener(analystText);

		// Senior
		gridData = new GridData();
		gridData.widthHint = 50;

		Label labelSenior = new Label(container, SWT.NULL);
		labelSenior.setText("Senior Analyst:  ");
		seniorText = new Text(container, SWT.BORDER | SWT.SINGLE);
		seniorText.setLayoutData(gridData);
		seniorText.setText("0.35");
		addKeyListener(seniorText);
		
		this.validateText();
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(true);

	}

	private void addKeyListener(Text text) {
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (validateText()) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}

		});
	}

	private boolean validateText() {
		boolean ret = false;
		if (!internText.getText().isEmpty() && !treineeText.getText().isEmpty()
				&& !juniorText.getText().isEmpty()
				&& !analystText.getText().isEmpty()
				&& !seniorText.getText().isEmpty()) {
			
			this.seniorities.add(internText.getText());
			this.seniorities.add(treineeText.getText());
			this.seniorities.add(juniorText.getText());
			this.seniorities.add(analystText.getText());
			this.seniorities.add(seniorText.getText());
			
			ret = true;
		}
		return ret;
	}

	public ArrayList<String> getSeniorities() {
		return seniorities;
	}

}
