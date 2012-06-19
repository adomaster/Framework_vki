import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class XmlLayoutTest extends JFrame {
    XmlLayout test;

    public XmlLayoutTest() {
        test = new XmlLayout("xmltest.xml");
        setLayout(test);

    }

    public ActionListener myAction1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JTextField login, pass, cpass;
            login = (JTextField) test.im.items.get("textfield1");
            pass = (JTextField) test.im.items.get("textfield2");
            cpass = (JTextField) test.im.items.get("textfield3");
            System.out.println();
            System.out.println(login.getText());
            login.setText("");
            System.out.println(pass.getText());
            pass.setText("");
            System.out.println(cpass.getText());
            cpass.setText("");
        }
    };
    public ActionListener myAction2 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed");
        }
    };


    public static void main(String[] args) {
        XmlLayoutTest flt = new XmlLayoutTest();
        flt.test.actions.put("newAction1", flt.myAction1);
        flt.test.actions.put("newAction2", flt.myAction2);


    }
}
