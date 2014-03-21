package edu.calpoly.razsoftware;

import edu.calpoly.razsoftware.renderer.CourseRenderer;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.TableColumn;

/**
 * The application's main frame.
 * @author jkwalsh
 */
public class FlowcharterView extends FrameView implements ActionListener, MouseListener
{
    /** max number of icons **/
    public static final int kMaxIcons = 15;
    /** default col width **/
    private final static int kColWidth = 100; // default col width (pixels)
    /** software version **/
    private final static String kVersion = "DegreeAdvisor v1.1";
    /** about message**/
    private final static String kAboutMessage = "You are using the Degree Advisor"
            + " program\nVersion: 1.1";
    /** variable for deleting columns **/
    private boolean isDeleting = false;  // Dirty variable for deleting columns
    /** variable for adding columns **/
    private boolean isAdding = false;  // Dirty variable for adding columns
    /** variable for renaming **/
    private boolean isRenaming;
    /** column names **/
    private String colName;

    /** Custom dialog class. */
    private static class FlowcharterPopupBox extends JDialog
    {
        public FlowcharterPopupBox(JFrame mainFrame, String title, String text)
        {
            super(mainFrame, title);
            JPanel myPanel = new JPanel();
            getContentPane().add(myPanel);
            JTextArea area = new JTextArea(text);
            area.setEditable(false);
            area.setOpaque(false);
            myPanel.add(area);
            mainFrame.setDefaultCloseOperation(0);
            //setLocationRelativeTo(mainFrame);

            setVisible(true);
        }
    }
    private Controller control;

    /** Constructs a FlowcharterView object that is registered to a
     * SingeFrameApplication and a Controller object.
     * @param app The application base class.  Handles program resources,
     * component display, and program exit.
     * @param control A Controller object that handles events in the GUI.
     */
    public FlowcharterView(SingleFrameApplication app, Controller control)
    {
        super(app);

        this.control = control;
        this.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initComponents();

        upDlg = new UpdateReqDialog(this.getFrame(), control, true);

        setupTable(control);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        // FOR the number of icons
        for (int index = 0; index < busyIcons.length; index++)
        {
            busyIcons[index] = resourceMap.getIcon("StatusBar.busyIcons[" + index + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;

            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        setupTaskMonitor();
    }

    private void setupTaskMonitor()
    {
        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                String propertyName = evt.getPropertyName();
                // Is this event for the start property?
                if ("started".equals(propertyName))
                {
                    // Is the application busy?
                    if (!busyIconTimer.isRunning())
                    {
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                }
                // Is this event for the done property?
                else if ("done".equals(propertyName))
                {
                    busyIconTimer.stop();
                }
                // Is this event for the message property?
                else if ("message".equals(propertyName))
                {
                    String text = (String) (evt.getNewValue());
                    messageTimer.restart();
                }
                // Is this event for the progress property?
                else if ("progress".equals(propertyName))
                {
                    int value = (Integer) (evt.getNewValue());
                }
            }
        });
    }

    private void setupTable(Controller ctrl)
    {
        // Set the dimensions for each column in the table
        for (int index = 0; index < ctrl.getChartSize(); index++)
        {
            TableColumn column = tblChart.getColumnModel().getColumn(index);
            column.setPreferredWidth(kColWidth);
            column.setMaxWidth(kColWidth);
            column.setMinWidth(kColWidth);
        }
        // Required: NetBeans default won't allow dynamically added labels to appear
        courseDetails.setLayout(new BorderLayout());
        // Provide a custom cell renderer
        tblChart.setDefaultRenderer(Requirement.class, new CourseRenderer());
        tblChart.scrollRectToVisible(new Rectangle(1, 1));
        // Disable tooltips (to improve performance)
        //http://www.chka.de/swing/table/faq.html
        ToolTipManager.sharedInstance().unregisterComponent(tblChart);
        ToolTipManager.sharedInstance().unregisterComponent(tblChart.getTableHeader());
    }

