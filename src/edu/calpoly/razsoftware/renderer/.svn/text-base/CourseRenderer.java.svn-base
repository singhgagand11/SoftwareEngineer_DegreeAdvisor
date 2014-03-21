package edu.calpoly.razsoftware.renderer;

import edu.calpoly.razsoftware.Contingent;
import edu.calpoly.razsoftware.I_Flowchart;
import edu.calpoly.razsoftware.I_Requirement;
import java.awt.Color;
import java.awt.Component;
import java.util.HashSet;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A cell renderer for the cells in the table.
 *
 * 02/17/12 fixed defected 278 [line 135: fulfill.contains(kOrSeperator)]
 * Gasingh
 * 
 * @author J. Dalbey, gasingh
 * @version 10/14/2011
 */
public final class CourseRenderer extends DefaultTableCellRenderer
{
    // dark green
    private static final Color kGreen = new Color(17, 102, 13);
    //private or seperator keyword used in requirement toString
    private static final String kOrSeperator = "or";

    /**
     * Constructs a default table cell rendering
     */
    public CourseRenderer()
    {
        super();
    }

    /**
     * Returns: the default table cell renderer
     *
     * @param table - the JTable
     * @param value - the value to assign to the cell at
     * @param isSelected - true if cell is selected
     * @param hasFocus - true if cell has focus
     * @param row - the row of the cell to render
     * @param column - the column of the cell to render
     *
     * @return the default table cell renderer
     */
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column)
    {
        //SET cell to the default table cell renderer
        //from the parent class
        Component cell = super.getTableCellRendererComponent(table,
                                                             value,
                                                             isSelected,
                                                             hasFocus,
                                                             row, column);

        //SET jc to cell
        JComponent jc = (JComponent) cell;

        //setHorizontalAlignment(CENTER);
        this.setHorizontalAlignment(CENTER);
        //set the text of this component to empty
        setText(""); // default case - empty cell

        //IF value is not null
        if (value == null)
        {
            //SET cell's background to white
            cell.setBackground(Color.WHITE);
        }
        //ELSEIF value is an instance of Requirement
        else if (value instanceof I_Requirement)
        {
            //SET req to values
            I_Requirement req = (I_Requirement) value;

            //set the text of this component to formatCell for
            //the table, cell, requirment, and columen
            setText(formatCell(table, cell, req, column));

            //IF this component is selected
            if (isSelected)
            {
                //jc.setBorder(new MatteBorder(1, 0, 1, 0, Color.cyan) );
                //cell.setBackground(Color.LIGHT_GRAY);

                //SET the foreground of the cell to blue
                cell.setForeground(Color.BLUE);
            }
            //ELSE
            else
            {
                //SET the foreground of the cell to black
                cell.setForeground(Color.BLACK);
            }
            //ENDIF-ELSE
        }
        //ENDIF

        //return cell
        return cell;
    }

    /**
     * Returns a format description of a cell in the table. This method is used
     * to configure the rendering cell format appropriately before drawing.
     *
     * @param table - the JTable that is asking the renderer to draw; can be
     * null
     * @param cell - cell component which is to be render
     * @param req - requirement which is is to be rendered in the cell
     * @param col - the column index of the cell being drawn
     * @return a format string containing requirement information
     */
    String formatCell(JTable table, Component cell,
                      I_Requirement req, int col)
    {
        //SET underline to false
        boolean underline = false;

        //SET sb to new stringBuller with "<HTML>" tag
        StringBuffer sb = new StringBuffer("<HTML>");
        //req.getCourse();
        //IF the course of the requirement is not null
        if (req.getCourse() != null)
        {
            //SET fulfill to string of fulfillment options of req's
            String fulfill = req.toString(); //.getFulfillment();
            //IF filfill contains a 'or'
            if (fulfill.contains(kOrSeperator))
            {
                //SET underline to true
                underline = true;
                //APPEND <U> tag to sb
                sb.append("<U>");
            }

            //SET name to the title of the requirement course
            String name = req.getCourse().getTitle();

            //SET name to name of the requirement's course
            name = req.getCourse().getName();

            //SET obj to currenlty seleted cell in the table
            Object obj = (table.getModel().getValueAt(table.getSelectedRow(),
                                                      table.getSelectedColumn()));
            //SET current to selected cell
            I_Requirement current = (I_Requirement) obj;

            // If this course is a prereq for the currently selected course THEN
            if (current != null && current.getCourse() != null
                    && current.getCourse().getPreRequisitesString().contains(name))
            {
                // emphasize it by appending a '>' to name in bold and italic
                name = "<B><I>" + name + "&gt;</I></B>";
            }
            //append name to sb
            sb.append(name);
            //SET the background color to white
            cell.setBackground(Color.WHITE);

            //IF req is complete
            if (req.getCourse().isComplete())
            {
                //SET the background to dark green color taken
                cell.setBackground(kGreen);
            }
            //ENDIF

            //IF underline THEN
            if (underline)
            {
                //append the underline tage "</U"> to sb
                sb.append("</U>");
            }
            //ENDIF
        }
        //ELSE
        else
        {
            //APPEND <small> tag to sb
            sb.append("<small>");
            //APPEND "???" to sb for since course info is not
            sb.append("???");
            //APPEND the closing </small> tag to sb
            sb.append("</small>");
            //SET the background color of the cell to yellow
            cell.setBackground(Color.YELLOW);
        }
        //ENDIF-ELSE

        //DEFECT #278
        //IF req course is not null and there are other alternate options
        if (req.getCourse() != null && req.toString().contains(kOrSeperator))
        {
            //APPEND elipsis in small text to sb
            sb.append("<small>...</small>");
        }

        //CALL checkReqTitle with requirement and stringBuffer
        checkReqTitle(req, sb);
        //append "</HTML>" to sb
        sb.append("</HTML>");

        //CALL check course elifibility with tabke, cll, requirement, and column
        //number
        checkCourseEligiblity(table, cell, req, col);
        //System.out.println(sb.toString());

        //RETURN sb's string
        return sb.toString();
    }

    /**
     *A helper function used to obtain requirement title and modify the
     * the stringBuffer to hold the display information about the requirement.
     * (used to decrease cyclomatic complexity of formatcell)
     * @param req - requirement which is is to be rendered in the cell
     * @param sb - stringbuffer holder the display information of the requirement
     *  in HTML tag
     */
    private static void checkReqTitle(I_Requirement req, StringBuffer sb)
    {
        //IF req's tittle is a empty string
        if (req.getTitle().equals(""))
        {
            //IF course in requirement is null
            if (req.getCourse() != null)
            {
                //append the title of the course to sb
                sb.append(req.getCourse().getTitle());
            }
            //ELSE
            else
            {
                //print a internal error message
                System.out.println("Internal error: title is blank and course "
                        + "is null: " + req.getTitle());
            }
        }
        // I think it looks better if we only show the title when necesssary,
        // i.e., when there's no course specified yet.
        //ELSEIF requirement is not found (null)
        else if (req.getCourse() == null)
        {
            //append "<small> to sb
            sb.append("<small>");
            //append "? " and the req title to sb
            sb.append("? " + req.getTitle());
            //append "</small>" to sb
            sb.append("</small>");
        }
        //ENDIF-ELSE
    }

    /**
     * Helper function that modifies the cell based on it's eligibility and
     * completeness status
     ** (used to decrease cyclomatic complexity of formatcell)
     * @param table - the JTable that is asking the renderer to draw; can be
     * null
     * @param cell - cell component which is to be render
     * @param req - requirement which is is to be rendered in the cell
     * @param col - the column index of the cell being drawn
     */
    private static void checkCourseEligiblity(JTable table, Component cell,
                                              I_Requirement req, int col)
    {
        // If this item has a selected course that isn't completed
        if (req.getCourse() != null && !req.getCourse().isComplete())
        {
            //SET flow to the table's model
            I_Flowchart flow = (I_Flowchart) table.getModel();
            //SET prior to prior courses based on column, considering them
            //complete
            HashSet<Contingent> prior = flow.buildPriorCourses(col, true);

            //IF req isn't eligible
            if (!req.getCourse().isEligible(prior))
            {
                //SET the background of the cell to light gray
                cell.setBackground(Color.LIGHT_GRAY);


            } //ENDIF
            //SET prior to prior courses without considering completion
            prior = flow.buildPriorCourses(col, false);

            // IF the req is out of sequence
            if (!req.getCourse().inSequence(prior))
            {
                //SET the background of the cell to pink
                cell.setBackground(Color.pink);

            }
            //END IF
        }
        //ENDIF
    }
}
