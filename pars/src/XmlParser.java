import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class XmlTree{
    public JComponent item;
    public Attributes attr;
    public String name;
    public ArrayList<XmlTree> children;
    public XmlTree parent;

    public XmlTree addChild(XmlTree cur) {

        this.children.add(cur);
        cur.parent = this;
        return cur;
    }

    public XmlTree NextCont(int id){
       return this.children.get(id);
    }
    public XmlTree NextChild(int id){
       return this.children.get(id);
    }

}
public class XmlParser extends DefaultHandler{
    public XmlTree curItem;
    public void parseXml(String uri)
  {
    try
    {
      SAXParserFactory spf = SAXParserFactory.newInstance();
      SAXParser sp = spf.newSAXParser();
      sp.parse(uri, this);
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }


  public void startDocument()
  {
      curItem = new XmlTree();
      curItem.name = "root";


  }


  public void startElement(String namespaceURI, String localName,
                           String rawName, Attributes attrs)
  {
      curItem = curItem.addChild(new XmlTree());
      curItem.name = rawName;
      curItem.attr = attrs;
  }
  public void characters(char ch[], int start, int length)
  {

      if (curItem.name.equals("panel")) {
            curItem.item = new JPanel();

        }
        if (curItem.name.equals("label")) {

            curItem.item = new JLabel(new String(ch, start, length));

        }
        if (curItem.name.equals("text-field")) {
            curItem.item = new JTextField(new String(ch, start, length));

        }
        if (curItem.name.equals("button")) {
            curItem.item = (new JButton(new String(ch, start, length)));
        }
  }
  public void ignorableWhitespace(char ch[], int start, int length)
  {
    characters(ch, start, length);
  }
  public void endElement(String namespaceURI, String localName,
                         String rawName)
  {
      curItem = curItem.parent;
  }


  public void endDocument()
  {
      System.out.print("Parsing finished");
  }

  public void warning(SAXParseException ex)
  {
    System.err.println("[Warning] "+
                       getLocationString(ex)+": "+
                       ex.getMessage());
  }


  public void error(SAXParseException ex)
  {
    System.err.println("[Error] "+
                       getLocationString(ex)+": "+
                       ex.getMessage());
  }


  public void fatalError(SAXParseException ex)
    throws SAXException
  {
    System.err.println("[Fatal Error] "+
                       getLocationString(ex)+": "+
                       ex.getMessage());
    throw ex;
  }


  private String getLocationString(SAXParseException ex)
  {
    StringBuffer str = new StringBuffer();

    String systemId = ex.getSystemId();
    if (systemId != null)
    {
      int index = systemId.lastIndexOf('/');
      if (index != -1)
        systemId = systemId.substring(index + 1);
      str.append(systemId);
    }
    str.append(':');
    str.append(ex.getLineNumber());
    str.append(':');
    str.append(ex.getColumnNumber());

    return str.toString();
  }
}
