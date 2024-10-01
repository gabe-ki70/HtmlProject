import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.CENTER;

public class Main implements ActionListener {

    private JFrame mainFrame;
    private JPanel controlPanel;
    private JLabel statusLabel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public static void main(String[] args) {
        Main html = new Main();
        Main.showEventDemo();
    }

    public Main() {
        prepareGUI();
    }

        private void prepareGUI () {
            mainFrame = new JFrame("Gabe Learning SWING");
            mainFrame.setSize(WIDTH, HEIGHT);
            mainFrame.setLayout(new BorderLayout());
            Button startbutton = new Button("Start");
            mainFrame.add(startbutton);
            try {
                System.out.println();
                System.out.print("hello \n");
                Scanner linkholder = new Scanner(System.in);
                System.out.println("Enter Link: ");
                String userLink = linkholder.nextLine();
                Scanner keyword = new Scanner(System.in);
                System.out.println("Search for Keyword: ");
                String userKeyword = linkholder.nextLine();
                URL url = new URL(userLink);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("href")) {
                        int start = line.indexOf("href=") + 6;
                        String link = line.substring(start);

                        int end = link.indexOf("\"");
                        if (end == -1) {
                            end = link.indexOf("'");
                        }
                        if (end == -1) {
                            end = link.indexOf("--");
                        }
                        link = line.substring(start, start + end);
                        if (link.contains(userKeyword)) {
                            System.out.println(start + "," + end);
                            //System.out.println(link);
                            System.out.println(line.substring(start, start + end));
                        }
                    }
                }
                reader.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }
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
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                statusLabel.setText("Ok Button clicked.");
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
    }

