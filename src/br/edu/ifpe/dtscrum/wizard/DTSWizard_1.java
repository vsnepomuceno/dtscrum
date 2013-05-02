package br.edu.ifpe.dtscrum.wizard;

import java.util.ArrayList;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import br.edu.dtscrum.dialog.DTTaskDialog;
import br.edu.dtscrum.entidades.Task;

public class DTSWizard_1 extends WizardPage {

	private Composite container;
	private Table table;
	private ArrayList<Task> tasks;

	public DTSWizard_1() {
		super("DTSWizard");
		setTitle("IBs Input");
		setDescription("Add all IBs in this Sprint. " +
				"\nThe IB's time must be considered " +
				"from the developer with minor seniority " +
				"among team members.");
		this.tasks = new ArrayList<Task>();
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;
		Button addTask = new Button(container, SWT.PUSH);
		addTask.setText("Add IB");
		addTask.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DTTaskDialog dialog = new DTTaskDialog(container.getShell());
				dialog.create();
				if (dialog.open() == Window.OK) {
				  tasks.add(new Task(dialog.getName(), dialog.getTimeTask()));
				  TableItem item1 = new TableItem(table, SWT.NONE);
				  item1.setText(new String[] { ""+tasks.size(), dialog.getName(), ""+dialog.getTimeTask() });
				  setPageComplete(true);
				} 
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 20;
		this.table = new Table(container, SWT.MULTI | SWT.VIRTUAL | SWT.BORDER);
		String[] titles = {"Nº", "Name", "Minor Seniority IB Time"};
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (this.table, SWT.CENTER);
			column.setText (titles [i]);
			column.setWidth(150);
			
		}
		table.setHeaderVisible (true);
		table.setLinesVisible (true);
		table.setLayoutData(gridData);
		
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
}
