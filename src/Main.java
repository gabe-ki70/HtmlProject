import com.sun.jdi.request.MonitorContendedEnteredRequest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;


public class Main implements ActionListener {

    private JFrame mainFrame;
    private JPanel controlPanel;
    private JPanel controlPanel2;
    private JPanel controlPanel3;
    private JPanel linkoutputPanel;
    private JPanel buttonpanel;
    private JLabel statusLabel;
    private JLabel separator1;
    private JLabel separator2;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private JTextArea linkinput;
    private JTextArea keywordinput;
    private JTextArea linkoutput;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public static void main(String[] args) {
        Main html = new Main();
        Main.showEventDemo();
    }

    public Main() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Gabe Learning SWING");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(1, 2));
        controlPanel = new JPanel();
        controlPanel2 = new JPanel();
        controlPanel3 = new JPanel();
        buttonpanel = new JPanel();
        controlPanel3.setLayout(new GridLayout(5, 1));
        linkoutputPanel = new JPanel();
        separator1 = new JLabel("");
        separator2 = new JLabel("");

        JButton startbutton = new JButton("Start");
        startbutton.setActionCommand("Start");
        startbutton.addActionListener(new Main.ButtonClickListener());

        JButton resetbutton = new JButton("Reset");
        resetbutton.setActionCommand("Reset");
        resetbutton.addActionListener(new Main.ButtonClickListener());

        buttonpanel.setLayout(new GridLayout(1, 2));
        controlPanel.setLayout(new GridLayout(1, 1));
        controlPanel2.setLayout(new GridLayout(1, 1));

        linkoutput = new JTextArea("Links with Keyword: ");
        linkinput = new JTextArea("Link: ");
        keywordinput = new JTextArea("Keyword: ");
        controlPanel.add(linkinput);
        controlPanel2.add(keywordinput);
        mainFrame.add(controlPanel3);
        JScrollPane scrollPane = new JScrollPane(linkoutput);
        mainFrame.add(scrollPane);
        controlPanel3.add(controlPanel);
        controlPanel3.add(separator1);
        controlPanel3.add(controlPanel2);
        controlPanel3.add(separator2);
        controlPanel3.add(buttonpanel);
        buttonpanel.add(startbutton);
        buttonpanel.add(resetbutton);

        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }

    private static void showEventDemo() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Start")) {
                try {
                    String userlink = linkinput.getText();
                    String userKeyword = keywordinput.getText();
                    int filteredstart = userlink.indexOf("https");
                    String filteredlink = userlink.substring(filteredstart);
                   // int filteredkeywordstart = userKeyword.indexOf(8);
                    String filteredkeyword = userKeyword.substring(9);
                    System.out.println(filteredkeyword);
                    URL url = new URL(filteredlink);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream())
                    );
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("href")) {
                            int start = line.indexOf("href=") + 6;
                            String link = line.substring(start);
                          //  System.out.println("og"+link);

                            int end = link.indexOf("\"");
                            int end2 = link.indexOf("'");
                            if (end2 == -1){
                                link = link.substring(0, end);
                                if (link.contains(filteredkeyword)) {
                                    System.out.println(start + "," + end);
                                    System.out.println(link);
                                    linkoutput.append(link+ "\n");
                                    //System.out.println(line.substring(start, start + end));
                                }
                            } else  if (end == -1) {
                               // end = link.indexOf("'");
                                link = link.substring(0, end2);
                                if (link.contains(filteredkeyword)) {
                                    System.out.println(start + "," + end2);
                                    System.out.println(link);
                                    linkoutput.append(link+ "\n");
                                    //System.out.println(line.substring(start, start + end));
                                }
                            }
                           else if (end < end2) {
                                //end = link.indexOf("--");
                                //link = line.substring(start, start + end);
                                link = link.substring(0, end);
                                if (link.contains(filteredkeyword)) {
                                    System.out.println(start + "," + end);
                                    System.out.print(link + "\n");
                                    linkoutput.append(link + "\n");
                                    //System.out.println(line.substring(start, start + end));
                                }
                            }
                            else {
                                link = link.substring(0, end2);
                                if (link.contains(filteredkeyword)) {
                                    System.out.println(start + "," + end2);
                                    System.out.println(link);
                                    linkoutput.append(link+ "\n");
                                    //System.out.println(line.substring(start, start + end));
                                }
                            }

                        }
                    }
                    reader.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                    System.out.println("Inputted link is not valid, please click reset and input another one");
                    linkoutput.append("\n");
                    linkoutput.append("Inputted link is not valid, please click reset and input another one");
                }
                //System.out.println(keywordinput.getText());
            }
            else if (command.equals("Reset")){
                linkinput.setText("Link: ");
                keywordinput.setText("Keyword: ");
                linkoutput.setText("Links with Keyword: ");
            }


            else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            }
            else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
    }

   // try {
   //     System.out.println();
   //     System.out.print("hello \n");
   //     Scanner linkholder = new Scanner(System.in);
   //     System.out.println("Enter Link: ");
   //     String userLink = linkholder.nextLine();
   //     Scanner keyword = new Scanner(System.in);
   //     System.out.println("Search for Keyword: ");
   //     String userKeyword = linkholder.nextLine();
   //     URL url = new URL(userLink);
   //     BufferedReader reader = new BufferedReader(
   //             new InputStreamReader(url.openStream())
   //     );
    //    String line;
    //    while ((line = reader.readLine()) != null) {
    //        if (line.contains("href")) {
    //            int start = line.indexOf("href=") + 6;
    //            String link = line.substring(start);
//
  //              int end = link.indexOf("\"");
    //            if (end == -1) {
     //               end = link.indexOf("'");
     //           }
     //           if (end == -1) {
     //               end = link.indexOf("--");
     //           }
     //           link = line.substring(start, start + end);
     //           if (link.contains(userKeyword)) {
     //               System.out.println(start + "," + end);
     //               //System.out.println(link);
     //               System.out.println(line.substring(start, start + end));
     //           }
      //      }
       // }
      //  reader.close();

   // } catch (Exception ex) {
     //   System.out.println(ex);
    //}
    

