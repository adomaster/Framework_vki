import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


class XmlLayoutTest extends JFrame {
    public XmlLayoutTest() {
        setLayout(new XmlLayout("xmltest.xml"));

    }

    public static void main(String[] args) {
        XmlLayoutTest flt = new XmlLayoutTest();

    }
}

class Bg_color {
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
    public int len;
    public int j;
    public int pWidth;
    public int pHeight;
    public ItemMap im;
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

        return new Dimension(prefWidth, prefHeight);

    }


    XmlLayout(String xmlFile) {
        xp = new XmlParser();
        xp.parseXml(xmlFile);
        bg = new Bg_color();

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
            if (obj.at.op.containsKey("action")) {

                obj.btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JTextField pole;
                        pole = (JTextField) im.items.get("textfield1");
                        System.out.println(pole.getText());
                        pole.setText("");


                    }
                });
            }

            if (obj.at.op.containsKey("bgcolor")) {
                if (obj.name.equals("label")) {
                    obj.item.setOpaque(true);
                }
                if (obj.name.equals("button")) {
                    obj.btn.setBackground(bg.Set_bgColor(obj.at.op.get("bgcolor").toString()));
                } else
                    obj.item.setBackground(bg.Set_bgColor(obj.at.op.get("bgcolor").toString()));


            }
            if (obj.at.op.containsKey("width")) {
                width = Integer.parseInt(obj.at.op.get("width").toString());
            }
            if (obj.at.op.containsKey("height")) {
                height = Integer.parseInt(obj.at.op.get("height").toString());
            }
            if (obj.at.op.containsKey("align")) {
                Float x = Float.parseFloat(obj.at.op.get("align").toString());
                if (x.equals(0f)) {
                    b11 = 0;
                    b21 = width;

                }
                if (x.equals(0.5f)) {
                    b11 = pWidth / 3;
                    b21 = (pWidth / 3) + width;
                }
                if (x.equals(1f)) {
                    b11 = (pWidth / 3) * 2;
                    b21 = ((pWidth / 3) * 2) + width;
                }
            }
            if (obj.at.op.containsKey("valign")) {
                Float y = Float.parseFloat(obj.at.op.get("valign").toString());
                if (y.equals(0f)) {
                    b12 = 0;
                    b22 = height;
                }
                if (y.equals(0.5f)) {
                    b12 = pHeight / 3;
                    b22 = (pHeight / 3) + height;
                }
                if (y.equals(1f)) {
                    b12 = (pHeight / 3) * 2;
                    b22 = ((pHeight / 3) * 2) + height;
                }
            }

            if (obj.at.op.containsKey("font-size")) {
                fsize = Integer.parseInt(obj.at.op.get("font-size").toString());
            }
            if (obj.at.op.containsKey("font-style")) {
                if (obj.at.op.get("font-style").equals("plain")) fstyle = Font.PLAIN;
                if (obj.at.op.get("font-style").equals("bold")) fstyle = Font.BOLD;
                if (obj.at.op.get("font-style").equals("italic")) fstyle = Font.ITALIC;

            }
            j++;
            len += obj.atlen;
            Dimension d = new Dimension(width, height);
            if (obj.name.equals("button")) {
                obj.btn.setPreferredSize(d);

                Rectangle r = new Rectangle(b11, b12, width, height);
                obj.btn.setBounds(r);

                obj.btn.setFont(new Font(fname, fstyle, fsize));
            } else {
                obj.item.setPreferredSize(d);
                Rectangle r = new Rectangle(b11, b12, width, height);
                obj.item.setBounds(r);
                obj.item.setFont(new Font(fname, fstyle, fsize));
            }
            frame.getContentPane();
        }
    }


    public void overTree(XmlTree prnt, JFrame frm) {
        len = 0;
        j = 0;
        if ((prnt.children != null) && (!prnt.children.isEmpty())) {
            for (int i = 0; i < prnt.children.size(); i++) {
                if (prnt.children.get(i).name.equals("frame")) {
                    frame = frm;
                    frame.setSize(500, 500);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    overTree(prnt.children.get(i), frame);
                }
                if (prnt.children.get(i).name.equals("panel")) {
                    frame.setContentPane(prnt.children.get(i).item);
                    pHeight = prnt.children.get(i).item.getHeight();
                    pWidth = prnt.children.get(i).item.getWidth();
                    prnt.children.get(i).item.setLayout(null);
                    overTree(prnt.children.get(i), frame);
                }
                if (prnt.children.get(i).name.equals("label")) {
                    prnt.children.get(i).parent.item.add(prnt.children.get(i).item);
                    useAttrib(prnt.children.get(i));
                    overTree(prnt.children.get(i), frame);
                }
                if (prnt.children.get(i).name.equals("textfield")) {
                    prnt.children.get(i).parent.item.add(prnt.children.get(i).item);
                    useAttrib(prnt.children.get(i));
                    overTree(prnt.children.get(i), frame);
                }
                if (prnt.children.get(i).name.equals("button")) {
                    prnt.children.get(i).parent.item.add(prnt.children.get(i).btn);
                    useAttrib(prnt.children.get(i));
                    overTree(prnt.children.get(i), frame);
                }
            }
        }
    }


    public void layoutContainer(Container parent)

    {
        curItem1 = new XmlTree();
        curItem1 = xp.getTree();
        im = new ItemMap();
        im = xp.getItemMap();
        overTree(curItem1, frame);
    }

}

