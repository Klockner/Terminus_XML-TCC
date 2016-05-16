package Starter;

import GUI.MainWindow;
import Program.ControlClass;

//Application start class
public class Starter {
    
    public static void main(String[] args) {
        ControlClass controlClass;
        MainWindow programWindow;
        
        controlClass = new ControlClass(); //Instantiates a new control class
        programWindow = new MainWindow(controlClass); //Instantiates the program window passing the control class
        programWindow.setVisible(true);
    }
}
