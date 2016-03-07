package pt.tecnico.myDrive.domain;
import org.jdom2.Element;


public class App extends App_Base {
    
    /**
     * Default constructor.
     */
    public App() {
        super();
    }

    /**
     * Alternate construtor to create a App with xml.
     * @param  xml Element (JDOM library type) which represents a File.
     */
    public App(Element xml) {
        super();
        importXml(xml);
    }

    /**
     * Imports a App from persistent state (XML format).
     * @throws ImportDocumentException
     */
    @Override
    public void importXml (Element xml) {
        super.importXml(xml);
    }

	/**
	 * Exports an App to a persistent state (XML format),
	 * @see PlainFile
	 * @return Element (JDOM library type) which represents an App
	 */
	@Override
	public Element exportXml () {
		return super.exportXml();
	}
	
    public File getFileByPath (String link){
    	throw new UnsupportedOperationException("Not Implemented!");
    }

    public void remove(){throw new UnsupportedOperationException("Not Implemented!");}  
}
