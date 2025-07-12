package sn.esp.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame 
{

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() 
    {
        setTitle("Connexion Utilisateur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        Color bleuPrincipal = new Color(0, 120, 215);
        Color fondClair = new Color(230, 240, 255);
        Color blanc = Color.WHITE;

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bleuPrincipal);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        // PANEL CENTRAL
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(blanc);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        // ENTÊTE
        JLabel title = new JLabel("Connexion à la plateforme", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(bleuPrincipal);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setPreferredSize(new Dimension(100, 50));
        innerPanel.add(title, BorderLayout.NORTH);

        // FORMULAIRE DE CONNEXION
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setBackground(fondClair);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel emailLabel = new JLabel("Email :");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordField = new JPasswordField();

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        innerPanel.add(formPanel, BorderLayout.CENTER);

        // BOUTON CONNEXION
        loginButton = new JButton("Se connecter");
        loginButton.setBackground(bleuPrincipal);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(150, 35));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(fondClair);
        buttonPanel.add(loginButton);
        innerPanel.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose(); 
        });
    }

    // GETTERS
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
