import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.jndi.dns.DnsName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.*;
import java.util.List;

class XmlTree{
    public JComponent item;
    public Attributes attr;
    public String name;
    public ArrayList<XmlTree> children;
    public XmlTree parent;
    public List<String> a = new ArrayList<String>();

    public void addChild(XmlTree cur) {

        this.children.add(cur);
        cur.parent = this;
       // this = cur;

    }

 /*   public XmlTree NextCont(int id){
       return this.children.get(id);
    }
    public XmlTree NextChild(int id){
       return this.children.get(id);
    }
     */
}
public class XmlParser extends DefaultHandler{
    public XmlTree curItem;
   // public XmlTree root;
    public int i;

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

  public XmlTree getTree(){
    /*  while(!curItem.name.equals("root"))
      {
          curItem = curItem.parent;
      } */
      return  curItem;
  }
  public void startDocument()
  {
      curItem = new XmlTree();
      curItem.name = "frame";
      curItem.children = new ArrayList<XmlTree>();
      i = 0;


  }


  public void startElement(String namespaceURI, String localName,
                           String rawName, Attributes attrs)
  {
     // curItem.addChild(new XmlTree());
         if(curItem.children==null) curItem.children=new ArrayList<XmlTree>();

      if(curItem.children.isEmpty()){

          XmlTree saveItem = new XmlTree();

          i=0;
          curItem.children.add(i,new XmlTree());
          saveItem = curItem.children.get(i);
          saveItem.parent = curItem;
           curItem = saveItem;
     // curItem.children.get(i).parent = curItem;
    //  curItem = curItem.children.get(i);
      i++;
      curItem.name = rawName;
      curItem.attr = attrs;

      }
      else {
          XmlTree saveItem = new XmlTree();
      curItem.children.add(i,new XmlTree());
      saveItem = curItem.children.get(i);
          saveItem.parent = curItem;
          curItem = saveItem;
    //  curItem.children.get(i).parent = curItem;
    // curItem = curItem.children.get(i);
      i++;
      curItem.name = rawName;
      curItem.attr = attrs;   }

      for ( int i = 0; i < attrs.getLength(); i++) {
          curItem.a.add(attrs.getQName(i));
      }
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
