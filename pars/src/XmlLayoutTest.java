import XmlLayout.XmlLayout;

import javax.swing.*;

public class XmlLayoutTest extends JFrame {
    public XmlLayout test;

    public XmlLayoutTest() {
        test = new XmlLayout("xmltest.xml");
        setLayout(test);

    }

    public static void main(String[] args) throws IllegalAccessException,InstantiationException {
        XmlLayoutTest flt = new XmlLayoutTest();

    }
}
