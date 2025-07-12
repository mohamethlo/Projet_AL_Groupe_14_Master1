package sn.esp.view;

import javax.swing.*;
import java.awt.*;

public class PanelAjoutUtilisateur extends JPanel 
{

    private JTextField prenomField;
    private JTextField nomField;
    private JTextField emailField;
    private JTextField telephoneField;
    private JPasswordField motDePasseField;
    private JComboBox<String> roleCombo;
    private JButton ajouterBtn;

    public PanelAjoutUtilisateur(Color vertPrincipal, Color fondClair) 
    {
        setLayout(new BorderLayout(10, 10));
        setBackground(fondClair);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel title = new JLabel("Ajout d’un Utilisateur", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(vertPrincipal);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setPreferredSize(new Dimension(100, 40));
        add(title, BorderLayout.NORTH);

        // Formulaire
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(fondClair);

        // Champs
        formPanel.add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        formPanel.add(prenomField);

        formPanel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        formPanel.add(nomField);

        formPanel.add(new JLabel("Email :"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Téléphone :"));
        telephoneField = new JTextField();
        formPanel.add(telephoneField);

        formPanel.add(new JLabel("Mot de passe :"));
        motDePasseField = new JPasswordField();
        formPanel.add(motDePasseField);

        formPanel.add(new JLabel("Rôle :"));
        roleCombo = new JComboBox<>(new String[]{"Administrateur", "Éditeur"});
        formPanel.add(roleCombo);

        add(formPanel, BorderLayout.CENTER);

        // Bouton Ajouter
        ajouterBtn = new JButton("Ajouter Utilisateur");
        ajouterBtn.setBackground(vertPrincipal);
        ajouterBtn.setForeground(Color.WHITE);
        ajouterBtn.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(fondClair);
        btnPanel.add(ajouterBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    
}
