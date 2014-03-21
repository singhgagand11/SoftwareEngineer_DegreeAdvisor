package edu.calpoly.razsoftware;

import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Panel for displaying requirement status such as if the requirement is
 * completed and set of prerequisites.
 * 
 * 11/17/12 Fixed defect #279 [line 90-104: using iterator rather than for each
 * statement] gasingh
 * 
 * 11/17/12 Fixed defect #280 [moved lblDescrip.setText("Description: " + 
 * crs.getDescription()); to 73]  gasingh
 *
 * @author jdalbey, gasingh
 */
public class CourseDetailsViewOld
{
    //String length for fullfillment
    private static final int kFillLen = 60;

    /**
     * A Panel for displaying requirement status such as if the requirement is
     * completed and set of prerequisites.
     * @param req requirement to display information of
     * @return a panel containing req's information
     */
    public static JPanel getPanel(I_Requirement req)
    {
        JPanel panel = new JPanel();
        //repaint the panel
        panel.repaint();  // to erase old labels
        //remove all components in panel
        panel.removeAll();
        //IF req is not null
        if (req != null)
        {
            //SET the layout of the panel to new boxLayout with page axis
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            //SET crs to req's course
            I_Course crs = req.getCourse();
            //set the lblCourse to new JLabel with space as the text
            JLabel lblCourse = new JLabel("Course: ");
            //set the lblPrereqs to new JLabel with space as the text
            JLabel lblPrereqs = new JLabel("Prereqs: ");

            //SET the lblDescrip to new JLabel with space as the text
            JLabel lblDescrip = new JLabel("Description: ");
            //IF crs is not null
            if (crs != null)
            {
                //SET course to "Course: " followed by crs
                String course = "Course: " + crs.getName();
                //IF crs is completed
                if (crs.isComplete())
                {
                    //append "COMPLETED" to course
                    course += "  COMPLETED";
                }
                //ENDIF

                //SET lblCourse to new JLabel of course
                lblCourse = new JLabel(course);

                //SET reqs to course's prerequisites string
                String reqs = req.getCourse().getPreRequisitesString();

                //IF reqs is not null
                if (reqs != null)
                {
                    //SET lblPrereqs to new JLabel of "Prereqs" follows by reqs
                    lblPrereqs = new JLabel("Prereqs: " + reqs);
                }
                //ENDIF

                //SET the lblDescrip to new JLabel with course description
                lblDescrip.setText("Description: " + crs.getDescription());
            }
            //ENDIF


            //add lblCourse to the panel
            panel.add(lblCourse);

            //add lblDescrip to the panel
            panel.add(lblDescrip);
            //add lblPrereqs to the panel
            panel.add(lblPrereqs);

            //SET lblTitle to new JLabel of "Requirement" followd by req
            JLabel lblTitle = new JLabel("Requirement: " + req.toString());

            //add lblTitle to panel
            panel.add(lblTitle);

            //SET fulfill to fulfillment options of req's
            StringBuilder fulfill = new StringBuilder();

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
            }

            //SET options to new JLabel of space string
            JLabel options = new JLabel(" ");

            //IF the length of fulfill is greater than 60
            if (fulfill.length() > kFillLen)
            {
                //truncated to fulfill up to 60 character offset

                fulfill.setLength(kFillLen);
                //append ... to fulfill
                fulfill.append("...");
            }
            //ENDIF

            //SET options's text to "Options" followed by truncated
            options.setText("Options: " + fulfill.toString());
            //SET options's Opaque to true
            options.setOpaque(true);

            //SET options's background to yellow
            options.setBackground(Color.YELLOW);

            //ENDIF
            //add options to the panel
            panel.add(options);


        }
        return panel;
    }
}
