package pt.tecnico.myDrive.presentation;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.myDrive.Main;
import pt.tecnico.myDrive.domain.Manager;

/**
 * Created by lolstorm on 26/04/16.
 */
public class MyDriveShell extends Shell {

    public static void main(String[] args) throws Exception {
        Main.init();
        Main.setup();
        MyDriveShell sh = new MyDriveShell();
        sh.execute();
    }

    /**
     * Add commands here.
     */
    public MyDriveShell() {
        super("MyDrive");
        new ChangeDirectory(this);
        new EnvVariable(this);
        new ExecuteFile(this);
        new ListDirectory(this);
        new LoginUser(this);
        new WriteFile(this);
        new Token(this);
    }
}
