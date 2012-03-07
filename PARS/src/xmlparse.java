import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class xmlparse extends DefaultHandler {
    String thisElement = "";
    JFrame frame;
    JPanel panel;


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
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel = new JPanel();
    }


    public void startElement(String namespacepath, String localName,
                             String rawName, Attributes attrs) {
        thisElement = rawName;
    }

    public void characters(char ch[], int start, int length) {

        panel.setLayout(new FlowLayout());
        if (thisElement.equals("label")) {
            panel.add(new JLabel(new String(ch, start, length)));
        }
        if (thisElement.equals("text-field")) {
            panel.add(new JTextField(new String(ch, start, length)));
        }
        if (thisElement.equals("button")) {
            panel.add(new JButton(new String(ch, start, length)) {{
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setText("Pushed");
                    }
                });
            }});
        }
        frame.setContentPane(panel);
    }


    public void ignorableWhitespace(char ch[], int start, int length) {
        characters(ch, start, length);
    }


    public void endElement(String namespacepath, String localName,
                           String rawName) {
        thisElement = "";
    }

    public void endDocument() {

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

