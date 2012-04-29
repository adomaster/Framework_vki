import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class XmlLayoutTest extends JFrame {
    public XmlLayoutTest() {

        getContentPane().setLayout(new XmlLayout("xmltest.xml"));

        setBounds(100, 100, 500, 500);
    }

    public static void main(String[] args) {
        XmlLayoutTest flt = new XmlLayoutTest();
        //   flt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  flt.setVisible(true);
    }
}

abstract class Bg_color {
    public Color bgColor;

    public Color Set_bgColor(String str) {
        if (str.equals("black"))
            bgColor = Color.black;
        if (str.equals("white"))
            bgColor = Color.white;
        if (str.equals("red"))
            bgColor = Color.red;
        if (str.equals("green"))
            bgColor = Color.green;
        if (str.equals("blue"))
            bgColor = Color.blue;
        if (str.equals("yellow"))
            bgColor = Color.yellow;
        if (str.equals("gray"))
            bgColor = Color.gray;

        return bgColor;
    }

}

public class XmlLayout implements LayoutManager

{

    public XmlParser xp;
    public JFrame frame;
    public XmlTree curItem1;
    public int pWidth;
    public int pHeight;
    Bg_color bg;

    public void addLayoutComponent(String name, Component comp)

    {
    }

    public void removeLayoutComponent(Component comp)

    {
    }


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
        /*
       Component[] components = parent.getComponents();

       for (int k = 0; k < components.length; k++) {

           prefWidth += components[k].getWidth();

           prefHeight += components[k].getHeight();

       }
        */
        return new Dimension(prefWidth, prefHeight);

    }


    XmlLayout(String xmlFile) {
        xp = new XmlParser();
        xp.parseXml(xmlFile);
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        layoutContainer(frame);

    }

    public void useAttrib(XmlTree obj) {

        if (obj.attr != null) {
            int width = 60;
            int height = 20;
            int b11 = 0;
            int b12 = 0;
            int b21 = 0;
            int b22 = 0;
            String fname = "serif";
            int fstyle = Font.PLAIN;
            int fsize = 14;


            int len = obj.attr.getLength();
            for (int i = 0; i < len; i++) {
                if (obj.attr.getQName(i).equals("bgcolor")) {
                    if (obj.name.equals("label")) obj.item.setOpaque(true);
                    obj.item.setBackground(bg.Set_bgColor(obj.attr.getValue(i)));


                }
                if (obj.attr.getQName(i).equals("width")) {
                    width = Integer.parseInt(obj.attr.getValue(i));
                }
                if (obj.attr.getQName(i).equals("height")) {
                    height = Integer.parseInt(obj.attr.getValue(i));
                }
                if (obj.attr.getQName(i).equals("align")) {
                    String x = obj.attr.getValue(i);
                    if (x.equals("left")) {
                        b11 = 0;
                        b21 = width;

                    }
                    if (x.equals("center")) {
                        b11 = pWidth / 3;
                        b21 = (pWidth / 3) + width;

                    }
                    if (x.equals("right")) {
                        b11 = (pWidth / 3) * 2;
                        b21 = ((pWidth / 3) * 2) + width;

                    }
                }
                if (obj.attr.getQName(i).equals("valign")) {
                    String y = obj.attr.getValue(i);
                    if (y.equals("top")) {
                        b12 = 0;
                        b22 = height;

                    }
                    if (y.equals("center")) {
                        b12 = pHeight / 3;
                        b22 = (pHeight / 3) + height;

                    }
                    if (y.equals("bottom")) {
                        b12 = (pHeight / 3) * 2;
                        b22 = ((pHeight / 3) * 2) + height;

                    }
                }

                if (obj.attr.getQName(i).equals("font-size")) {
                    fsize = Integer.parseInt(obj.attr.getValue(i));
                }
                if (obj.attr.getQName(i).equals("font-style")) {
                    if (obj.attr.getValue(i).equals("plain")) fstyle = Font.PLAIN;
                    if (obj.attr.getValue(i).equals("bold")) fstyle = Font.BOLD;
                    if (obj.attr.getValue(i).equals("italic")) fstyle = Font.ITALIC;
                }

            }
            Dimension d = new Dimension(width, height);
            obj.item.setPreferredSize(d);
            Rectangle r = new Rectangle(b11, b12, b21, b22);
            obj.item.setBounds(r);
            obj.item.setFont(new Font(fname, fstyle, fsize));
            frame.getContentPane();
        }
    }


    public void overTree(XmlTree prnt) {
        if(!prnt.children.isEmpty()){
        for (int i = 0; i <= prnt.children.size(); i++) {
            if (prnt.children.get(i).name.equals("frame")) {
                frame = new JFrame();
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                overTree(prnt.children.get(i));
            }
            if (prnt.children.get(i).name.equals("panel")) {
                frame.setContentPane(prnt.children.get(i).item);
                pHeight = prnt.children.get(i).item.getHeight();
                pWidth = prnt.children.get(i).item.getWidth();
                overTree(prnt.children.get(i));
            }
            if (prnt.children.get(i).name.equals("label")) {
                prnt.children.get(i).parent.item.add(prnt.children.get(i).item);
                useAttrib(prnt.children.get(i));
                overTree(prnt.children.get(i));
            }
            if (prnt.children.get(i).name.equals("text-field")) {
                prnt.children.get(i).parent.item.add(prnt.children.get(i).item);
                useAttrib(prnt.children.get(i));
                overTree(prnt.children.get(i));
            }
            if (prnt.children.get(i).name.equals("button")) {
                prnt.children.get(i).parent.item.add(prnt.children.get(i).item);
                useAttrib(prnt.children.get(i));
                overTree(prnt.children.get(i));
            }
        }
        }

    }

    public void layoutContainer(Container parent)

    {

        curItem1 = new XmlTree();
        curItem1 = xp.getTree();
        overTree(curItem1);



    }

}

