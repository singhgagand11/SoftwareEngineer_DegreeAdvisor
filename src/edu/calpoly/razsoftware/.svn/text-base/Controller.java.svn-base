package edu.calpoly.razsoftware;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/** The Controller class represents the controller in a Model-View-Controller
 * architecture (MVC).  It provides an interface for all the actions that can
 * be performed on a flowchart.
 * 
 * @author jkwalsh
 */
public class Controller
{
    private Flowchart flowchart;
    private CourseList courses;
    private I_Catalog catalog;
    private FlowcharterView view;  // reference to view
    /**
     * A global flag to determine if the application is saved, defaults to true.
     * Is reset to true whenever the user saves. Is changed to false when they
     * add or remove a class from the taken list
     */
    private boolean saved = true;
    /**
     * If the user has saved or loaded a file this variable is a reference to
     * the pointer. This is used so the save functionality knows what file to
     * save to
     */
    private File savedFile;
    /**
     * The saved file extension for this application. The save and load dialogs
     * filter their view by this extension. The save dialog guarantees that the
     * extension is appended onto the end of the file name.
     */
    private static final String kFileExtension = "us";
    /**
     * The name of the application to display n the about box and the
     * application title bar
     */
    public static final String kApplicationName = "Degree Advisor";

    /** Creates a new Controller object using the specified Catalog.
     * @param catalog The Requirements contained in this catalog are used
     * to build a flowchart.
     */
    public Controller(I_Catalog catalog)
    {
        this.courses = catalog.getAllCourses();
        this.catalog = catalog;
        flowchart = new Flowchart(catalog);
        //PEER TEAM TESTING DEFECT ROSS #7
        if (Database.loadMostRecent() != null)
        {
            savedFile = new File(Database.loadMostRecent());
        }
    }

    /** Sets the GUI which this Controller uses to display changes in the model.
     * @param frame A FlowcharterView that will be connected to this Controller
     */
    public void setView(FlowcharterView frame)
    {
        this.view = frame;
    }

    /** Performs the relevant action for a mouse-click in the flowchart view,
     * depending on whether the click was a single- or double-click.
     * @param clicks The number of clicks that ocurred: e.g 1 for single clicks,
     * 2 for double clicks.  
     */
    public void mouseClicked(int clicks)
    {
        // IF view has valid cell selected THEN
        if (view.currentRow() != -1 && view.currentCol() != -1)
        {
            I_Requirement req = flowchart.getValueAt(view.currentRow(),
                                                     view.currentCol());
            // DOUBLE clicked - so popup dialog
            // DEFECT #344 - UpdateReqDialog needs to check if blank cell
            // is selected and not display
            if (clicks == 2 && req != null)
            {
                view.showUpdateDialog(req);
            }
            view.updateDetails(req);
        }
    }

    /** Handles a right click event from the flowchart view.
     * @param row The row in which the right-click took place.
     * @param col The column in which the right-click took place.
     */
    public void mouseRightClicked(int row, int col)
    {
        //SET req to requirement at row, col in the flowchart
        I_Requirement req = flowchart.getValueAt(row, col);

        //IF there is a requirement at row/col in the flowchart
        if (req != null)
        {
            //SET course to course needed to fulfill the requirement
            I_Course course = req.getCourse();
            // Is there a course at this cell && is eligible THEN
            if (course != null
                    && course.isEligible(flowchart.buildPriorCourses(col, true)))
            {
                // Toggle the course completed attribute
                course.setCompleted(!course.isComplete());
                // Indicate that the chart has been changed
                // CALL setSaved with false
                setSaved(false);
            }
            //ENDIF
        }
        //ENDIF
    }

    /** Moves the contents of a cell to a new cell.
     * @param fromRow The row of the source cell.
     * @param fromCol The column of the source cell.
     * @param toRow The row of the destination cell.
     * @param toCol The column of the destination cell.
     */
    public void moveCourse(int fromRow, int fromCol, int toRow, int toCol)
    {
        int newRow = flowchart.moveCourse(fromRow, fromCol, toRow, toCol);
        // Did the move take place? (newRow will be -1 if move was illegal)
        if (newRow >= 0)
        {
            view.moveCursor(newRow, toCol);
            I_Requirement req = flowchart.getValueAt(newRow, toCol);
            view.updateDetails(req);
        }
        // Indicate that the chart has been changed
        // CALL setSaved with false
        setSaved(false);
    }

