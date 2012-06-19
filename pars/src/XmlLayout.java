import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

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
    public XmlTree curItem2;
    public int len;
    public int j;
    public int pWidth;
    public int pHeight;
    public ItemMap im;
    public Map actions;
    Bg_color bg;
    public boolean bpanel_bg;
    public boolean bpanel_fsize;
    public boolean bpanel_fstyle;
    public String panel_bg;
    public String panel_fsize;
    public String panel_fstyle;


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
        pHeight = frame.getHeight();
        pWidth = frame.getWidth();
        over(curItem2);
        return new Dimension(prefWidth, prefHeight);
    }

    public void over(XmlTree parent) {
        if ((parent.children != null) && (!parent.children.isEmpty())) {
            for (int i = 0; i < parent.children.size(); i++) {
                useAttrib(parent.children.get(i));
                over(parent.children.get(i));
            }
        }
    }


    XmlLayout(String xmlFile) {
        xp = new XmlParser();
        xp.parseXml(xmlFile);
        bg = new Bg_color();

        bpanel_bg = false;
        bpanel_fsize = false;
        bpanel_fstyle = false;

        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                Dimension dd = new Dimension();
                dd = computeLayoutSize(frame);

            }

            public void componentMoved(ComponentEvent e) {

            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });
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
            if (!obj.bact) {
                if (obj.at.op.containsKey("action")) {
                    obj.btn.removeActionListener((ActionListener) actions.get(obj.at.op.get("action").toString()));
                    obj.btn.addActionListener((ActionListener) actions.get(obj.at.op.get("action").toString()));

                }
            }

            if (obj.at.op.containsKey("bgcolor")) {
                if (obj.name.equals("panel")) {
                    bpanel_bg = true;
                    panel_bg = obj.at.op.get("bgcolor").toString();
                }

                if (obj.name.equals("label")) {
                    obj.item.setOpaque(true);
                }
                if (obj.name.equals("button")) {
                    obj.btn.setBackground(bg.Set_bgColor(obj.at.op.get("bgcolor").toString()));
                } else
                    obj.item.setBackground(bg.Set_bgColor(obj.at.op.get("bgcolor").toString()));
            } else {
                if (bpanel_bg) {


                    if (obj.name.equals("label")) {
                        obj.item.setOpaque(true);
                    }
                    if (obj.name.equals("button")) {
                        obj.btn.setBackground(bg.Set_bgColor(panel_bg));
                    } else
                        obj.item.setBackground(bg.Set_bgColor(panel_bg));
                }


            }

            if (obj.at.op.containsKey("width"))

            {
                width = Integer.parseInt(obj.at.op.get("width").toString());
            }

            if (obj.at.op.containsKey("height"))

            {
                height = Integer.parseInt(obj.at.op.get("height").toString());
            }

            if (obj.at.op.containsKey("align"))

            {
                Float x = Float.parseFloat(obj.at.op.get("align").toString());
                b11 = (int) (pWidth * x);
                b21 = (int) (pWidth * x) + width;
            }

            if (obj.at.op.containsKey("valign"))

            {
                Float y = Float.parseFloat(obj.at.op.get("valign").toString());
                b12 = (int) (pHeight * y);
                b22 = (int) (pHeight * y) + height;
            }

            if (obj.at.op.containsKey("font-size"))

            {
                if (obj.name.equals("panel")) {
                    bpanel_fsize = true;
                    panel_fsize = obj.at.op.get("font-size").toString();
                }

                fsize = Integer.parseInt(obj.at.op.get("font-size").toString());

            } else {
                if (bpanel_fsize) {
                    fsize = Integer.parseInt(panel_fsize);
                }
            }

            if (obj.at.op.containsKey("font-style"))

            {
                if (obj.name.equals("panel")) {
                    bpanel_fstyle = true;
                    panel_fstyle = obj.at.op.get("font-style").toString();
                }
                if (obj.at.op.get("font-style").equals("plain")) fstyle = Font.PLAIN;
                if (obj.at.op.get("font-style").equals("bold")) fstyle = Font.BOLD;
                if (obj.at.op.get("font-style").equals("italic")) fstyle = Font.ITALIC;

            } else {
                if (bpanel_fstyle && !obj.at.op.containsKey("font-style")) {
                    if (panel_fstyle.equals("plain")) fstyle = Font.PLAIN;
                    if (panel_fstyle.equals("bold")) fstyle = Font.BOLD;
                    if (panel_fstyle.equals("italic")) fstyle = Font.ITALIC;
                }
            }

            j++;
            len += obj.atlen;
            Dimension d = new Dimension(width, height);
            if (obj.name.equals("button"))

            {
                obj.btn.setPreferredSize(d);

                Rectangle r = new Rectangle(b11, b12, width, height);
                obj.btn.setBounds(r);

                obj.btn.setFont(new Font(fname, fstyle, fsize));
            } else

            {
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
                    useAttrib(prnt.children.get(i));
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
        curItem2 = new XmlTree();
        curItem2 = xp.getTree();
        im = new ItemMap();
        actions = new HashMap();
        im = xp.getItemMap();
        overTree(curItem1, frame);
    }

}

