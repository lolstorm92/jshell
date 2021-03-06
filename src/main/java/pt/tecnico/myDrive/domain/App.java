package pt.tecnico.myDrive.domain;
import org.jdom2.Element;

import pt.tecnico.myDrive.Main;
import pt.tecnico.myDrive.exception.ExecutePermissionException;
import pt.tecnico.myDrive.presentation.Hello;
import pt.tecnico.myDrive.presentation.Shell;

import pt.tecnico.myDrive.exception.FileNotFoundException;

import java.lang.ClassNotFoundException;
import java.lang.NoSuchMethodException;
import java.lang.SecurityException;
import java.lang.IllegalAccessException;
import java.lang.IllegalArgumentException;
import java.lang.reflect.InvocationTargetException;

import pt.tecnico.myDrive.exception.ExecuteFileException;


import java.io.UnsupportedEncodingException;

 
public class App extends App_Base {
    
    /**
     * Default constructor.
     */
    public App() {
        super();
    }

    /**
     * Alternate constructor to create a App with xml.
     * @param  xml (Element JDOM) which represents a File.
     */
    public App(Element xml) {
        super();
        importXml(xml);
    }

    /**
     * Alternate constructor to create a App.
     * @param owner (User) represents a User.
     * @param name (String) represents the name a App.
     * @param content (String) represents the content of the App.
     * @param m (Manager) represents an instance of Manager.
     */
    public App(User owner, String name, String content, Directory parent, Manager m) {
        super();
        super.init(owner, name, parent, m);
        initContent(content);
    }

    /**
     * Interface method.
     * @throws UnsupportedEncodingException occurs always if called directly with an App.
     */
    public File getFileByPath (String link) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    /**
     * Executes App
     */
    @Override
    public void execute(String token, String [] args){
        User u = Manager.getInstance().getUserByToken(token);
		if (!u.getPermissions().canExecute(this))
			throw new ExecutePermissionException(getName(), u.getUsername());
		if (args == null)
			args = new String[]{};
		try{
			
			Shell.run(u.getFileByPath(this.getPath() +  this.getName(), token).getContent(token), args);
		} catch (ClassNotFoundException | SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) { throw new ExecuteFileException(); }
	}
}
