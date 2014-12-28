package ru.mail.timelimit;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import ru.mail.timelimit.controller.Controller;
import ru.mail.timelimit.controller.SimpleController;
import ru.mail.timelimit.model.Model;
import ru.mail.timelimit.model.SimpleModel;
import ru.mail.timelimit.view.SimpleView;
import ru.mail.timelimit.view.View;

public class Main 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException 
    {
        SwingUtilities.invokeAndWait(new Runnable() 
        {
            @Override
            public void run() 
            {
                view = new SimpleView();
            }
        });
        model = new SimpleModel();
        controller = new SimpleController(view, model);
    }
    
    private static View view;
    private static Controller controller;
    private static Model model;
}

