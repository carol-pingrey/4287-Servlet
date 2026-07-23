package model;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ordinateur {

    public int id;
    public int idModele;
    public String libelleModele;
    public String libelleMarque;
    public String processeur;
    public int ram;
    public int disqueDur;
    public String etatActuel;

    public Ordinateur() {
    }

    public Ordinateur(int idModele, String processeur, int ram, int disqueDur) {
        this.idModele = idModele;
        this.processeur = processeur;
        this.ram = ram;
        this.disqueDur = disqueDur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void save(Ordinateur o) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "INSERT INTO ordinateur (id_modele, processeur, ram, disque_dur) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, o.idModele);
            ps.setString(2, o.processeur);
            ps.setInt(3, o.ram);
            ps.setInt(4, o.disqueDur);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

    public static void update(Ordinateur o) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "UPDATE ordinateur SET id_modele = ?, processeur = ?, ram = ?, disque_dur = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, o.idModele);
            ps.setString(2, o.processeur);
            ps.setInt(3, o.ram);
            ps.setInt(4, o.disqueDur);
            ps.setInt(5, o.id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

    public static void delete(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "DELETE FROM ordinateur WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

    public static Ordinateur findById(int id) throws Exception {
        Ordinateur o = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT o.id, o.id_modele, o.processeur, o.ram, o.disque_dur, "
                    + "m.libelle AS libelle_modele, ma.libelle AS libelle_marque "
                    + "FROM ordinateur o, modele m, marque ma "
                    + "WHERE o.id_modele = m.id AND m.id_marque = ma.id AND o.id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                o = new Ordinateur();
                o.id = rs.getInt("id");
                o.idModele = rs.getInt("id_modele");
                o.processeur = rs.getString("processeur");
                o.ram = rs.getInt("ram");
                o.disqueDur = rs.getInt("disque_dur");
                o.libelleModele = rs.getString("libelle_modele");
                o.libelleMarque = rs.getString("libelle_marque");
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return o;
    }

    public static List<Ordinateur> findAll() throws Exception {
        List<Ordinateur> liste = new ArrayList<Ordinateur>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT o.id, o.id_modele, o.processeur, o.ram, o.disque_dur, "
                    + "m.libelle AS libelle_modele, ma.libelle AS libelle_marque "
                    + "FROM ordinateur o, modele m, marque ma "
                    + "WHERE o.id_modele = m.id AND m.id_marque = ma.id";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordinateur o = new Ordinateur();
                o.id = rs.getInt("id");
                o.idModele = rs.getInt("id_modele");
                o.processeur = rs.getString("processeur");
                o.ram = rs.getInt("ram");
                o.disqueDur = rs.getInt("disque_dur");
                o.libelleModele = rs.getString("libelle_modele");
                o.libelleMarque = rs.getString("libelle_marque");
                liste.add(o);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        for (int i = 0; i < liste.size(); i++) {
            Ordinateur o = liste.get(i);
            o.etatActuel = findEtatActuel(o.id);
        }
        return liste;
    }

    public static String findEtatActuel(int idOrdi) throws Exception {
        String libelle = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT e.libelle "
                    + "FROM ordinateur_etat oe, etat e "
                    + "WHERE oe.id_etat = e.id AND oe.id_ordi = ? "
                    + "ORDER BY oe.date DESC, oe.id DESC "
                    + "LIMIT 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOrdi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                libelle = rs.getString("libelle");
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return libelle;
    }

    public static List<String[]> findModeles() throws Exception {
        List<String[]> liste = new ArrayList<String[]>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id, libelle FROM modele";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] ligne = new String[2];
                ligne[0] = rs.getString("id");
                ligne[1] = rs.getString("libelle");
                liste.add(ligne);
            }
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
        return liste;
    }
}
