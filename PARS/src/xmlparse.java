import javax.swing.border.Border;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import java.awt.BorderLayout;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class xmlTree {
    public JFrame frame;
    public JPanel panel;
    public JLabel label;
    public JTextField textField;
    public JButton button;
    public String name;
    public Attributes attrib;
    public xmlTree child;
    public xmlTree parent;

    /* public xmlTree (Object obj)
    {
        this.item = obj;
    }
    */
    /* public xmlTree addChild (String objName, xmlTree prnt){
prnt.child.name = objName;
prnt.child.parent = prnt;
return prnt.child;
}         */
}

public class xmlparse extends DefaultHandler {
    //String curItem.name = "";
    JFrame frame;
    JPanel panel;
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
        curItem.frame = frame;
        curItem.name = "frame";
        panel = new JPanel();
       // panel.setLayout(new BorderLayout());
    }


    public void startElement(String namespacepath, String localName,
                             String rawName, Attributes attrs) {
        curItem.child = new xmlTree();
        curItem.child.parent = curItem;
        curItem = curItem.child;
        curItem.name = rawName;

        curItem.attrib = attrs;


        //curItem.name = rawName;
    }

    public void characters(char ch[], int start, int length) {

        // panel.setLayout(new FlowLayout());
        if (curItem.name.equals("panel")) {
            curItem.panel = panel;
            //curItem.parent.frame.setContentPane(curItem.panel);

        }
        if (curItem.name.equals("label")) {

            curItem.label = new JLabel(new String(ch, start, length));
            curItem.parent.panel.add(curItem.label);
            int width = 60;
            int height = 20;
            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if(curItem.attrib.getQName(i).equals("bgcolor"))
                    {
                        curItem.label.setOpaque(true);
                       if(curItem.attrib.getValue(i).equals("black"))
                           curItem.label.setBackground(Color.black);
                        if(curItem.attrib.getValue(i).equals("white"))
                           curItem.label.setBackground(Color.white);
                        if(curItem.attrib.getValue(i).equals("red"))
                           curItem.label.setBackground(Color.red);
                        if(curItem.attrib.getValue(i).equals("green"))
                           curItem.label.setBackground(Color.green);
                        if(curItem.attrib.getValue(i).equals("blue"))
                           curItem.label.setBackground(Color.blue);
                        if(curItem.attrib.getValue(i).equals("yellow"))
                           curItem.label.setBackground(Color.yellow);
                        if(curItem.attrib.getValue(i).equals("gray"))
                           curItem.label.setBackground(Color.gray);
                       // curItem.label.setBackground(curItem.attrib.getValue(i));//!!! Color != String
                    }
                    if(curItem.attrib.getQName(i).equals("width"))
                    {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if(curItem.attrib.getQName(i).equals("height"))
                    {
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    Dimension d =new Dimension(width,height);
                    curItem.label.setPreferredSize(d);

                   /* if(curItem.attrib.getQName(i).equals("valign"))
                    {

                       switch (curItem.attrib.getValue(i).charAt(0))  {
                           case 't': curItem.label.setVerticalAlignment(JLabel.TOP);
                           case 'c': curItem.label.setVerticalAlignment(JLabel.CENTER);
                           case 'b': curItem.label.setVerticalAlignment(JLabel.BOTTOM);
                       }

                    } */
                }
            }
        }
        if (curItem.name.equals("text-field")) {
            curItem.textField = new JTextField(new String(ch, start, length));


            int width = 100;
            int height = 30;


            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if(curItem.attrib.getQName(i).equals("bgcolor"))
                    {
                            if(curItem.attrib.getValue(i).equals("black"))
                           curItem.textField.setBackground(Color.black);
                        if(curItem.attrib.getValue(i).equals("white"))
                           curItem.textField.setBackground(Color.white);
                        if(curItem.attrib.getValue(i).equals("red"))
                           curItem.textField.setBackground(Color.red);
                        if(curItem.attrib.getValue(i).equals("green"))
                           curItem.textField.setBackground(Color.green);
                        if(curItem.attrib.getValue(i).equals("blue"))
                           curItem.textField.setBackground(Color.blue);
                        if(curItem.attrib.getValue(i).equals("yellow"))
                           curItem.textField.setBackground(Color.yellow);
                        if(curItem.attrib.getValue(i).equals("gray"))
                           curItem.textField.setBackground(Color.gray);
                       // curItem.textField.setBackground(curItem.attrib.getValue(i));//!!! Color != String
                    }
                    if(curItem.attrib.getQName(i).equals("width"))
                    {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if(curItem.attrib.getQName(i).equals("height")){
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    frame.setContentPane(panel);
                    Dimension d =new Dimension(width,height);
                    curItem.textField.setPreferredSize(d);
                    if(curItem.attrib.getQName(i).equals("align"))
                    {
                      //    switch ()
                    }
                }
            }
            curItem.parent.panel.add(curItem.textField);
        }
        if (curItem.name.equals("button")) {
            curItem.button = (new JButton(new String(ch, start, length)) {{
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setText("Pushed");
                    }
                });
            }});
          //  System.out.print("bug");
          //  curItem.button.setBackground(Color.red);
          //  System.out.print("bug");
           // curItem.parent.panel.add(curItem.button);

            int width = 60;
            int height = 20;
            if (curItem.attrib != null) {
                int len = curItem.attrib.getLength();
                for (int i = 0; i < len; i++) {
                    if(curItem.attrib.getQName(i).equals("bgcolor"))
                    {

                       if(curItem.attrib.getValue(i).equals("black"))
                           curItem.button.setBackground(Color.black);
                        if(curItem.attrib.getValue(i).equals("white"))
                           curItem.button.setBackground(Color.white);
                        if(curItem.attrib.getValue(i).equals("red"))
                           curItem.button.setBackground(Color.red);
                        if(curItem.attrib.getValue(i).equals("green"))
                           curItem.button.setBackground(Color.green);
                        if(curItem.attrib.getValue(i).equals("blue"))
                           curItem.button.setBackground(Color.blue);
                        if(curItem.attrib.getValue(i).equals("yellow"))
                           curItem.button.setBackground(Color.yellow);
                        if(curItem.attrib.getValue(i).equals("gray"))
                           curItem.button.setBackground(Color.gray);
                    }
                    if(curItem.attrib.getQName(i).equals("width"))
                    {
                        width = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    if(curItem.attrib.getQName(i).equals("height"))
                    {
                        height = Integer.parseInt(curItem.attrib.getValue(i));
                    }
                    Dimension d =new Dimension(width,height);
                    curItem.button.setPreferredSize(d);
                    /*if(curItem.attrib.getQName(i).equals("align"))
                    {

                       switch (curItem.attrib.getValue(i).charAt(0))  {
                           case 't': curItem.parent.panel.add(curItem.button, BorderLayout.NORTH);
                           case 'c': curItem.parent.panel.add(curItem.button, BorderLayout.CENTER);
                           case 'b': curItem.parent.panel.add(curItem.button, BorderLayout.SOUTH);
                           case 'l': curItem.parent.panel.add(curItem.button, BorderLayout.WEST);
                           case 'r': curItem.parent.panel.add(curItem.button, BorderLayout.EAST);
                       }

                    } else{
                    curItem.parent.panel.add(curItem.button);       }
                     */
                    curItem.parent.panel.add(curItem.button);
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

