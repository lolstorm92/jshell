package pt.tecnico.myDrive;

import pt.tecnico.myDrive.domain.*;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;
import org.jdom2.input.SAXBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;
import java.io.IOException;


public class Main {

    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String [] args) {
        try {
            init();
            for (String s: args) xmlScan(new java.io.File(s));
            //setup();
            //xmlPrint();
            //debug();
        } finally {
            // ensure an orderly shutdown
            FenixFramework.shutdown();
        }
    }

    @Atomic
    public static void init() { // empty phonebook
        logger.trace("Init lol: " + FenixFramework.getDomainRoot());
        Manager.getInstance();

     //   logger.trace(Manager.getInstance().getSuperuser().getFileByPath("/home") + "<----------------------------");

    }

    /**
     * Exports the Manager to a xml file
     */
    @Atomic
    public static void xmlPrint() {
        logger.trace("xmlPrint: " + FenixFramework.getDomainRoot());
        Document doc = Manager.getInstance().exportXml();
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());


        try { xmlOutput.output(doc, new PrintStream(System.out));
        } catch (IOException e) { System.out.println(e); }
    }

    /**
     * Debug Xml
     */
    @Atomic
    public static void debug() {
        logger.trace("xmlPrint: " + FenixFramework.getDomainRoot());
        Document doc = Manager.getInstance().xmlExport();
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());


        try { xmlOutput.output(doc, new PrintStream(System.out));
        } catch (IOException e) { System.out.println(e); }
    }

    @Atomic
    public static void setup() { // phonebook with debug data
        logger.trace("Setup: " + FenixFramework.getDomainRoot());
        Manager m = Manager.getInstance();
        /*User su = m.getSuperuser();
        String n = "";
        File rootDir = su.getFileByPath("/");

        Directory usr = new Directory(su, "usr", (RootDirectory) rootDir, m);
        Directory local = new Directory(su, "local", usr, m);

        File home = su.getFileByPath("/home");

        User user = new User("Biana","yommere","pass", (short) 0xF0, m);

        //m.deleteUser(user);
        
        // #1
        for (User u : m.getUsersSet())
            n += u.getUsername() + "\n";
        File readme = new PlainFile(su, "README", n, (Directory) home, m);

        // #2
        Directory bin = new Directory(su, "bin", local, m);

        // #3
        System.out.println(readme.getContent(Manager.getInstance().getUserByUsername("root")));
        
        // #5
        xmlPrint();
        
        // #6
        readme.remove(su);
        
        // #7
        */
        //User user = new User("Biana","yommere","pass", (short) 0xF0, m);
        //Session s = new Session(user);
        //File home = user.getFileByPath("/home",s.getToken());
        //for (File f:
        //        ((Directory) home).listContent(s.getToken())) {
        //    System.out.println(f);
        //}

    }   

    /**
     * Scans the xml file and imports to database.
     * @param file (Java.io.File) represents a file. 
     */
    @Atomic
    public static void xmlScan(java.io.File file) {
        Manager m = Manager.getInstance();
        
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = (Document)builder.build(file);
            m.importXml(document.getRootElement());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

}
