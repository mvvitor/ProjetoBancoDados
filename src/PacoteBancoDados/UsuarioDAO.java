package PacoteBancoDados;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public class UsuarioDAO {

    //Definindo o atributo/variável global       
    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String cadastraUsuario(UsuarioBean usuarios) {

        String sql = "insert into usuarios(usuario,senha)values(?,?)";

        try {
            //variavel sql recebendo os dados
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuarios.getNomeUsuario());
            ps.setString(2, usuarios.getSenhaUsuario());

            if (ps.executeUpdate() > 0) {
                return "Usuario cadastrado com sucesso!!!";

            } else {
                return "Erro ao cadastrar usuario!!!";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public boolean autenticaUsuario(UsuarioBean usuarios) {

        boolean flag = false;

        String sql = "select usuario,senha from usuarios where usuario=? and senha=?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuarios.getNomeUsuario());
            ps.setString(2, usuarios.getSenhaUsuario());

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

    public boolean autenticaPermissao(UsuarioBean usuarios) {

        boolean flag = false;

        String sql = "select usuario,senha from permissao where usuario=? and senha=?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuarios.getNomeUsuario());
            ps.setString(2, usuarios.getSenhaUsuario());

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

    public List<UsuarioBean> listaUsuario() {

        String sql = "select usuario,senha from usuarios";

        List<UsuarioBean> listarUsu = new ArrayList<>();

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {

                while (rs.next()) {

                    UsuarioBean ub = new UsuarioBean();

                    ub.setNomeUsuario(rs.getString(1));
                    ub.setSenhaUsuario(rs.getString(2));

                    listarUsu.add(ub);
                }
                return listarUsu;

            }

        } catch (SQLException ex) {
            return null;
        }
    }

}
