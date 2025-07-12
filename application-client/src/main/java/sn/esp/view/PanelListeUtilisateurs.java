package sn.esp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        // Titre
        JLabel title = new JLabel("Liste Des Editeurs", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(vertPrincipal);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setPreferredSize(new Dimension(100, 40));
        add(title, BorderLayout.NORTH);

        // Table
        String[] columns = {"Prenom", "Nom", "Mail", "Action", "Gestion Jetons"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bouton deconnexion
        deconnexionBtn = new JButton("Se deconnecter");
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

    public JButton getdeconnexionBtn() 
    {
        return deconnexionBtn;
    }
}
