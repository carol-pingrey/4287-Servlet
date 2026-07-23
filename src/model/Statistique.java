package model;

import util.DBConnection;
import java.sql.*;

public class Statistique {

    public int nbTotal;            
    public int nbFonctionnel;       
    public int nbNonFonctionnel;    

    public int nbAlimentation;
    public int nbDisqueDur;
    public int nbCarteMere;

    public Statistique() {
    }

    public static Statistique pourDate(String dateSql) throws Exception {
        Statistique s = new Statistique();
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            s.nbTotal = compter(con, "SELECT COUNT(*) FROM ordinateur", null);

            s.nbFonctionnel = compter(con,
                    "SELECT COUNT(*) FROM ordinateur_etat oe, etat e "
                  + "WHERE oe.id_etat = e.id AND e.libelle = 'Fonctionnel' AND oe.date = ?", dateSql);

            s.nbNonFonctionnel = compter(con,
                    "SELECT COUNT(*) FROM ordinateur_etat oe, etat e "
                  + "WHERE oe.id_etat = e.id AND e.libelle = 'Non fonctionnel' AND oe.date = ?", dateSql);

            s.nbAlimentation = compterPanne(con, dateSql, "Alimentation");
            s.nbDisqueDur = compterPanne(con, dateSql, "Disque Dur");
            s.nbCarteMere = compterPanne(con, dateSql, "Carte mere");
        } finally {
            if (con != null) con.close();
        }
        return s;
    }

    private static int compter(Connection con, String sql, String param) throws Exception {
        int n = 0;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if (param != null) {
                ps.setString(1, param);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } finally {
            if (ps != null) ps.close();
        }
        return n;
    }

    private static int compterPanne(Connection con, String dateSql, String libellePanne) throws Exception {
        int n = 0;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT COUNT(*) FROM ordinateur_etat oe, etatpanne p "
                    + "WHERE oe.id_panne = p.id AND p.libelle = ? AND oe.date = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, libellePanne);
            ps.setString(2, dateSql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } finally {
            if (ps != null) ps.close();
        }
        return n;
    }
}
