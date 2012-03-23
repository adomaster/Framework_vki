import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*public class XmlLayoutTest extends xmlparse
{

    private xmlparse s1;
    public xmlTree root;
    public XmlLayoutTest()

    {
        s1 = new xmlparse();
        s1.parseXml("xmltest.xml");
        root = s1.root;

    }



    public static void main(String[] args)

    {

        XmlLayoutTest flt = new XmlLayoutTest();


    }

}
 */
class XmlLayout implements LayoutManager

{



    public void addLayoutComponent(String name, Component comp)

    {}

    public void removeLayoutComponent(Component comp)

    {}

 

    public Dimension minimumLayoutSize(Container parent)

    {

        return computeLayoutSize(parent);

    }

 

    public Dimension preferredLayoutSize(Container parent)

    {

        return computeLayoutSize(parent);

    }

 

    private Dimension computeLayoutSize(Container parent)

    {

        int prefWidth = 0;

        int prefHeight = 0;

        Component[] components = parent.getComponents();

        for(int i=0; i<components.length; i++) {

            prefWidth += components[i].getWidth();

            prefHeight += components[i].getHeight();

        }

        return new Dimension(prefWidth, prefHeight);

    }

 

    public void layoutContainer(Container parent)

    {

        Component[] components = parent.getComponents();
        int row = 0;
        int col = 0;
        int width = parent.getWidth()/components.length;

        int height = parent.getHeight()/components.length;

        for(int i=0; i<components.length; i++) {

            Rectangle r = new Rectangle(col, row, width, height);

            components[i].setBounds(r);

            col += width;

            row += height;

        }

    }

}