    /** Gets the size of the chart.
     * @return The number of quarters in the flowchart.
     */
    public int getChartSize()
    {
        // RETURN number of quarters in flowchart
        return flowchart.getNumQuarters();
    }

    /** Gets the current quarter, e.g the column immediately to the right of
     * the right-most course that is marked as taken.
     * @return The column of the current quarter.
     */
    public int findCurrentQuarter()
    {
        return flowchart.findCurrentQuarter();
    }

    /** Gets a course from the catalog
     * @param name The name of the course, z.B "CSC101"
     * @return A Course object whose name matches the given name, or null if a
     * Course was not found with the given name.
     */
    public Course getCourseFromCatalog(String name)
    {
        // INIT result to null
        I_Course result = null;
        // CALL CourseList.getCourses(), RETURNING list
        Set<I_Course> list = courses.getCourses();

        // FOR each Course in list
        for (I_Course crs : list)
        {
            // IF the name of the current Course matches name THEN
            if (crs.getName().equals(name))
            {
                // ASSIGN current Course to result
                result = crs;
            } // ENDIF
        } // ENDFOR
        // RETURN result
        return (Course) result;
    }

    /** Gets the names of all the departments in the catalog
     * @return A list of department names.
     */
    public Set<String> getDeptsFromCatalog()
    {
        // CALL Catalog.getDeptNames() RETURNING names
        Set<String> result = catalog.getDeptNames();
        // RETURN result
        return result;
    }

    /** Gets the names of all courses in a department.  Warning: assumes that
     * Course.getDept() returns a List<String> which contains no duplicates.
     * For example {"CSC", "CPE", "CSC"} 
     * @param dept The name of the department.
     * @return A list of Strings that is the names of Courses in the given
     * department.
     */
    public List<String> getNamesFromCatalog(String dept)
    {
        // INIT result to new List of Strings
        List<String> result = new java.util.ArrayList<String>();
        // CALL Catalog.getCourses() RETURNING courses
        Set<I_Course> crsSet = this.courses.getCourses();
        // FOR each course in crs LOOP
        for (I_Course crs : crsSet)
        {
            // IF deparment names match THEN
            if (crs.getDept().contains(dept))
            {
                // CALL result.add() with crs.getName()
                result.add(crs.getName());
            }
        }
        return result;
    }

    /** Creates a new requirement from a String.
     * @param requirement The name of the new requirement.
     */
    public void newRequirement(String requirement)
    {
        // TODO implement
    }

