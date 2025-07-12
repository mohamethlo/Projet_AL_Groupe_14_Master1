package sn.esp.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame 
{

    private CardLayout cardLayout;
    private JPanel dynamicContentPanel;

    public DashboardView() 
    {
        setTitle("TABLEAU DE BORD ADMINISTRATEUR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        Color bleuPrincipal = new Color(0, 120, 215);       
        Color fondClair = new Color(230, 240, 255);         
        Color blanc = Color.WHITE;


        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bleuPrincipal);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        // PANEL INTERNE
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(blanc);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        // HEADER
        JLabel header = new JLabel("TABLEAU DE BORD ADMINISTRATEUR", SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(bleuPrincipal);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        innerPanel.add(header, BorderLayout.NORTH);

        // CORPS PRINCIPAL
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(bleuPrincipal);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        innerPanel.add(contentPanel, BorderLayout.CENTER);

        // MENU LATÉRAL
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(blanc);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel menuTitle = new JLabel("Menu", SwingConstants.CENTER);
        menuTitle.setOpaque(true);
        menuTitle.setBackground(bleuPrincipal);
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 18));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuTitle.setMaximumSize(new Dimension(200, 40));
        menuPanel.add(menuTitle);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        contentPanel.add(menuPanel, BorderLayout.WEST);

        // ZONE DE DONNÉES DYNAMIQUE
        cardLayout = new CardLayout();
        dynamicContentPanel = new JPanel(cardLayout);
        dynamicContentPanel.setBackground(fondClair);
        dynamicContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(dynamicContentPanel, BorderLayout.CENTER);

        // Création et ajout des panels dynamiques
        PanelListeUtilisateurs panelEtudiants = new PanelListeUtilisateurs(bleuPrincipal, fondClair);
        PanelListeAdministrateurs panelAdmin = new PanelListeAdministrateurs(bleuPrincipal, fondClair);
        PanelAjoutUtilisateur panelAjout = new PanelAjoutUtilisateur(bleuPrincipal, fondClair);

        dynamicContentPanel.add(panelEtudiants, "liste_utilisateurs");
        dynamicContentPanel.add(panelAdmin, "liste_admins");
        dynamicContentPanel.add(panelAjout, "ajout_utilisateur");

        // Boutons du menu avec action
        JButton[] buttons = 
        {
            createMenuButton("Liste Editeur", "liste_utilisateurs"),
            createMenuButton("Liste Administrateur", "liste_admins"),
            createMenuButton("Ajout Utilisateur", "ajout_utilisateur"),
        };

        for (JButton btn : buttons) 
        {
            menuPanel.add(btn);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Afficher la vue par défaut
        cardLayout.show(dynamicContentPanel, "liste_utilisateurs");
    }

    private JButton createMenuButton(String label, String panelName) 
    {
        JButton button = new JButton(label);
        button.setBackground(new Color(0, 90, 158));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));

        button.addActionListener(e -> cardLayout.show(dynamicContentPanel, panelName));

        return button;
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new DashboardView().setVisible(true));
    }
}
