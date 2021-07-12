import mypackage.WeatherWSLocator;
import mypackage.WeatherWSSoap_PortType;

import javax.swing.*;
import javax.xml.rpc.ServiceException;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Scanner;

public class WeatherQuery extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton Button1;
    private JTextField Field1;
    private JTextArea Area1;
    private JTextPane Pane1;

    public WeatherQuery() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        Button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onQuery();
                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onQuery() throws ServiceException, RemoteException {
        WeatherWSLocator wsl = new WeatherWSLocator();
        WeatherWSSoap_PortType wp = wsl.getWeatherWSSoap();

        Area1.setLineWrap(true);        //激活自动换行功能
        Area1.setWrapStyleWord(true);            // 激活断行不断字功能

        String city = new String();
        city = Field1.getText();
        String[] place = wp.getWeather(city,null);
        for(String str:place){

            Area1.append(str);
        }

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        WeatherQuery dialog = new WeatherQuery();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
