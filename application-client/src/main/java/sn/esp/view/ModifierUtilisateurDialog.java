package view;

import sn.esp.soap.gen.UpdateUtilisateurRequest;
import sn.esp.soap.gen.UpdateUtilisateurResponse;
import controller.UtilisateurController;

import javax.swing.*;
import java.awt.*;

public class ModifierUtilisateurDialog extends JDialog 
{

    private JTextField nomField, prenomField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private JButton saveBtn, cancelBtn;
    private UtilisateurController controller;
    private Runnable onSuccess;

    public ModifierUtilisateurDialog(Frame parent, String nom, String prenom, String email, String role, UtilisateurController controller, Runnable onSuccess) 
    {
        super(parent, "Modifier l'utilisateur", true);
        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
        this.controller = controller;
        this.onSuccess = onSuccess;

        initComponents();
    }

    public void initComponents()
    {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nom:"));
        nomField = new JTextField(nom);
        panel.add(nomField);

        panel.add(new JLabel("Prénom:"));
        prenomField = new JTextField(prenom);
        panel.add(prenomField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(email);
        emailField.setEditable(false); // email = identifiant
        panel.add(emailField);

        panel.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Rôle:"));
        roleCombo = new JComboBox<>(new String[]{"ADMIN", "EDITEUR"});
        roleCombo.setSelectedItem(role.toUpperCase());
        panel.add(roleCombo);

        saveBtn = new JButton("Enregistrer");
        cancelBtn = new JButton("Annuler");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        // Action
        saveBtn.addActionListener(e -> updateUtilisateur());
        cancelBtn.addActionListener(e -> dispose());
    }

    private void updateUtilisateur() 
    {
        UpdateUtilisateurRequest request = new UpdateUtilisateurRequest();
        request.setNom(nomField.getText());
        request.setPrenom(prenomField.getText());
        request.setEmail(emailField.getText());
        request.setMotDePasse(new String(passwordField.getPassword()));
        request.setRole(roleCombo.getSelectedItem().toString());

        UpdateUtilisateurResponse response = controller.updateUtilisateur(request);

        if (response.getMessage().toLowerCase().contains("succes")) 
        {
            JOptionPane.showMessageDialog(this, "Utilisateur mis a jour.");
            dispose();
            if (onSuccess != null) onSuccess.run();
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Erreur : " + response.getMessage());
        }
    }
}
