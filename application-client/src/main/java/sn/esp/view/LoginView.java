package view;

import controller.UtilisateurController;
import sn.esp.soap.gen.GetUtilisateurResponse;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame 
{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginView() 
    {
        setTitle("Connexion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    public void initComponents()
    {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Se connecter");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(passwordField);
        panel.add(loginButton);
        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> authentifier());
    }

    private void authentifier() 
    {
        String email = emailField.getText().trim();
        String motDePasse = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || motDePasse.isEmpty()) 
        {
            statusLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        try 
        {
            UtilisateurController controller = new UtilisateurController();
            GetUtilisateurResponse utilisateur = controller.getUtilisateurParEmail(email);

            if (utilisateur != null && utilisateur.getNom() != null) 
            {
                if (motDePasse.equals("1234")) 
                {
                    statusLabel.setText("Connexion réussie en tant que " + utilisateur.getRole());

                    SwingUtilities.invokeLater(() -> 
                    {
                        dispose();
                        if (utilisateur.getRole().equalsIgnoreCase("ADMIN")) 
                        {
                            new AdminDashboardView(); 
                        } 
                        else 
                        {
                            new EditeurDashboardView(); 
                        }
                    });

                } 
                else 
                {
                    statusLabel.setText("Mot de passe incorrect.");
                }
            } 
            else 
            {
                statusLabel.setText("Utilisateur introuvable.");
            }

        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            statusLabel.setText("Erreur lors de l’authentification.");
        }
    }
}
