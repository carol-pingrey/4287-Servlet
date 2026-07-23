package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Etatpanne {

    public int id;
    public String libelle;

    public Etatpanne() {
    }

    public static List<Etatpanne> findAll() throws Exception {
        List<Etatpanne> liste = new ArrayList<Etatpanne>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id, libelle FROM etatpanne";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etatpanne p = new Etatpanne();
                p.id = rs.getInt("id");
                p.libelle = rs.getString("libelle");
                liste.add(p);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }
}
