package pt.tecnico.myDrive.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.myDrive.service.BaseServiceTest ;
import pt.tecnico.myDrive.presentation.*;

public class SystemTest extends BaseServiceTest {

    private MyDriveShell  sh;

    protected void populate() {
        sh = new MyDriveShell();
    }

    @Test
    public void success() {
        /*new Import(sh).execute(new String[] { "other.xml" } );
        new CreatePerson(sh).execute(new String[] { "Rui" } );
        new CreateContact(sh).execute(new String[] { "Rui", "SOS", "112" } );
        new List(sh).execute(new String[] { } );
        new RemoveContact(sh).execute(new String[] { "Xana", "Xico" } );
        new RemovePerson(sh).execute(new String[] { "Sofia" } );
        new Export(sh).execute(new String[] { } );*/
    }
}