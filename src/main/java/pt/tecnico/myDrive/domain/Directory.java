
package pt.tecnico.myDrive.domain;

import org.jdom2.Element;

import pt.tecnico.myDrive.exception.FileNotFoundException;
import pt.tecnico.myDrive.exception.FileIdNotFoundException;
import pt.tecnico.myDrive.exception.NameFileAlreadyExistsException;
import pt.tecnico.myDrive.exception.DeletePermissionException;
import pt.tecnico.myDrive.exception.ReadPermissionException;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class Directory extends Directory_Base {
    
    /**
     * Default Constructor.
     */ 
    public Directory() {
        super();
    }

    /**
     * Alternate Constructor for a Directory.
     * @param  owner User user owner of the file.
     * @param name Represents the name of the folder.
     * @param m represents
     */
    public Directory(User owner, String name, Directory parent, Manager m) {
        this();
        super.init(owner, name,parent, m);
        //getOwner().createLink(parent,"..", this, m);
        //getOwner().createLink(this,".", this, m);
    }

    /**
     * Alternate constructor to create a Link with xml.
     * @param  xml (Element JDOM) represents a File in xml format.
     */
    public Directory(Element xml) {
        super();
        importXml(xml);
    }
    
    /**
     * Imports a Directory to a persistent state (XML format).
     * @see PlainFile Link App Directory
     * @throws ImportDocumentException
     */
    @Override
    public void importXml (Element xml) {
        super.importXml(xml);
    }

    /**
     * @deprecated and replaced with new exportXml
     */
    @Deprecated
    public Element xmlExport(){
        Element node = super.xmlExport();

        Element filesElement = new Element("Files");
        node.addContent(filesElement);

        for (File f: getFileSet())
            filesElement.addContent(f.xmlExport());
        
        return node;
    }

    /**
     * Exports a Directory to a persistent state (XML format).
     * @see File
     * @return (Element JDOM) which represents a Directory.
     */
    @Override
    public Element exportXml () {
        
        return super.exportXml();
    }

    /**
     * Finds a file in a current given path.
     * @param link (String) represents a the path to the file (relative or absolute).
     * @return File represents the file found.
     * @throws FileNotFoundException occurs when the given path is invalid.
     */
    @Override
    public File getFileByPath (String link) throws FileNotFoundException {
    	if(link.equals("")) {
    		return this;
    	}
        String[] spliTest = link.split("/");
        String[] split = spliTest;
        String rest = "";
        String nomeInit = link;
        for(File path : getFileSet()) {
            if (spliTest.length != 1) {
                split = link.split("/",2);
                rest = split[1];
                nomeInit = split[0];
                if(path.getName().equals(nomeInit)){
                    return path.getFileByPath(rest);
                }
            }
            else {
                if(path.getName().equals(link)){
                    return path;
                }
            }
        }
        throw new FileNotFoundException(nomeInit);
    }

    /**
     * Removes the Files in a Directory.
     * @throws DeletePermissionException The user doesn't have the privilege to remove the directory.
     */
    @Override
    public void remove(User user)
    { 
        if(user.getPermissions().canDelete(this))
        {
            for(File f : getFileSet()) 
            {
                f.remove();
            }
            super.remove();
        }
        else
        {
            throw new DeletePermissionException(this.getName(), user.getUsername());
        }       
    }

    /**
     * Returns a list of files.
     * @return String represents the list of files.
     */
    @Override
    public String getContent(User user) {
        
        if (!user.getUsername().equals("root")){
            if (!user.equals(getOwner())){
                if (!getPermissions().worldCanRead())
                    throw new ReadPermissionException(getName(), user.getUsername());
            }
            else
                if (!getPermissions().userCanRead())
                    throw new ReadPermissionException(getName(), user.getUsername());
        }
        return listContent();
    }

    /**
     * @// FIXME: 18/03/16 needs refactoring.
     * @return list (String) returns a list of files inside a directory.
     */
    public String listContent(){
    	String[] names = new String[getFileSet().size()+2];
    	String[] entries = new String[getFileSet().size()+2];
        String list = "";
        String tempStr = "";
        int k = 2;
        for(File path : this.getFileSet()){
        	entries[k]= path.toString();
        	names[k] = path.getName();
        	k++;
        }
        for (int t = 2; t < names.length - 1; t++) {
            for (int i= 2; i < names.length - t -1; i++) {
                if(names[i+1].compareTo(names[i])<0) {
                    tempStr = names[i];
                    names[i] = names[i + 1];
                    names[i + 1] = tempStr;
                    tempStr = entries[i];
                    entries[i] = entries[i + 1];
                    entries[i + 1] = tempStr;
                }
            }
        }
        Directory p = getParent();
        entries[0] = toString().replace(getName(), ".");
        entries[1] = p.toString().replace(p.getName(), "..");
        for (int j = 0; j < names.length; j++){
        	list += entries[j] + "\n";        	
        }
        return list;
    }

    
    /**
     * Adds a File to a Directory.
     * @param file (File) receives a file.
     * @see File
     * @throws NameFileAlreadyExistsException occurs when adding a File in the same Directory has same name.
     */
    @Override
    public void addFile(File file) throws NameFileAlreadyExistsException {
        for (File fName : this.getFileSet()){
            if (fName.getName().equals(file.getName())){
                throw new NameFileAlreadyExistsException(file.getName());
            }
        }
        super.addFile(file);
    }


    /**
     * Searches for a File by name in a Directory.
     * @param  name (String) receives a String which is the name of the File.
     * @throws FileNotFoundException when there is no File with that name.
     * @return File returns the file with the name received.
     */
    public File searchFile(String name) throws FileIdNotFoundException {
        for(File f: getFileSet())
            if (f.getName().equals(name))
                return f;
        throw new FileNotFoundException(name);
    }

    /**
     * Search a File by id in a Directory
     * @param id (int) receives a String which is the id of the File
     * @see File
     * @throws FileIdNotFoundException
     */
    public File searchFile(int id) throws FileIdNotFoundException {
        for(File f: getFileSet())
            if (f.getId() == id)
                return f;
        throw new FileIdNotFoundException(id);
    }

    /**
     * Overrides original toString() to the current object implementation.
     * @return String represents the output string of a Directory.
     */
    public String toString(){
        String a = super.toString();
        String dim = getFileSet().size() + "";
        String username = this.getOwner().getUsername();
        String modified = getModified().toString("MMM dd hh:mm");
        String rest = dim + " " + username + " " + getId() + " " + modified + " " + this.getName();
        return a + rest;
    }
}
