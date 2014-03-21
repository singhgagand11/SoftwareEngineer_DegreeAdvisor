package edu.calpoly.razsoftware;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Requirement information dialog box, used to override, complete, and select
 * from list of classes for GE and courses.
 * @author jdalbey, gdonegan
 */
public class UpdateReqDialog extends javax.swing.JDialog implements
        ActionListener, WindowListener, ItemListener
{
    /** A return status code - returned if Cancel button has been pressed */
    public static final int kReturnCancel = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int kReturnOk = 1;
    /** requirement to display information of**/
    private I_Requirement req;
    /** control obtain information of **/
    private Controller control;
    /** preRequisities of the current course **/
    private String preRequisites;
    /** dept direction **/
    private String deptDirections;
    /** change **/
    public String change = "change";
    // Declare value for which card you are in
    public int cardVal = 0;

    /** Creates new form UpdateReqDialog
     * @param parent parent frame
     * @param control controller for accessor to course info
     * @param modal check modal
     */
    public UpdateReqDialog(java.awt.Frame parent, Controller control,
                           boolean modal)
    {
        super(parent, modal);
        this.control = control;
        initComponents();
    }

    /**
     * sets the requirement for this dialog
     * @param req requirement to display infor of
     */
    public void setRequirement(I_Requirement req)
    {
        this.req = req;
        this.jLabel3.setVisible(false);
        this.lblCourse.setText("");


        comboBox.removeAllItems();
        // DEFECT Shouldn't display prereq box when none are present
        preReqSatisfiedBox.setVisible(false);

        // if there are multiple courses that can fulfill this requirement
        // show a comboBox with the available options
        // DEFECT #344 - UpdateReqDialog needs to check if blank cell
        // is selected
        if (req != null)
        {
            if(req.getCourse() != null)
            {
                this.lblReqTitle.setText("<html><p>"+req.getCourse().getTitle()+"</p></html>");
            }
            else
            {
                this.lblReqTitle.setText("<html><p>"+req.getTitle()+"</p></html>");
            }
            //IF there are fullfillment options for the requirements
            if (!req.getFulfillmentOptions().isEmpty())
            {
                
                String fulfill = req.getFulfillmentOptions().toString();

                //get deptDirections from requirement
                deptDirections = req.getDeptDirections();


                // DEFECT #295 - Shouldn't display combobox for only one option
                this.jLabel3.setVisible(false);
                this.lblCourse.setText("");
                comboBox.setVisible(false);

                //IF requirement has more than one fulfillment option THEN
                if (req.getFulfillmentOptions().size() > 1)
                {
                    this.jLabel3.setVisible(true);
                    comboBox.setVisible(true);
                }//ENDIF

                //FOR each courses in the fulfillment options THEN
                for (I_Course item : req.getFulfillmentOptions())
                {
                    comboBox.addItem(item);
                    //IF course is the same course as the requiremnt
                    if (item.equals(req.getCourse()))
                    {
                        comboBox.setSelectedItem(item);
                    }//ENDIF
                }//ENDFOR
                // Add a selection to let user choose none of the above

                //IF requirement has a course THEN
                if (req.getCourse() != null)
                {
                    preRequisites = req.getCourse().getPreRequisitesString();
                    comboBox.addItem("none");
                }
                //ELSE Add a selection reminding user to choose one
                else
                {
                    // check for requirement prereqs
                    preRequisites = req.getPreRequisites().toString();
                    //Cut off brackets in string
                    preRequisites = preRequisites.substring(1, preRequisites.length() - 1);
                    comboBox.addItem("choose one");
                    comboBox.setSelectedItem("choose one");
                }//ENDELSEIF
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "card2");
                cardVal = 2;
                //IF there are deptDirections for this requirement
                if (deptDirections != null && deptDirections.length() > 1)
                {
                    // Display a text box with the special directions
                    this.jLabel5.setVisible(true);
                    this.jLabel5.setText("<html><p>Department Directions: " + deptDirections);
                }
                //END IF
                //ELSE THERE ARENT
                else
                {
                    this.jLabel5.setVisible(false);
                }//END ELSE

                //IF course has prerequisites THEN
                //DEFECT #301, made it so prerequisites only display when they exist
                if (preRequisites != null && preRequisites.length() > 2)
                {
                    //IF requirement has a course and requirement is eligible
                    if (req.getCourse() != null
                            && req.getCourse().isEligible(
                            (HashSet<Contingent>) req.getPreRequisites()))
                    {
                        preReqSatisfiedBox.setSelected(true);
                    }//ELSE
                    else
                    {

                        preReqSatisfiedBox.setSelected(false);
                    }
                    //display preRequisites
                    this.jLabel4.setVisible(true);
                    this.jLabel4.setText("Prerequisites: " + preRequisites);
                    // display an option for overriding the preRequisites
                    preReqSatisfiedBox.setVisible(true);
                }//ENDIF
                //DEFECT #324 made it so prerequisites dont display old versions if 
                // they dont exist
                else
                {
                    this.jLabel4.setVisible(false);
                }//END

                //END IF
            }//ELSE
            else
            {
                // HAD TO ADD BACK IN FIELD FOR CHECKING FOR CARD 1 BECAUSE
                // OF SPECIAL CASE BROUGHT ABOUT BY THE CUSTOMER
                // RELEASE MEETING JSON FILE
                this.txtComments.setText(req.getDeptDirections()+" Be sure"
                        +"   that the requirement is not already on your flowchart.");
                deptNames.addItem("none");
                for(String dept : control.getDeptsFromCatalog())
                {
                    deptNames.addItem(dept);
                }
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "card3");
                cardVal = 3;
                comboBox.setVisible(false);
                this.jLabel3.setVisible(false);
                
            }
            //IF requirement has a course and it is completed
            if (req.getCourse() != null && req.getCourse().isComplete())
            {
                ckCompleted.setSelected(true);
            }//ELSE
            else
            {
                ckCompleted.setSelected(false);
            }//END ELSEIF
        }
    }

    /**
     * getting to requirement status
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus()
    {
        //RETURN requirement status
        return returnStatus;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblReqTitle = new javax.swing.JLabel();
        ckCompleted = new javax.swing.JCheckBox();
        cardPanel = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCourse = new javax.swing.JLabel();
        card2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        preReqSatisfiedBox = new javax.swing.JCheckBox();
        card3 = new javax.swing.JPanel();
        lblForCourse = new javax.swing.JLabel();
        catCourseList = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        deptNames = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComments = new javax.swing.JTextArea();

        setName("Form"); // NOI18N
        addWindowListener(this);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(UpdateReqDialog.class);
        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        okButton.addActionListener(this);

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(this);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblReqTitle.setText(resourceMap.getString("lblReqTitle.text")); // NOI18N
        lblReqTitle.setName("lblReqTitle"); // NOI18N

        ckCompleted.setText(resourceMap.getString("ckCompleted.text")); // NOI18N
        ckCompleted.setName("ckCompleted"); // NOI18N

        cardPanel.setName("cardPanel"); // NOI18N
        cardPanel.setLayout(new java.awt.CardLayout());

        card1.setName("card1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lblCourse.setText(resourceMap.getString("lblCourse.text")); // NOI18N
        lblCourse.setName("lblCourse"); // NOI18N

        javax.swing.GroupLayout card1Layout = new javax.swing.GroupLayout(card1);
        card1.setLayout(card1Layout);
        card1Layout.setHorizontalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        card1Layout.setVerticalGroup(
            card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblCourse))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        cardPanel.add(card1, "card1");

        card2.setName("card2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        comboBox.setName("comboBox"); // NOI18N

        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setName("jLabel5"); // NOI18N

        preReqSatisfiedBox.setText(resourceMap.getString("preReqSatisfiedBox.text")); // NOI18N
        preReqSatisfiedBox.setName("preReqSatisfiedBox"); // NOI18N

        javax.swing.GroupLayout card2Layout = new javax.swing.GroupLayout(card2);
        card2.setLayout(card2Layout);
        card2Layout.setHorizontalGroup(
            card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card2Layout.createSequentialGroup()
                .addGroup(card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(card2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(preReqSatisfiedBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(card2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel5))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        card2Layout.setVerticalGroup(
            card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addComponent(preReqSatisfiedBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        cardPanel.add(card2, "card2");

        card3.setName("card3"); // NOI18N

        lblForCourse.setText(resourceMap.getString("lblForCourse.text")); // NOI18N
        lblForCourse.setName("lblForCourse"); // NOI18N

        catCourseList.setName("catCourseList"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        deptNames.setName("deptNames"); // NOI18N
        deptNames.addItemListener(this);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtComments.setColumns(20);
        txtComments.setEditable(false);
        txtComments.setLineWrap(true);
        txtComments.setRows(5);
        txtComments.setName("txtComments"); // NOI18N
        jScrollPane1.setViewportView(txtComments);

        javax.swing.GroupLayout card3Layout = new javax.swing.GroupLayout(card3);
        card3.setLayout(card3Layout);
        card3Layout.setHorizontalGroup(
            card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card3Layout.createSequentialGroup()
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deptNames, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblForCourse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(catCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(card3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
                .addContainerGap())
        );
        card3Layout.setVerticalGroup(
            card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, card3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(card3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForCourse)
                    .addComponent(catCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(deptNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        cardPanel.add(card3, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblReqTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
                    .addComponent(ckCompleted, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblReqTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckCompleted)
                .addGap(18, 18, 18)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == okButton) {
            UpdateReqDialog.this.okButtonActionPerformed(evt);
        }
        else if (evt.getSource() == cancelButton) {
            UpdateReqDialog.this.cancelButtonActionPerformed(evt);
        }
    }

    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getSource() == deptNames) {
            UpdateReqDialog.this.deptNamesItemStateChanged(evt);
        }
    }

    public void windowActivated(java.awt.event.WindowEvent evt) {
    }

    public void windowClosed(java.awt.event.WindowEvent evt) {
    }

    public void windowClosing(java.awt.event.WindowEvent evt) {
        if (evt.getSource() == UpdateReqDialog.this) {
            UpdateReqDialog.this.closeDialog(evt);
        }
    }

    public void windowDeactivated(java.awt.event.WindowEvent evt) {
    }

    public void windowDeiconified(java.awt.event.WindowEvent evt) {
    }

    public void windowIconified(java.awt.event.WindowEvent evt) {
    }

    public void windowOpened(java.awt.event.WindowEvent evt) {
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        okButtonTestable(evt);
    }

    /**
     * Tester for ok button
     * @param evt action event on the button
     */
    void okButtonTestable(java.awt.event.ActionEvent evt)
    {
        // Since the last item in the combobox is either"none" or "choose one"
        // if the selected index is prior to that we know the user
        // selected a course
        int cbIndex = comboBox.getSelectedIndex();
        
        
        //IF the selected course if the last one in the list THEN
        if (cbIndex < comboBox.getItemCount() - 1 && cardVal == 2)
        {
            if(!control.flowChartHas((Course) comboBox.getSelectedItem()))
            {
                req.setCourse((Course) comboBox.getSelectedItem());
                preRequisites = req.getCourse().getPreRequisitesString();
            }
            // DEFECT 375
            else if(comboBox.getItemCount() > 2)
            {
                control.classExists();
            }      
        }//ELSE
        else
        {
            req.setCourse(null);
        }//END ELSEIF

        // IF this is the "all courses" selection
        if (catCourseList.getItemCount() > 0 && cardVal == 3)
        {
            String desired = (String) catCourseList.getSelectedItem();
            //catCourseList.removeAllItems();
            //IF user didn't select any courses
            if (desired.equals("none"))
            {
                req.setCourse(null);
                // req.setFulfillment("#");
            }//ELSE
            // DEFECT 375 - check to see if already in flowchart
            else if(!control.flowChartHas(control.getCourseFromCatalog(desired)))
            {
                // the combo box should contain Courses, not strings.
                req.setCourse(control.getCourseFromCatalog(desired));
            }//ENDIF
            else
            {
                control.classExists();
            }
        }//ENDIF

        //deptNames.removeAllItems();
        jLabel4.removeAll();
        jLabel5.removeAll();
        deptDirections = null;
        preRequisites = null;

        //IF requirement has a course
        if (req.getCourse() != null)
        {

            // DEFECT #305 PREREQSATISFIED BOX DID NOT PROPERLY SET COURSE 
            //AS AVAIL.
            //IF user has choosen to satisfies the requirement THEN
            if (preReqSatisfiedBox.isSelected())
            {
                req.getCourse().setEligible(true);
            }//ELSE
            else
            {
                req.getCourse().setEligible(false);
            }
            //ENE IFELSE
            req.getCourse().setCompleted(ckCompleted.isSelected());
        }//END IF
        doClose(kReturnOk);
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * cancel button action handler
     * @param evt action events for the cancel button
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // DEFECT 340 make sure cancel button resets display too
        //deptNames.removeAllItems();
        jLabel4.removeAll();
        jLabel5.removeAll();
        deptDirections = null;
        preRequisites = null;
        doClose(kReturnCancel);
    }//GEN-LAST:event_cancelButtonActionPerformed

 /**
     * close button action handler
     * @param evt action events for the close button
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        // DEFECT 340 make sure cancel/x button resets display too
        //deptNames.removeAllItems();
        jLabel4.removeAll();
        jLabel5.removeAll();
        deptDirections = null;
        preRequisites = null;
        doClose(kReturnCancel);
    }//GEN-LAST:event_closeDialog

    private void deptNamesItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_deptNamesItemStateChanged
    {//GEN-HEADEREND:event_deptNamesItemStateChanged
        JComboBox cb = (JComboBox) evt.getSource();
        String deptName = (String) cb.getSelectedItem();
        // Sometimes this event handler gets called when a programmatic change
        // happens to the list.  I only want to add course names if there
        // really was a dept name selected by the user.
        if (deptName != null)
        {
            catCourseList.removeAllItems();
            ArrayList<String> coursenames = (ArrayList) control.getNamesFromCatalog(deptName);
            // FOR each course
            for (String course : coursenames)
            {
                catCourseList.addItem(course);
            }
            catCourseList.addItem("none");
            catCourseList.setEnabled(true);
        }
    }//GEN-LAST:event_deptNamesItemStateChanged

    /**
     * closes the requirement dialog window
     * @param retStatus return status
     */
    void doClose(int retStatus)
    {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /** WOn't work without a catalog
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                UpdateReqDialog dialog = new UpdateReqDialog(
                        new javax.swing.JFrame(), null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel card1;
    private javax.swing.JPanel card2;
    private javax.swing.JPanel card3;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JComboBox catCourseList;
    private javax.swing.JCheckBox ckCompleted;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JComboBox deptNames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblForCourse;
    private javax.swing.JLabel lblReqTitle;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox preReqSatisfiedBox;
    private javax.swing.JTextArea txtComments;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = kReturnCancel;

    /**
     * TEST METHOD - returns preRequisites to insure preReqs are being properly 
     * obtained
     * @return preRequisites
     */
    String getPreReqsTest()
    {
        return preRequisites;
    }

    /**
     * TEST METHOD - returns deptDirections string to insure deptDirections 
     * are being properly set
     * @return dept direction
     */
    String getDeptDirTest()
    {
        return deptDirections;
    }

    /**
     * Test METHOD - Sets the satisfied checkbox for testing purposes
     */
    void setSatisfied(boolean val)
    {
        preReqSatisfiedBox.setSelected(val);
    }
}
