package br.edu.dtscrum.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DTTaskDialog extends TitleAreaDialog {

  private Text nameText;
  private Text timeTaskText;
  private String name;
  private String timeTask;

  public DTTaskDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  public void create() {
    super.create();
    // Set the title
    setTitle("Add IB Dialog");
    // Set the message
    setMessage("Add IB Dialog", 
        IMessageProvider.INFORMATION);

  }

  @Override
  protected Control createDialogArea(Composite parent) {
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    // layout.horizontalAlignment = GridData.FILL;
    parent.setLayout(layout);

    // The text fields will grow with the size of the dialog
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;

    Label label1 = new Label(parent, SWT.NONE);
    label1.setText("Name: ");

    nameText = new Text(parent, SWT.BORDER);
    nameText.setLayoutData(gridData);
    
    Label label2 = new Label(parent, SWT.NONE);
    label2.setText("Intern IB Time: ");
    // You should not re-use GridData
    gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    timeTaskText = new Text(parent, SWT.BORDER);
    timeTaskText.setLayoutData(gridData);
    return parent;
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    GridData gridData = new GridData();
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalSpan = 3;
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = true;
    gridData.horizontalAlignment = SWT.CENTER;

    parent.setLayoutData(gridData);
    // Create Add button
    // Own method as we need to overview the SelectionAdapter
    createOkButton(parent, OK, "Add", true);
    // Add a SelectionListener

    // Create Cancel button
    Button cancelButton = 
        createButton(parent, CANCEL, "Cancel", false);
    // Add a SelectionListener
    cancelButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        setReturnCode(CANCEL);
        close();
      }
    });
  }

  protected Button createOkButton(Composite parent, int id, 
      String label,
      boolean defaultButton) {
    // increment the number of columns in the button bar
    ((GridLayout) parent.getLayout()).numColumns++;
    Button button = new Button(parent, SWT.PUSH);
    button.setText(label);
    button.setFont(JFaceResources.getDialogFont());
    button.setData(new Integer(id));
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        if (isValidInput()) {
          okPressed();
        }
      }
    });
    if (defaultButton) {
      Shell shell = parent.getShell();
      if (shell != null) {
        shell.setDefaultButton(button);
      }
    }
    setButtonLayoutData(button);
    return button;
  }

  private boolean isValidInput() {
    boolean valid = true;
    if (nameText.getText().length() == 0) {
      setErrorMessage("Name is Empty!");
      valid = false;
    }
    if (timeTaskText.getText().length() == 0) {
      setErrorMessage("IB Time is Empty!");
      valid = false;
    }
    if (!timeTaskText.getText().matches("^[0-9]+$")) {
    	setErrorMessage("IB Time must have only digits!");
    	valid = false;
    }
    return valid;
  }
  
  @Override
  protected boolean isResizable() {
    return true;
  }

  // Coyy textFields because the UI gets disposed
  // and the Text Fields are not accessible any more.
  private void saveInput() {
    name = nameText.getText();
    timeTask = timeTaskText.getText();
  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getName() {
    return name;
  }

  public int getTimeTask() {
    return Integer.parseInt(timeTask);
  }
} 
