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

class Attrib {
    public String idname;
    public int size;
    public Map op;

}

class ItemMap{
    public Map items;

}

class XmlTree {
    public JComponent item;
    public JButton btn;
    public Attributes attr;
    public int atlen;
    public String name;
    public ArrayList<XmlTree> children;
    public XmlTree parent;
    public Attrib at;


}

public class XmlParser extends DefaultHandler {
    public XmlTree curItem;
    public int i;
    ItemMap im = new ItemMap();

    public void parseXml(String uri) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            sp.parse(uri, this);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public ItemMap getItemMap() {
        return im;
    }

    public XmlTree getTree() {
        return curItem;
    }

    public void startDocument() {
        curItem = new XmlTree();
        curItem.name = "frame";
        curItem.children = new ArrayList<XmlTree>();
        i = 0;

        im.items = new HashMap();


    }


    public void startElement(String namespaceURI, String localName,
                             String rawName, Attributes attrs) {
        XmlTree saveItem;
        if (curItem.children == null) curItem.children = new ArrayList<XmlTree>();
        if (curItem.children.isEmpty()) {

            saveItem = new XmlTree();

            i = 0;
            saveItem.name = rawName;
            saveItem.attr = attrs;
            saveItem.atlen = attrs.getLength();

            saveItem.at = new Attrib();
            saveItem.at.op = new HashMap();
            saveItem.at.idname = rawName;
            saveItem.at.size = attrs.getLength();
            for (int g = 0; g < attrs.getLength(); g++) {
                saveItem.at.op.put(attrs.getQName(g).toString(), attrs.getValue(g).toString());
            }

            saveItem.parent = curItem;
            curItem.children.add(i, saveItem);
            curItem = saveItem;
            i++;

        } else {
            saveItem = new XmlTree();
            saveItem.name = rawName;
            saveItem.attr = attrs;
            saveItem.at = new Attrib();
            saveItem.at.op = new HashMap();
            saveItem.at.idname = rawName;
            saveItem.at.size = attrs.getLength();
            for (int g = 0; g < attrs.getLength(); g++) {
                saveItem.at.op.put(attrs.getQName(g), attrs.getValue(g));
            }
            saveItem.atlen = attrs.getLength();
            saveItem.parent = curItem;
            curItem.children.add(i, saveItem);
            curItem = saveItem;
            i++;
        }


    }

    public void characters(char ch[], int start, int length) {

        if (curItem.name.equals("panel")) {
            curItem.item = new JPanel();

        }
        if (curItem.name.equals("label")) {

            curItem.item = new JLabel(new String(ch, start, length));

        }
        if (curItem.name.equals("textfield")) {
            curItem.item = new JTextField(new String(ch, start, length));

        }
        if (curItem.name.equals("button")) {
            curItem.btn = (new JButton(new String(ch, start, length)));
        }
        if (curItem.at.op.containsKey("name")) {
                im.items.put(curItem.at.op.get("name"), curItem.item);
            }
    }

    public void ignorableWhitespace(char ch[], int start, int length) {
        characters(ch, start, length);
    }

    public void endElement(String namespaceURI, String localName,
                           String rawName) {
        curItem = curItem.parent;

    }


    public void endDocument() {
        System.out.print("Parsing finished");
    }

    public void warning(SAXParseException ex) {
        System.err.println("[Warning] " +
                getLocationString(ex) + ": " +
                ex.getMessage());
    }


    public void error(SAXParseException ex) {
        System.err.println("[Error] " +
                getLocationString(ex) + ": " +
                ex.getMessage());
    }


    public void fatalError(SAXParseException ex)
            throws SAXException {
        System.err.println("[Fatal Error] " +
                getLocationString(ex) + ": " +
                ex.getMessage());
        throw ex;
    }


    private String getLocationString(SAXParseException ex) {
        StringBuffer str = new StringBuffer();

        String systemId = ex.getSystemId();
        if (systemId != null) {
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
