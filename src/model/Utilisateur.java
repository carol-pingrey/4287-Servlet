package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

    public int id;
    public String login;
    public String motDePasse;
    public String role;

    public Utilisateur() {
    }

    public static Utilisateur checkLogin(String login, String motDePasse) throws Exception {
        Utilisateur u = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id, login, mot_de_passe, role FROM table_utilisateur "
                    + "WHERE login = ? AND mot_de_passe = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Utilisateur();
                u.id = rs.getInt("id");
                u.login = rs.getString("login");
                u.motDePasse = rs.getString("mot_de_passe");
                u.role = rs.getString("role");
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return u;
    }

    public static List<Utilisateur> findAll() throws Exception {
        List<Utilisateur> liste = new ArrayList<Utilisateur>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id, login, mot_de_passe, role FROM table_utilisateur";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.id = rs.getInt("id");
                u.login = rs.getString("login");
                u.motDePasse = rs.getString("mot_de_passe");
                u.role = rs.getString("role");
                liste.add(u);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }
}
