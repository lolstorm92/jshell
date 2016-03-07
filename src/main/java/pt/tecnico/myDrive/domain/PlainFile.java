package pt.tecnico.myDrive.domain;
import org.jdom2.Element;


/**
 * 
 */
public class PlainFile extends PlainFile_Base {

    /**
     * Default constructor to create PlainFile
     */
	public PlainFile() {
		super();
	}

    /**
     * Alternate construtor to create a File with xml.
     * @param  xml Element (JDOM library type) which represents a File.
     */
    public PlainFile(Element xml) {
        super();
        importXml(xml);
    }

    /**
     * Imports a PlainFile from persistent state (XML format).
     * @throws ImportDocumentException
     */
    @Override
    public void importXml (Element xml) {
        Element node = xml;
        super.importXml(node);
        String content = node.getAttribute("content").getValue();
        setContent(content);
    }

	/**
	 * Exports a PlainFile to a persistent state (XML format),
	 * @see File
	 * @return Element (JDOM library type) which represents a PlainFile
	 */
	@Override
	public Element exportXml () {
		Element node = super.exportXml();
		node.setAttribute("content", getContent());
		return node;
	}
    
	public File getFileByPath (String link){
		return this;
	}
}