    /**
     * Handle the user's file Open request.
     */
    public void open()
    {
        // IF the user didn't accidently click the load button
        if (confirmOpen(1) != JOptionPane.CANCEL_OPTION)
        {
            // prompt the user for the file to load
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(kSaveLoadFilter);
            int choice = chooser.showOpenDialog(view.getFrame());
            // If the user gives a file to load
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                // CALL Database to open the file and load flowchart
                flowchart.fillChart(Database.loadChart(
                        chooser.getSelectedFile().getAbsolutePath(), catalog));
                savedFile = chooser.getSelectedFile();
            }
        }
        flowchart.fireTableDataChanged();
    }

    /**
     * Prompts the user to save any changes they have made since the last save
     *
     * @return the choice the user made
     */
    int confirmOpen(int val)
    {
        int choice = JOptionPane.YES_OPTION;
        // IF the application has been changed since the last save
        if (!saved)
        {
            // prompt the user to save their progress
            choice =
                    JOptionPane.showConfirmDialog(
                    new JFrame(),
                    "You have unsaved changes, "
                    + "do you wish to save?",
                    "Save",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            // IF the user wasnts to save be sure to save
            if(val == 0 && choice != JOptionPane.NO_OPTION || choice == JOptionPane.YES_OPTION)
            {    
                save();
            }
            // ENDIF
            
        }// ENDIF
        
        return choice;

    }

    /**
     * Handle the user's Save request.
     */
    public void save()
    {
        // IF the user is trying to save without having a file to back the save
        if (savedFile == null)
        {
            // Prompt the user for a file
            saveAs();

        }
        // ELSE there is a file to back the save
        else
        {
            try
            {
                // CALL Database to save the flowchart
                Database.saveFlowchart(savedFile.getAbsolutePath(), flowchart);
            }
            catch (IOException ex)
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// ENDIF
    }

    /**
     * Handle user's Save As request.
     */
    public void saveAs()
    {
        JFileChooser chooser = new JFileChooser()
        {
            @Override
            public void approveSelection()
            {
                File selectedFile = getSelectedFile();
                // IF the selected file doen't have the correct extension
                if (!selectedFile.getName().endsWith("." + kFileExtension))
                {

                    // append the extension onto the name
                    selectedFile =
                            new File(selectedFile.getPath() + "."
                            + kFileExtension);

                }// ENDIF
                // If the user is overwriting a already existing file
                if (selectedFile.exists() && !selectedFile.equals(savedFile))
                {
                    int overWrite =
                            JOptionPane.showConfirmDialog(this,
                            getSelectedFile().getName()
                            + " already exists.\n"
                            + "Do you want to replace it?",
                            "Confirm Save As",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    // IF the user wants to overwrite
                    if (overWrite == JOptionPane.OK_OPTION)
                    {
                        // save the file
                        savedFile = selectedFile;
                        // CALL setSaved with true
                        setSaved(true);
                        super.approveSelection();
                    }// ELSEIF the user wants to cancel
                    else if (overWrite == JOptionPane.CANCEL_OPTION)
                    {
                        // cancel the save
                        super.cancelSelection();
                    }
                    return;
                }// ELSE the file does not exist
                else
                {
                    savedFile = selectedFile;
                    super.approveSelection();
                }// ENDIF

            }
        };
        chooser.setFileFilter(kSaveLoadFilter);
        chooser.setSelectedFile(savedFile);
        int choice = chooser.showSaveDialog(view.getFrame());
        // IF the user selects a file to save in
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            // save their state
            save();
        }// END IF
    }
    /**
     * A filter that only displays the files with the extension that can be
     * loaded as a list of courses taken
     */
    private final FileFilter kSaveLoadFilter = new FileNameExtensionFilter(
            "User State",
            kFileExtension);

    /**
     * Updates the global saved variable and changes the title of the window to
     * show that it is saved
     */
    private void setSaved(boolean saved)
    {
        this.saved = saved;
        String title = kApplicationName;
        // IF there is currently a file the user is working with
        if (savedFile != null)
        {
            // add the file's name to the title
            title += " - " + savedFile.getName();
        }// ENDIF
        // IF the application has been changed since the last save
        if (!saved)
        {
            // Add a notifier to the title
            title += "*";
        }// ENDIF

        view.getFrame().setTitle(title);

    }

    /**
     * terminates the application first asking the user if the want to save
     * their state
     */
    public void quit()
    {
        int quitType = confirmOpen(1);
        
        // IF the user didn't accidently click the exit button
        if (quitType != JOptionPane.CANCEL_OPTION && 
                quitType != JOptionPane.CLOSED_OPTION)
        {
            // IF the user didn't cancel while the save dialog was active
            if (quitType == JOptionPane.YES_OPTION && saved)
            {
                
            }   
            System.exit(0);
        }// ENDIF

    }

    /** Examines each requirement in the the flowchart for how many units have
     * been completed. 
     * Precondition: flowchart is not null
     * @return The total number of units in courses that were completed.
     */
    public int getCompletedUnits()
    {
        // INIT totalUnits to 0
        int totalUnits = 0;
        // CALL Flowchart.getRequirements RETURNING reqsList
        List<I_Requirement> reqsList = this.flowchart.getRequirements();
        // FOR each requirement in reqsList LOOP
        for (I_Requirement rqmt : reqsList)
        {
            // IF requirement is a single Course THEN
            if (rqmt.getCourse() != null)
            {
                // CALL rqmt.getCourse() RETURNING crs
                I_Course crs = rqmt.getCourse();
                // IF crs is complete THEN
                if (crs.isComplete())
                {
                    // CALL Requirement.getUnits() RETURNING reqUnits
                    int units = crs.getUnits();
                    // ADD reqUnits to totalUnits
                    totalUnits += units;
                } // ENDIF
            } // ENDIF
            // ESLE requirement is multiple Courses
            else
            {
                // CALL rqmt.getFulfillmentOptions() RETURNING courses
                Set<I_Course> crsSet = rqmt.getFulfillmentOptions();
                // FOR each Course in courses LOOP
                for (I_Course crs : crsSet)
                {
                    // IF crs is complete THEN
                    if (crs.isComplete())
                    {
                        // CALL Requirement.getUnits() RETURNING reqUnits
                        int units = crs.getUnits();
                        // ADD reqUnits to totalUnits
                        totalUnits += units;
                    } // ENDIF
                } // ENDFOR
            }// ENDIF
        }// ENDFOR

        // CALL Flowchart.unitsCompleted, RETURNING totalUnits
        totalUnits = flowchart.unitsCompleted();

        // INIT totalUnits to 0
        totalUnits = 0;
        // ASSIGN courses in catalog to crsSet
        Set<I_Course> crsSet = catalog.getAllCourses().getCourses();
        // FOR each course in crsSet
        for (I_Course crs : crsSet)
        {
            // IF crs is complete THEN
            if (crs.isComplete())
            {
                // CALL Requirement.getUnits() RETURNING reqUnits
                int units = crs.getUnits();
                // ADD reqUnits to totalUnits
                totalUnits += units;
            } // ENDIF
        } // ENDFOR
        // RETURN totalUnits
        return totalUnits;
    }

    /** Gets the name of the flowchart savefile.
     * @return A String that is the name of the savefile currently being
     * used by the controller.
     */
    public String getSavefileName()
    {


        // Has a save file been created yet?
        if (savedFile == null)
        {
            // RETURN "no current savefile
            return "no current savefile";
        }
        // CALL savedFile.getName(), RETURNING name

        // RETURN name
        return savedFile.getName();
    }

    /** Gets the name of the catalog.
     * @return A String that is the name of the catalog currently being used by
     * the controller z.B "CSC 07-09"
     */
    public String getCatalogVersion()
    {
        // CALL Catalog.getName() RETURNING catName
//        catalog.getName();
        // RETURN catName
        return "CSC 07-09";
    }

    /** Adds an empty column in the specified column of the flowchart
     * @param col The column (0 inclusive) where the quarter will be added
     */
    public void addQuarter(int col)
    {
        // CALL Flowchart.addQuarter() with the number of quarters in the flowchart
        flowchart.addQuarter(col);
        // CALL setSaved with false
        setSaved(false);
    }

    /** Removes a specified column from the flowchart.  The colum of the 
     * flowchart specified by col will be removed if it did not contain any
     * courses.
     * @param col The column to be removed.
     */
    public void remove(int col)
    {
        // ASSIGN courses in column specified by variable col to list
        List<I_Requirement> list = flowchart.getList(col);
        // IF list is empty THEN
        if (list.isEmpty())
        {
            // CALL Flowchart.deleteQuarter() with col
            flowchart.deleteQuarter(col);
            // CALL setSaved with false
            setSaved(false);
        } // ENDIF
    }

    /** This is only called once constructor of the view so the JTable can
     *  have a reference to it's tablemodel.
     * @return Flowchart the underlying table model
     */
    public Flowchart getModel()
    {
        return (Flowchart) flowchart;
    }
    
    // DEFECT 375 - CHECK TO SEE IF FLOWCHART ALREADY CONTAINS COURSE
    public boolean flowChartHas(I_Course course)
    {
        HashSet<Contingent> priors = flowchart.
                buildPriorCourses(flowchart.getColumnCount(), false);
        Contingent check = (Contingent) course;
        if(priors.contains(check))
        {
            return true;
        }
        return false;
    }
    
    // DEFECT 375 - need to display pop up box if Class Already Exists
    void classExists()
    {
        JOptionPane.showConfirmDialog(
                new JFrame(),
                "This class is already in your flowchart.",
                "Class Already Exists",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    
}
