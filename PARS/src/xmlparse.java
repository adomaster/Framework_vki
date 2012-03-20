import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container;
import java.util.Collection;

class xmlTree{

    public Container item;
    public String name;
    public Attributes attrib;
    public Collection<xmlTree> children;
    public xmlTree parent;

    public void addChild(xmlTree child){
		child.parent = this;
		getChildren().add(child);
	}
    public Collection<xmlTree> getChildren() {
		return children;
	}

}

public class xmlparse extends DefaultHandler {
    //String curItem.name = "";
    JFrame frame;
    JPanel panel;
    xmlTree root;
    xmlTree curItem;


    public void parseXml(String path) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            sp.parse(path, this);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void startDocument() {
        frame = new JFrame();
        curItem = new xmlTree();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        curItem.parent = root;
        curItem.item = frame;
       // curItem.name = "frame";
        panel = new JPanel();
        // panel.setLayout(new BorderLayout());
    }


    public void startElement(String namespacepath, String localName,
                             String rawName, Attributes attrs) {
       /* curItem.child = new xmlTree();
        curItem.child.parent = curItem;
        curItem = curItem.child;
        curItem.name = rawName;

        curItem.attrib = attrs;  */
        curItem.addChild(curItem);
        curItem.name=rawName;
        curItem.attrib = attrs;



    }

    public void characters(char ch[], int start, int length) {

        // panel.setLayout(new FlowLayout());
        if (curItem.name.equals("panel")) {
            curItem.item = panel;
            //curItem.parent.frame.setContentPane(curItem.panel);

        }
        if (curItem.name.equals("label")) {

            curItem.item = new JLabel(new String(ch, start, length));
            curItem.parent.item.add(curItem.item);
            int width = 60;
            int height = 20;
            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if (curItem.attrib.getQName(i).equals("bgcolor")) {
                       // curItem.item.setOpaque(true);
                        if (curItem.attrib.getValue(i).equals("black"))
                            curItem.item.setBackground(Color.black);
                        if (curItem.attrib.getValue(i).equals("white"))
                            curItem.item.setBackground(Color.white);
                        if (curItem.attrib.getValue(i).equals("red"))
                            curItem.item.setBackground(Color.red);
                        if (curItem.attrib.getValue(i).equals("green"))
                            curItem.item.setBackground(Color.green);
                        if (curItem.attrib.getValue(i).equals("blue"))
                            curItem.item.setBackground(Color.blue);
                        if (curItem.attrib.getValue(i).equals("yellow"))
                            curItem.item.setBackground(Color.yellow);
                        if (curItem.attrib.getValue(i).equals("gray"))
                            curItem.item.setBackground(Color.gray);

                    }
                    if (curItem.attrib.getQName(i).equals("width")) {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if (curItem.attrib.getQName(i).equals("height")) {
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    Dimension d = new Dimension(width, height);
                    curItem.item.setPreferredSize(d);


                }
            }
        }
        if (curItem.name.equals("text-field")) {
            curItem.item = new JTextField(new String(ch, start, length));


            int width = 100;
            int height = 30;


            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if (curItem.attrib.getQName(i).equals("bgcolor")) {
                        if (curItem.attrib.getValue(i).equals("black"))
                            curItem.item.setBackground(Color.black);
                        if (curItem.attrib.getValue(i).equals("white"))
                            curItem.item.setBackground(Color.white);
                        if (curItem.attrib.getValue(i).equals("red"))
                            curItem.item.setBackground(Color.red);
                        if (curItem.attrib.getValue(i).equals("green"))
                            curItem.item.setBackground(Color.green);
                        if (curItem.attrib.getValue(i).equals("blue"))
                            curItem.item.setBackground(Color.blue);
                        if (curItem.attrib.getValue(i).equals("yellow"))
                            curItem.item.setBackground(Color.yellow);
                        if (curItem.attrib.getValue(i).equals("gray"))
                            curItem.item.setBackground(Color.gray);
                    }
                    if (curItem.attrib.getQName(i).equals("width")) {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if (curItem.attrib.getQName(i).equals("height")) {
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    frame.setContentPane(panel);
                    Dimension d = new Dimension(width, height);
                    curItem.item.setPreferredSize(d);
                    if (curItem.attrib.getQName(i).equals("align")) {
                        //    switch ()
                    }
                }
            }
            curItem.parent.item.add(curItem.item);
        }
        if (curItem.name.equals("button")) {
            curItem.item = (new JButton(new String(ch, start, length)) {{
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setText("Pushed");
                    }
                });
            }});


            int width = 60;
            int height = 20;
            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if (curItem.attrib.getQName(i).equals("bgcolor")) {

                        if (curItem.attrib.getValue(i).equals("black"))
                            curItem.item.setBackground(Color.black);
                        if (curItem.attrib.getValue(i).equals("white"))
                            curItem.item.setBackground(Color.white);
                        if (curItem.attrib.getValue(i).equals("red"))
                            curItem.item.setBackground(Color.red);
                        if (curItem.attrib.getValue(i).equals("green"))
                            curItem.item.setBackground(Color.green);
                        if (curItem.attrib.getValue(i).equals("blue"))
                            curItem.item.setBackground(Color.blue);
                        if (curItem.attrib.getValue(i).equals("yellow"))
                            curItem.item.setBackground(Color.yellow);
                        if (curItem.attrib.getValue(i).equals("gray"))
                            curItem.item.setBackground(Color.gray);
                    }
                    if (curItem.attrib.getQName(i).equals("width")) {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if (curItem.attrib.getQName(i).equals("height")) {
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    Dimension d = new Dimension(width, height);
                    curItem.item.setPreferredSize(d);

                    curItem.parent.item.add(curItem.item);
                }
            }
        }
        frame.setContentPane(panel);

    }


    public void ignorableWhitespace(char ch[], int start, int length) {
        characters(ch, start, length);
    }


    public void endElement(String namespacepath, String localName,
                           String rawName) {
        curItem = curItem.parent;

    }

    public void endDocument() {
        frame.setContentPane(panel);
        System.out.println("Parsing finished");
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


    public static void main(String argv[]) {
        xmlparse s1 = new xmlparse();
        s1.parseXml("xmltest.xml");
    }
}

