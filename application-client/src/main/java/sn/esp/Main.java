package sn.esp;

import javax.swing.SwingUtilities;

import sn.esp.view.LoginView;

public class Main 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }

}
