/*
 * FlowcharterApp.java
 */
package edu.calpoly.razsoftware;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 * @author jkwalsh
 */
public class FlowcharterApp extends SingleFrameApplication
{
    /** control for handling mouse events for the app **/
    private Controller control;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup()
    {

        CourseList courseList =
                new CourseList(getClass().getResourceAsStream("Catalog.json"));
        I_Catalog catalog = new Catalog(courseList);
        control = new Controller(catalog);
        FlowcharterView view = new FlowcharterView(this, control);
        control.setView(view);
        show(view);
        view.init();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root The root of the component hierarchy.
     */
    @Override
    protected void configureWindow(java.awt.Window root)
    {
        // Defect #359
        //add window lister to the root window
        
        
        root.addWindowListener(new WindowAdapter()
        {
            /**
             * handles windowCLosing events
             */
            @Override
            public void windowClosing(WindowEvent evt)
            {
                //CALL quit in control
                control.confirmOpen(0);
            }
        });
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of FlowcharterApp
     */
    public static FlowcharterApp getApplication()
    {
        return Application.getInstance(FlowcharterApp.class);
    }

    /**
     * Main method launching the application.
     * @param args Arguments from the command line.
     */
    public static void main(String[] args)
    {
        launch(FlowcharterApp.class, args);
    }
}
