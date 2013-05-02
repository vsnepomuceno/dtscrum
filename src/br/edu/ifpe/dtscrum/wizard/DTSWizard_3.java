package br.edu.ifpe.dtscrum.wizard;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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

import br.edu.dtscrum.DTAlgorithm;
import br.edu.dtscrum.entidades.Developer;
import br.edu.dtscrum.entidades.Seniority;
import br.edu.dtscrum.entidades.Task;

public class DTSWizard_3 extends WizardPage {

	private Composite container;
	private Table table;

	public DTSWizard_3() {
		super("DTSWizard");
		setTitle("Result of the IB´s assignment process.");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;

		Button runDT = new Button(container, SWT.PUSH);
		runDT.setText("Run Task Designation");
		runDT.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DTSWizard_0 dts0 = (DTSWizard_0) getWizard().getPages()[0];
				ArrayList<String> sen = dts0.getSeniorities();
				DTSWizard_1 dts1 = (DTSWizard_1) getWizard().getPages()[1];
				ArrayList<Task> tasks = dts1.getTasks();
				DTSWizard_2 dts2 = (DTSWizard_2) getWizard().getPages()[2];
				ArrayList<Developer> devs = dts2.getDevs();
				Seniority minor = Seniority.SENIOR_ANALYST;

				DTAlgorithm dtAlg = new DTAlgorithm(sen, devs, tasks);
				dtAlg.start();
				while (dtAlg.isAlive()) {
					setMessage("Running...");
				}
				
				for (int i = 0; i < devs.size(); i++) {
					Developer d = devs.get(i);
					if (d.getSenior().getSeniority() < minor.getSeniority()) {
						minor = d.getSenior();
					}
				}

				Map<Developer, ArrayList<Task>> result = dtAlg.getResult();
				Set<Developer> devsSet = result.keySet();
				for (Developer dev : devsSet) {
					TableItem item1 = new TableItem(table, SWT.NONE);
					ArrayList<Task> tks = result.get(dev);
					String ibs = "";
					double time = 0;
					for (Task t : tks) {
						ibs += (", " + t.getName());
						time += (t.getTimeTask() * getFactorBySeniority(sen,
								dev.getSenior(), minor));
					}
					if (ibs.length() > 2) {
						time = (time*10.0);
						time = ((int)time)/10.0;
						item1.setText(new String[] { dev.getName(),
								ibs.substring(2), "" + time });
					}
				}
				setMessage("Finish!");
				setPageComplete(true);
			}

			private double getFactorBySeniority(ArrayList<String> sen,
					Seniority senior, Seniority minor) {
				double v = 0.0;
				double vminor = Double.parseDouble(sen.get(minor.getSeniority()-1));
				double vf = 0.0;
				
				switch (senior) {

				case INTERN:
					v = Double.parseDouble(sen.get(0));
					vf = v/vminor;
					break;
				case TREINEE:
					v = Double.parseDouble(sen.get(1));
					vf = v/vminor;
					break;
				case JUNIOR_ANALYST:
					v = Double.parseDouble(sen.get(2));
					vf = v/vminor;
					break;
				case ANALYST:
					v = Double.parseDouble(sen.get(3));
					vf = v/vminor;
					break;
				case SENIOR_ANALYST:
					v = Double.parseDouble(sen.get(4));
					vf = v/vminor;
					break;

				}
				return vf;
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 20;
		this.table = new Table(container, SWT.MULTI | SWT.VIRTUAL | SWT.BORDER);
		String[] titles = { "Developer", "IB´s", "Total Time" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(this.table, SWT.CENTER);
			column.setText(titles[i]);
			column.setWidth(150);

		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(gridData);

		setControl(container);
		setPageComplete(false);
	}

}
