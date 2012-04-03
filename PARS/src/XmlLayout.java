import XmlParser;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList.*;



class XmlLayoutTest extends JFrame

{

    public XmlLayoutTest()

    {

        getContentPane().setLayout(new XmlLayout());

    }



    public static void main(String[] args)

    {

        XmlLayoutTest flt = new XmlLayoutTest();

        flt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flt.setVisible(true);

    }

}




public class XmlLayout implements LayoutManager

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

        for(int k=0; k<components.length; k++) {

            prefWidth += components[k].getWidth();

            prefHeight += components[k].getHeight();

        }

        return new Dimension(prefWidth, prefHeight);

    }

 



    public void layoutContainer(XmlTree parent)

    {

        XmlTree curItem = new XmlTree();
        curItem = parent;
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //___________________________________
        //заготовка для переходов по дереву
        if(curItem.name.equals("root")) curItem = curItem.NextCont();
        if(curItem.name.equals("frame")) curItem = curItem.NextCont();
        if(curItem.name.equals("panel")) { frame.setContentPane(curItem.item); curItem = curItem.NextChild();}
        if(curItem.name.equals("label")) { curItem.parent.item.add(curItem.item); curItem = curItem.NextChild();}
        if(curItem.name.equals("text-field")) { curItem.parent.item.add(curItem.item); curItem = curItem.NextChild();}
        if(curItem.name.equals("button")) { curItem.parent.item.add(curItem.item); curItem = curItem.NextChild();}
        //_______________________________________


        }

    }

}