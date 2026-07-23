package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdinateurEtat {

    public String libelle;      
    public String date;
    public String observation;
    public String libelleOrdi;  
    public String libellePanne; 

    public OrdinateurEtat() {
    }

    public static List<OrdinateurEtat> findByDate(String dateSql) throws Exception {
        List<OrdinateurEtat> liste = new ArrayList<OrdinateurEtat>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT ma.libelle AS marque, m.libelle AS modele, "
                    + "e.libelle AS etat, p.libelle AS panne, oe.date, oe.observation "
                    + "FROM ordinateur_etat oe "
                    + "JOIN ordinateur o ON oe.id_ordi = o.id "
                    + "JOIN modele m ON o.id_modele = m.id "
                    + "JOIN marque ma ON m.id_marque = ma.id "
                    + "JOIN etat e ON oe.id_etat = e.id "
                    + "LEFT JOIN etatpanne p ON oe.id_panne = p.id "
                    + "WHERE oe.date = ? "
                    + "ORDER BY oe.id";
            ps = con.prepareStatement(sql);
            ps.setString(1, dateSql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdinateurEtat oe = new OrdinateurEtat();
                oe.libelleOrdi = rs.getString("marque") + " " + rs.getString("modele");
                oe.libelle = rs.getString("etat");
                oe.libellePanne = rs.getString("panne");
                oe.date = rs.getString("date");
                oe.observation = rs.getString("observation");
                liste.add(oe);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }

    public static List<OrdinateurEtat> findByOrdi(int idOrdi) throws Exception {
        List<OrdinateurEtat> liste = new ArrayList<OrdinateurEtat>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT e.libelle, oe.date, oe.observation "
                    + "FROM ordinateur_etat oe, etat e "
                    + "WHERE oe.id_etat = e.id AND oe.id_ordi = ? "
                    + "ORDER BY oe.date DESC, oe.id DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOrdi);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdinateurEtat oe = new OrdinateurEtat();
                oe.libelle = rs.getString("libelle");
                oe.date = rs.getString("date");
                oe.observation = rs.getString("observation");
                liste.add(oe);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }
}
