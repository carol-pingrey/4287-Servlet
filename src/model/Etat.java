package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Etat {

    public int id;
    public String libelle;

    public Etat() {
    }

    public static List<Etat> findAll() throws Exception {
        List<Etat> liste = new ArrayList<Etat>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id, libelle FROM etat";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etat e = new Etat();
                e.id = rs.getInt("id");
                e.libelle = rs.getString("libelle");
                liste.add(e);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }
}
