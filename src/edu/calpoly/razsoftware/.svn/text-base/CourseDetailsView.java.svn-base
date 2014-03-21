package edu.calpoly.razsoftware;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 * A Panel for displaying requirement status such as if the requirement is
 * completed and set of prerequisites.
 *
 * Fixed Defect #335 line 110 - 115 added
 * author: gasingh
 * Created on Feb 27, 2012, 6:25:30 PM
 */
public final class CourseDetailsView extends javax.swing.JPanel
{
    //String length for fullfillment
    private static final int kFullLen = 60;

    /** Creates new form CourseDetailView1 */
    private CourseDetailsView()
    {
        initComponents();
    }

    /**
     * A Panel for displaying requirement status such as if the requirement is
     * completed and set of prerequisites.
     * @param req requirement to display information of
     * @return a panel containing req's information
     */
    public static JPanel getPanel(I_Requirement req)
    {

        //SET panel to new CouseDetailsView2 object
        CourseDetailsView panel = new CourseDetailsView();

        //IF req is not null
        if (req != null)
        {

            //SET crs to req's course
            I_Course crs = req.getCourse();


            //IF crs is not null
            if (crs != null)
            {
                //SET course to "Course: " followed by crs
                String course = crs.getName();
                //IF crs is completed
                if (crs.isComplete())
                {
                    //append "COMPLETED" to course
                    course += "  COMPLETED";
                }
                //ENDIF

                //SET lblCourse to course string
                panel.courseTitle.setText(course);


                //SET reqs to course's prerequisites string
                String reqs = req.getCourse().getPreRequisitesString();

                //IF reqs is not null
                if (reqs != null)
                {
                    //SET prereqsText in panel to reqs string
                    panel.prereqs.setText(reqs);
                }
                //ENDIF

                //SET descrip to course description
                panel.descrip.setText(crs.getDescription());
            }
            //ENDIF
            else
            {
                // check for requirement prerequisites
                String reqs = req.getPreRequisites().toString();
                // cut off brackets for display purposes
                reqs = reqs.substring(1, reqs.length() - 1);
                //IF reqs is not null
                if (reqs != null)
                {
                    //SET prereqsText in panel to reqs string
                    panel.prereqs.setText(reqs);
                }
                //ENDIF
            }


            //SET lblTitle to new JLabel of "Requirement" followd by req
            panel.reqs.setText(req.toString());

            //SET fulfill to fulfillment options of req's
            StringBuilder fulfill = new StringBuilder();

            createFulFillmentStr(req, fulfill);

            //SET options's text to fulfill string
            panel.options.setText(fulfill.toString());

            //SET options's background to yellow
            panel.options.setBackground(Color.YELLOW);

            //IF req has a department direction
            if (req.getDeptDirections() != null)
            {
                //SET test in depDir label to req's department direction
                panel.deptDir.setText(req.getDeptDirections());
            }
            //ENDIF

        }
        //ENDIF
        //return the panel
        return panel;

    }

    /**
     * Creates a string for fulfillment for the requirement
     * Helper function used to remove method length
     * @param req requirement to obtain fulfillment options for
     * @param fulfill string buffer for creating the fulfillment options
     */
    private static void createFulFillmentStr(I_Requirement req,
                                             StringBuilder fulfill)
    {
        //SET courses to req's fulfillment options iterator
        Iterator<I_Course> courses = req.getFulfillmentOptions().iterator();

        //FOR each couse in courses
        while (courses.hasNext())
        {
            //APPEND the course name to fulfill
            fulfill.append(courses.next().getName());
            //IF there are more options
            if (courses.hasNext())
            {
                //APPEND a comma preceeded by a space
                fulfill.append(",  ");
            }
            //ENDIF
        }

        //IF the length of fulfill is greater than 60
        if (fulfill.length() > kFullLen)
        {
            //truncated to fulfill up to 60 character offset

            fulfill.setLength(kFullLen);
            //append ... to fulfill
            fulfill.append("...");
        }
        //ENDIF
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crcLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descrip = new javax.swing.JTextPane();
        courseTitle = new javax.swing.JLabel();
        descripLbl = new javax.swing.JLabel();
        prereqLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        prereqs = new javax.swing.JTextPane();
        reqLbl = new javax.swing.JLabel();
        options = new javax.swing.JLabel();
        optLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reqs = new javax.swing.JTextPane();
        deptDirLbl = new javax.swing.JLabel();
        deptDir = new javax.swing.JLabel();

        crcLbl.setText("Course:");
        crcLbl.setName("crcLbl"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        descrip.setEditable(false);
        descrip.setName("descrip"); // NOI18N
        jScrollPane1.setViewportView(descrip);

        courseTitle.setText("");
        courseTitle.setName("courseTitle"); // NOI18N

        descripLbl.setText("Description:");
        descripLbl.setName("descripLbl"); // NOI18N

        prereqLbl.setText("Prereqs:");
        prereqLbl.setName("prereqLbl"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        prereqs.setEditable(false);
        prereqs.setName("prereqs"); // NOI18N
        jScrollPane2.setViewportView(prereqs);

        reqLbl.setText("Requirement:");
        reqLbl.setName("reqLbl"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CourseDetailsView.class);
        options.setBackground(resourceMap.getColor("options.background")); // NOI18N
        options.setText("");
        options.setName("options"); // NOI18N

        optLbl.setBackground(java.awt.Color.yellow);
        optLbl.setText("Options:");
        optLbl.setName("optLbl"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        reqs.setEditable(false);
        reqs.setName("reqs"); // NOI18N
        jScrollPane3.setViewportView(reqs);

        deptDirLbl.setBackground(java.awt.Color.yellow);
        deptDirLbl.setText("Dept Directions:");
        deptDirLbl.setName("deptDirLbl"); // NOI18N

        deptDir.setBackground(new java.awt.Color(255, 255, 0));
        deptDir.setText("");
        deptDir.setName("deptDir"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deptDirLbl)
                            .addComponent(optLbl)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(descripLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(crcLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reqLbl)
                            .addComponent(prereqLbl))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(options, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(deptDir, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crcLbl)
                    .addComponent(courseTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripLbl)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prereqLbl)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reqLbl)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optLbl)
                    .addComponent(options, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deptDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deptDirLbl))
                .addContainerGap())
        );

        courseTitle.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel courseTitle;
    private javax.swing.JLabel crcLbl;
    public javax.swing.JLabel deptDir;
    private javax.swing.JLabel deptDirLbl;
    public javax.swing.JTextPane descrip;
    private javax.swing.JLabel descripLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel optLbl;
    private javax.swing.JLabel options;
    private javax.swing.JLabel prereqLbl;
    private javax.swing.JTextPane prereqs;
    private javax.swing.JLabel reqLbl;
    private javax.swing.JTextPane reqs;
    // End of variables declaration//GEN-END:variables
}
