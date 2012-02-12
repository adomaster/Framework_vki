
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.Timer;

/*class Task extends TimerTask{
    public void run(wFrame obj) throws IOException{
        obj.save("autosave.out");
    }
}                  */

class wFrame extends JFrame implements Serializable{
    private int Bitems=0;
    private int Litems=0;
    private int Titems=0;
    private int sec=0;

   // private Task tsk = new Task();

    //private java.util.Timer timer = new Timer();


    private JPanel panel = new JPanel();
    private JButton[] button = new JButton[100];
    private JLabel[] label = new JLabel[100];
    private JTextField[] textfield = new JTextField[100];
    public wFrame(String title){
        super(title);

    }



    public void add(String Element_Type, String text, int w, int h) throws IOException{
            panel.setLayout(new FlowLayout());
        if(Element_Type.equals("button")){
            button[Bitems] = new JButton(text);
            button[Bitems].setSize(w,h);
            panel.add(button[Bitems]);
            Bitems++;
        }

        if(Element_Type.equals("label")){
            label[Litems] = new JLabel(text);
            label[Litems].setSize(w,h);
            panel.add(label[Litems]);
            Litems++;
        }

        if(Element_Type.equals("textfield")){
            textfield[Titems] = new JTextField(text);
            textfield[Titems].setSize(w,h);
            panel.add(textfield[Titems]);
            Titems++;
        }

        setContentPane(panel);

     /*   TimerTask tsk = new TimerTask() {
            @Override
            public void run() throws IOException{
                save("autosave.out");
            }
        }
        timer.schedule(actionListener,15000);   */

    }

    public void save_components(String way) throws IOException{
        FileOutputStream File_Out_Stream = new FileOutputStream(way);
        ObjectOutputStream Obj_Out_Stream = new ObjectOutputStream(File_Out_Stream);
        Obj_Out_Stream.writeObject(this);
        Obj_Out_Stream.flush();
        Obj_Out_Stream.close();
    }

}

public class test {
    public static void main(String args[]) throws IOException{
        int Width = 500, Height = 400;

        if(args.length == 2){
            try{
                Width = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){}

            try{
                Height = Integer.valueOf(args[1]);
            }catch(NumberFormatException e){}
        }else{}

        wFrame MainFrame = new wFrame("esdfghdsfgh");

        MainFrame.add("button","text_1",20,40);
        MainFrame.add("textfield","text_2",20,40);
        MainFrame.add("label","text_3",20,40);
        MainFrame.save_components("save1.out");

        /*
        try{
        FileInputStream File_Input_Stream = new FileInputStream("save1.out");
        ObjectInputStream Obj_Input_Stream = new ObjectInputStream(File_Input_Stream);
        MainFrame = (wFrame) Obj_Input_Stream.readObject();
        }
        catch (ClassNotFoundException exc){}
         */        MainFrame.setSize(Width, Height);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);

    }
}