    /** Set the initial "current" cell */
    public void init()
    {
        int qtr = control.findCurrentQuarter();
        tblChart.changeSelection(0, qtr, false, false);
        control.mouseClicked(1);
    }

    /**
     * Displays a popup box with information about the program.
     */
    @Action
    public void showAboutBox()
    {
        // INIT a string with about information
        String text = kAboutMessage;
        // SHOW box with information about program
        JOptionPane.showMessageDialog(this.getFrame(), text, kVersion,
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    /** Accessor to the currently selected row in the displayed table.
     * @return The currently selected row.
     */
    public int currentRow()
    {
        return tblChart.getSelectedRow();
    }

    /** Accessor to the currently selected column in the displayed table.
     * @return The currently selected column.
     */
    public int currentCol()
    {
        return tblChart.getSelectedColumn();
    }

    /** Displays a popup box with options for changing the parameters of a
     * flowchart requirement.
     * @param req The requirement to be modified by the poopup.
     */
    public void showUpdateDialog(I_Requirement req)
    {
        upDlg.setRequirement(req);
        upDlg.setVisible(true);
    }

    /** Update the Course Details display panel.
     * @param req The requirement whose details will be displayed.
     */
    public void updateDetails(I_Requirement req)
    {
        courseDetails.removeAll();
        courseDetails.add(CourseDetailsView.getPanel(req), BorderLayout.CENTER);

        this.getFrame().validate();
        this.getFrame().repaint();
    }

    /**
     * Changes the selected cell in the flowchart table to the specified cell.
     * @param targetRow The row of the cell to be selected, 0 inclusive.
     * @param targetCol The column of the cell to be selected, 0 inclusive.
     */
    public void moveCursor(int targetRow, int targetCol)
    {
        tblChart.changeSelection(targetRow, targetCol, false, false);
    }

    private void sorry()
    {
        JOptionPane.showMessageDialog(this.getFrame(), "Not Implemented Yet",
                                      "Sorry!", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Shows a summary box. */
    private void showSummary()
    {
        // get the catalog version
        String version = control.getCatalogVersion();
        // CALL Controller.getChartName RETURNING nameString
        String name = control.getSavefileName();
        // CALL Controller.getCompletedUnits RETURNING compUnits
        int units = control.getCompletedUnits();
        // Format the info
        String text = "Catalog Version: " + version + "\n"
                + "Current Savefile: " + name + "\n"
                + "Units Completed: " + units;
        // show Popup dialog with nameString and compUnits.
        JOptionPane.showMessageDialog(this.getFrame(), text, "Summary",
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    /** Changes a dirty state variable to indicate that the program is in
     * "deleting" mode.  Right clicks cancel deleting mode.
     */
    private void deleteQuarter()
    {
        // Give a warning about the gravity of the operation being performed
        noticeField.setText("Select a quarter to delete using the cursor.\n"
                + "Right-click to cancel the delete.\n"
                + "This operation cannot be undone.");
        // Set the state to "deleting" so the click handler will know that the
        // click is for a delete operation
        isDeleting = true;
        // Change the cursor
        chartPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    /** Changes a dirty state variable to indicate that program is in "adding"
     * mode.  Right clicks will add a quarter to the right of the click
     * location.  Left clicks will add a quarter to the left of the click
     * location.
     */
    private void addQuarter()
    {
        // Give instructions on operation being performed
        noticeField.setText("Left-click anywhere in a column to insert an empty"
                + " column to the left of the clicked column.  Right-click "
                + "anywhere in a column to insert an empty column to the right"
                + "of the clicked column.  Middle-click cancels");
        // Set the state to "adding"
        isAdding = true;
        // Change the cursor
        chartPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        lblLegend = new javax.swing.JLabel();
        chartPane = new javax.swing.JScrollPane();
        tblChart = new javax.swing.JTable();
        courseDetails = new javax.swing.JPanel();
        noticeField = new javax.swing.JTextField("");
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        qtrNewMenuItem = new javax.swing.JMenuItem();
        qtrDeleteMenuItem = new javax.swing.JMenuItem();
        qtrRenameMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        summaryMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        lblLegend.setText("<html> <span style=\"background-color:yellow;\">Make selection</span>&nbsp;\n<span style=\"background-color:gray;\">&nbsp;Ineligible</span> \n <span style=\"background-color:rgb(255, 102, 102);\">Out of sequence</span> \n<span style=\"background-color:green;\">Complete</span>&nbsp;\n<span style=\"text-decoration: underline;\">alternate</span>\n<span style=\"font-style: italic;\">prereq</span>\n</html>"); // NOI18N
        lblLegend.setName("lblLegend"); // NOI18N

        chartPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        chartPane.setMinimumSize(new java.awt.Dimension(452, 419));
        chartPane.setName("chartPane"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FlowcharterView.class);
        tblChart.setFont(resourceMap.getFont("tblChart.font")); // NOI18N
        tblChart.setModel(control.getModel());
        tblChart.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblChart.setCellSelectionEnabled(true);
        tblChart.setName("tblChart"); // NOI18N
        tblChart.setRowHeight(40);
        tblChart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblChart.getTableHeader().setResizingAllowed(false);
        tblChart.getTableHeader().setReorderingAllowed(false);
        tblChart.addMouseListener(this);
        chartPane.setViewportView(tblChart);

        courseDetails.setMinimumSize(new java.awt.Dimension(737, 399));
        courseDetails.setName("courseDetails"); // NOI18N

        javax.swing.GroupLayout courseDetailsLayout = new javax.swing.GroupLayout(courseDetails);
        courseDetails.setLayout(courseDetailsLayout);
        courseDetailsLayout.setHorizontalGroup(
            courseDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );
        courseDetailsLayout.setVerticalGroup(
            courseDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        noticeField.setEditable(false);
        noticeField.setText(resourceMap.getString("noticeField.text")); // NOI18N
        noticeField.setToolTipText(resourceMap.getString("noticeField.toolTipText")); // NOI18N
        noticeField.setAutoscrolls(false);
        noticeField.setName("noticeField"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(chartPane, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(courseDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noticeField, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(lblLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartPane, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(courseDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(noticeField)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        mnuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuOpen.setText(resourceMap.getString("mnuOpen.text")); // NOI18N
        mnuOpen.setName("mnuOpen"); // NOI18N
        mnuOpen.addActionListener(this);
        fileMenu.add(mnuOpen);

        mnuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuSave.setText(resourceMap.getString("mnuSave.text")); // NOI18N
        mnuSave.setName("mnuSave"); // NOI18N
        mnuSave.addActionListener(this);
        fileMenu.add(mnuSave);

        mnuSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnuSaveAs.setText(resourceMap.getString("mnuSaveAs.text")); // NOI18N
        mnuSaveAs.setName("mnuSaveAs"); // NOI18N
        mnuSaveAs.addActionListener(this);
        fileMenu.add(mnuSaveAs);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText(resourceMap.getString("editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        qtrNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        qtrNewMenuItem.setText(resourceMap.getString("qtrNewMenuItem.text")); // NOI18N
        qtrNewMenuItem.setName("qtrNewMenuItem"); // NOI18N
        qtrNewMenuItem.addActionListener(this);
        editMenu.add(qtrNewMenuItem);

        qtrDeleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        qtrDeleteMenuItem.setText(resourceMap.getString("qtrDeleteMenuItem.text")); // NOI18N
        qtrDeleteMenuItem.setName("qtrDeleteMenuItem"); // NOI18N
        qtrDeleteMenuItem.addActionListener(this);
        editMenu.add(qtrDeleteMenuItem);

        qtrRenameMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        qtrRenameMenuItem.setText(resourceMap.getString("qtrRenameMenuItem.text")); // NOI18N
        qtrRenameMenuItem.setName("qtrRenameMenuItem"); // NOI18N
        qtrRenameMenuItem.addActionListener(this);
        editMenu.add(qtrRenameMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        summaryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        summaryMenuItem.setText(resourceMap.getString("summaryMenuItem.text")); // NOI18N
        summaryMenuItem.setName("summaryMenuItem"); // NOI18N
        summaryMenuItem.addActionListener(this);
        helpMenu.add(summaryMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FlowcharterView.class, this);
        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == mnuOpen) {
            FlowcharterView.this.mnuOpenActionPerformed(evt);
        }
        else if (evt.getSource() == mnuSave) {
            FlowcharterView.this.mnuSaveActionPerformed(evt);
        }
        else if (evt.getSource() == mnuSaveAs) {
            FlowcharterView.this.mnuSaveAsActionPerformed(evt);
        }
        else if (evt.getSource() == exitMenuItem) {
            FlowcharterView.this.exitMenuItemActionPerformed(evt);
        }
        else if (evt.getSource() == qtrNewMenuItem) {
            FlowcharterView.this.qtrNewMenuItemActionPerformed(evt);
        }
        else if (evt.getSource() == qtrDeleteMenuItem) {
            FlowcharterView.this.qtrDeleteMenuItemActionPerformed(evt);
        }
        else if (evt.getSource() == qtrRenameMenuItem) {
            FlowcharterView.this.qtrRenameMenuItemActionPerformed(evt);
        }
        else if (evt.getSource() == summaryMenuItem) {
            FlowcharterView.this.summaryMenuItemActionPerformed(evt);
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == tblChart) {
            FlowcharterView.this.tblChartMouseClicked(evt);
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == tblChart) {
            FlowcharterView.this.tblChartMousePressed(evt);
        }
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == tblChart) {
            FlowcharterView.this.tblChartMouseReleased(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void tblChartMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblChartMouseClicked
    {//GEN-HEADEREND:event_tblChartMouseClicked
        int clicks = evt.getClickCount();
        // IF current state is "deleting" THEN
        if (isDeleting)
        {
            // IF mouse click was not a right-click THEN
            if (!SwingUtilities.isRightMouseButton(evt))
            {
                // DETERMINE the column that should be removed
                int colNumber = tblChart.columnAtPoint(evt.getPoint());
                // CALL Controller.remove() to remove the column
                control.remove(colNumber);
            }
        }
        //ESLE IF the user is adding a column and user didn't hit middle click
        //THEN
        else if (isAdding && !SwingUtilities.isMiddleMouseButton(evt))
        {
            // Add new empty column at far-right
            tblChart.addColumn(new TableColumn(tblChart.getColumnCount(), kColWidth));
            // IF mouse click was a right-click THEN
            if (SwingUtilities.isRightMouseButton(evt))
            {
                // CALL Controller.addQuarter() with column to right of the click
                control.addQuarter(tblChart.columnAtPoint(evt.getPoint()) + 1);
            }
            // ELSE mouse click was a left-click
            else
            {
                // CALL Controller.addQuarter() with column to left of the click
                control.addQuarter(tblChart.columnAtPoint(evt.getPoint()));
            }
        }
        //ELSE IF the user is renaming
        else if (isRenaming)
        {
            TableColumn col = tblChart.getColumnModel().getColumn(
                    tblChart.columnAtPoint(evt.getPoint()));
            col.setHeaderValue(colName);
            control.getModel().renameQuarter(tblChart.columnAtPoint(
                    evt.getPoint()), colName);
        }
        //ELSE IF the user right clicks
        else if (SwingUtilities.isRightMouseButton(evt))
        {
            // get the coordinates of the mouse click
            // and convert to table position
            int rowNumber = tblChart.rowAtPoint(evt.getPoint());
            int colNumber = tblChart.columnAtPoint(evt.getPoint());
            // Uncomment these two lines if you want a right-clicked cell to be
            //selected
            // Right click doesn't select a cell automatically; we have to do
            //it manually
            //tblChart.setColumnSelectionInterval(colNumber,colNumber);
            //tblChart.setRowSelectionInterval(rowNumber,rowNumber);

            // go handle the click
            control.mouseRightClicked(rowNumber, colNumber);
            clicks = 1;  // continue with regular mouse click processing
        }
        control.mouseClicked(clicks);
        // SET cursor to default cursor
        chartPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        // SET "adding" and "deleting" flags to false
        isAdding = false;
        isDeleting = false;
        isRenaming = false;
        // SET the notice field to empty
        noticeField.setText(" ");
    }//GEN-LAST:event_tblChartMouseClicked
    private void mnuSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuSaveActionPerformed
    {//GEN-HEADEREND:event_mnuSaveActionPerformed
        control.save();
    }//GEN-LAST:event_mnuSaveActionPerformed

    private void tblChartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChartMousePressed
        mousedownRow = tblChart.getSelectedRow();
        mousedownCol = tblChart.getSelectedColumn();
    }//GEN-LAST:event_tblChartMousePressed

    private void tblChartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChartMouseReleased
        int row = tblChart.getSelectedRow();
        int col = tblChart.getSelectedColumn();
        //IF mouse was not clicked on the current row or column THEN
        if (row != mousedownRow || col != mousedownCol)
        {
            //IF col is less than chartSize THEN
            if (col < control.getChartSize())
            {
                control.moveCourse(mousedownRow, mousedownCol, row, col);
            }//ENDIF
        }//ENDIF

    }//GEN-LAST:event_tblChartMouseReleased

    private void qtrNewMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_qtrNewMenuItemActionPerformed
    {//GEN-HEADEREND:event_qtrNewMenuItemActionPerformed
        // CALL Controller.addQuarter()
        addQuarter();
    }//GEN-LAST:event_qtrNewMenuItemActionPerformed

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuOpenActionPerformed
    {//GEN-HEADEREND:event_mnuOpenActionPerformed
        control.open();
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuSaveAsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuSaveAsActionPerformed
    {//GEN-HEADEREND:event_mnuSaveAsActionPerformed
        control.saveAs();
    }//GEN-LAST:event_mnuSaveAsActionPerformed

    private void qtrDeleteMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_qtrDeleteMenuItemActionPerformed
    {//GEN-HEADEREND:event_qtrDeleteMenuItemActionPerformed
        // CALL deleteQuarter()
        deleteQuarter();
    }//GEN-LAST:event_qtrDeleteMenuItemActionPerformed

    private void summaryMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_summaryMenuItemActionPerformed
    {//GEN-HEADEREND:event_summaryMenuItemActionPerformed
        // CALL showSummary
        showSummary();
    }//GEN-LAST:event_summaryMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitMenuItemActionPerformed
        // CALL Controller.quit()
        control.quit();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void qtrRenameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtrRenameMenuItemActionPerformed
        isRenaming = true;
        colName = JOptionPane.showInputDialog("Enter name for new quarter,"
                + "then click in column to rename");

    }//GEN-LAST:event_qtrRenameMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JScrollPane chartPane;
    private javax.swing.JPanel courseDetails;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel lblLegend;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JTextField noticeField;
    private javax.swing.JMenuItem qtrDeleteMenuItem;
    private javax.swing.JMenuItem qtrNewMenuItem;
    private javax.swing.JMenuItem qtrRenameMenuItem;
    private javax.swing.JMenuItem summaryMenuItem;
    private javax.swing.JTable tblChart;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[kMaxIcons];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JDialog summaryBox;
    // Save start cell of a drag operation
    private int mousedownRow;
    private int mousedownCol;
    private UpdateReqDialog upDlg;
}
