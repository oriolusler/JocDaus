package CapaPersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import CapaDomini.Jugador;
import CapaDomini.Partida;

public class PartidaBBDD {

	public static void storePartida(Jugador jugador, Partida partida)
			throws Exception {

		ConnectionBBDD connection = LoginBBDD.getConnection();

		String sql = "INSERT INTO PARTIDA VALUES(?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);

		pst.setString(1, jugador.getNom());
		pst.setInt(2, partida.getDau1());
		pst.setInt(3, partida.getDau2());
		pst.setInt(4, partida.getId());

		if (pst.executeUpdate() != 1)
			throw new Exception("PARTIDA NO GUARDADA!");
	}

	public static ArrayList<Partida> llistatPArtidesBDD(Jugador jugador)
			throws Exception {
		ConnectionBBDD connection = LoginBBDD.getConnection();
		ArrayList<Partida> llistat = new ArrayList<>();

		try {
			String sql = "SELECT * FROM PARTIDA WHERE NOM =  ?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.clearParameters();

			preparedStatement.setString(1, jugador.getNom());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				int dau1, dau2;
				dau1 = rs.getInt("DAU1");
				dau2 = rs.getInt("DAU2");
				llistat.add(new Partida(dau1, dau2));
			}

			return llistat;
		} catch (SQLException e) {
			throw new Exception("ERROR CONNEXIÓ BDD");
		}

	}

	public static int quantesPartides() throws Exception {

		ConnectionBBDD connection = LoginBBDD.getConnection();

		try {
			String sql = "SELECT MAX(IDPARTIDA) AS ID FROM PARTIDA";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.clearParameters();
			ResultSet rs = preparedStatement.executeQuery();

			int maxID = 0;

			while (rs.next()) {

				maxID = rs.getInt("ID");
			}

			return maxID;
		} catch (SQLException e) {
			throw new Exception("ERROR");
		}
	}

}
