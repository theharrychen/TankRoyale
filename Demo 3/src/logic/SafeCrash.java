package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Class that interacts with the console to ADD SOMETHING THAT TAKES IN THE CLASS NAME
 * facilitate safe crashing mechanisms.
 */

public class SafeCrash {

    /**
     * @param errorType:    Type of error encountered. Ex. FileNotFoundException.
     * @param errorMessage: Message for the user on the logistics of the error encountered. Ex. Unable to find file.
     * @param location:     location of the error in the format: Package, Class, Method.
     **/

    public static void shutDown(String errorType, String errorMessage, String location) {
        JPanel panel = new JPanel();
        //panel.setSize(new Dimension(0, 300));
        //panel.setBackground(new Color(102, 205, 170));
        panel.setLayout(null);

        //Prints that TankRoyale is unable to launch
        JLabel label0 = new JLabel("Unable to launch TankRoyale!");
        label0.setVerticalAlignment(SwingConstants.BOTTOM);
        label0.setHorizontalAlignment(SwingConstants.CENTER);
        label0.setFont(new Font("Palatino", Font.BOLD, 25));
        label0.setBounds(0, -32, 400, 96);
        panel.add(label0);

        //Prints error type to the GUI in red and bold font.
        JLabel label1 = new JLabel(errorType);
        label1.setVerticalAlignment(SwingConstants.BOTTOM);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Palatino", Font.BOLD, 25));
        label1.setForeground(Color.red);
        label1.setBounds(0, 32, 400, 96);
        panel.add(label1);

        //Prints error messgae in italics to the GUI.
        JLabel label2 = new JLabel(errorMessage);
        label2.setVerticalAlignment(SwingConstants.BOTTOM);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Palatino", Font.ITALIC, 25));
        label2.setBounds(0, 64, 400, 96);
        panel.add(label2);

        //Prints location of the Error to the GUI
        JLabel label3 = new JLabel("Location: " + location);
        label3.setVerticalAlignment(SwingConstants.BOTTOM);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Palatino", Font.PLAIN, 25));
        label3.setBounds(00, 96, 400, 96);
        panel.add(label3);

        //Prints further instructions for shutdown
        JLabel label4 = new JLabel("Click 'Ok' to Confirm Quit.");
        label4.setVerticalAlignment(SwingConstants.BOTTOM);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Palatino", Font.PLAIN, 25));
        label4.setBounds(00, 128, 400, 96);
        panel.add(label4);

        UIManager.put("OptionPane.minimumSize", new Dimension(500, 300));

        //Putting it all together and sending to the GUI window
        JOptionPane pane = new JOptionPane("Error ShutDown Confirmation", 0);
        int input = JOptionPane.showConfirmDialog(null, panel, "Error ShutDown Confirmation",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        // 0 = yes, 1 = no, 2 = cancel for the YES_NO_CANCEL OPTION
        if (input == 0) {
            System.exit(0);
        }

    }

}
