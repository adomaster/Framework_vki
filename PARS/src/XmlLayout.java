import XmlParser;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


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
    public XmlTree curItem;
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

        Component[] components = parent.getComponents();

        for (int k = 0; k < components.length; k++) {

            prefWidth += components[k].getWidth();

            prefHeight += components[k].getHeight();

        }

        return new Dimension(prefWidth, prefHeight);

    }


    XmlLayout(String xmlFile) {
        xp = new XmlParser();
        xp.parseXml(xmlFile);

    }

    public void useAttrib(XmlTree obj) {

        if (obj.attr != null) {
            int width = 60;
            int height = 20;
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
                }
                if (obj.attr.getQName(i).equals("valign")) {
                    String y = obj.attr.getValue(i);
                }
                /*   if(obj.name.equals("button"))
               {
                   obj.item.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {

                      setText("Pushed");
                   }
               });
               } */
                Dimension d = new Dimension(width, height);
                obj.item.setPreferredSize(d);


            }
        }
    }


    public void overTree(XmlTree parent) {
        for (int i = 0; i < parent.children.size(); i++) {
            if (parent.children.get(i).name.equals("frame")) {
                frame = new JFrame();
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                overTree(parent.children.get(i));
            }
            if (parent.children.get(i).name.equals("panel")) {
                frame.setContentPane(parent.children.get(i).item);
                overTree(parent.children.get(i));
            }
            if (parent.children.get(i).name.equals("label")) {
                parent.children.get(i).parent.item.add(parent.children.get(i).item);
                useAttrib(parent.children.get(i));
                overTree(parent.children.get(i));
            }
            if (parent.children.get(i).name.equals("text-field")) {
                parent.children.get(i).parent.item.add(parent.children.get(i).item);
                useAttrib(parent.children.get(i));
                overTree(parent.children.get(i));
            }
            if (parent.children.get(i).name.equals("button")) {
                parent.children.get(i).parent.item.add(parent.children.get(i).item);
                useAttrib(parent.children.get(i));
                overTree(parent.children.get(i));
            }
        }
    }

    public void layoutContainer(Container parent)

    {

        curItem = new XmlTree();
        curItem = xp.getTree();
        overTree(curItem);


    }

}

