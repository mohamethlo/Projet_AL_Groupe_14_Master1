package view;

import sn.esp.soap.gen.GetAllUtilisateursResponse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelListeUtilisateurs extends JPanel 
{

    private JTable table;
    private DefaultTableModel model;
    private JButton deconnexionBtn;

    public PanelListeUtilisateurs(Color vertPrincipal, Color fondClair) 
    {
        setLayout(new BorderLayout());
        setBackground(fondClair);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
    }

    // Methode qui permet d'initialise les composants
    public void initComponents()
    {
        // Titre
        JLabel title = new JLabel("Liste Des Éditeurs", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(vertPrincipal);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setPreferredSize(new Dimension(100, 40));
        add(title, BorderLayout.NORTH);

        // Table
        String[] columns = {"Prenom", "Nom", "Email", "Action", "Gestion Jetons"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Ajout les renderers et editors pour les boutons "Modifier" et "Supprimer"
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), "Modifier", e -> 
        {
            int row = Integer.parseInt(e.getActionCommand());

            String prenom = (String) model.getValueAt(row, 0);
            String nom = (String) model.getValueAt(row, 1);
            String email = (String) model.getValueAt(row, 2);
            String role = "EDITEUR";

            ModifierUtilisateurDialog dialog = new ModifierUtilisateurDialog(
                SwingUtilities.getWindowAncestor(this),
                nom, prenom, email, role,
                new UtilisateurController(),
                () -> 
                {
                    List<GetAllUtilisateursResponse.Utilisateurs> users = new UtilisateurController().getListeUtilisateurs();
                    afficherListeEditeurs(users);
                }
            );
            dialog.setVisible(true);
        }));

        table.getColumn("Gestion Jetons").setCellRenderer(new ButtonRenderer("Jetons"));
        table.getColumn("Gestion Jetons").setCellEditor(new ButtonEditor(new JCheckBox(), "Supprimer", e -> 
        {
            int row = Integer.parseInt(e.getActionCommand());
            String email = (String) model.getValueAt(row, 2);
            int confirm = JOptionPane.showConfirmDialog(this, "Supprimer l'utilisateur : " + email + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) 
            {
                UtilisateurController controller = new UtilisateurController();
                boolean success = controller.supprimerUtilisateur(email);
                if (success) 
                {
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Utilisateur supprimé.");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.");
                }
            }
        }));


        // Bouton deconnexion
        deconnexionBtn = new JButton("Se déconnecter");
        deconnexionBtn.setBackground(vertPrincipal);
        deconnexionBtn.setForeground(Color.WHITE);
        deconnexionBtn.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(fondClair);
        btnPanel.add(deconnexionBtn);

        add(btnPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() 
    {
        return table;
    }

    public DefaultTableModel getTableModel() 
    {
        return model;
    }

    public JButton getDeconnexionBtn() 
    {
        return deconnexionBtn;
    }


    // Cette methode met a jour le tableau avec la liste des EDITEURS
    public void afficherListeEditeurs(List<GetAllUtilisateursResponse.Utilisateurs> utilisateurs) 
    {
        model.setRowCount(0); 

        for (GetAllUtilisateursResponse.Utilisateurs user : utilisateurs) 
        {
            if ("EDITEUR".equalsIgnoreCase(user.getRole())) 
            {
                model.addRow(new Object[]
                {
                        user.getPrenom(),
                        user.getNom(),
                        user.getEmail(),
                        "Modifier",
                        "Jetons"
                });
            }
        }
    }
}
