package XmlParser;

import org.xml.sax.Attributes;

import javax.swing.*;
import java.util.ArrayList;

public class XmlTree {
    public JComponent item;
    public JButton btn;
    public Attributes attr;
    public int atlen;
    public String name;
    public ArrayList<XmlTree> children;
    public XmlTree parent;
    public Attrib at;
    public boolean bact;

    public XmlTree(){}


}
