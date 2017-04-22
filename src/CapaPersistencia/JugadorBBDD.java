package CapaPersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CapaDomini.Jugador;

public class JugadorBBDD {

	public static Jugador getCoin(String coin_TYPE) throws Exception {
		ConnectionBBDD connection = LoginBBDD.getConnection();

		try {
			String sql = "SELECT * FROM JUGADOR";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				String nom;
				nom = rs.getString("NOM");
				//return new Coin(value, type);
				return new Jugador(nom);
			}

			throw new Exception("No s'ha trobat moneda!");
		} catch (SQLException e) {
			throw new Exception("ERROR");
		}

	}

	public static void storeJugador(Jugador jugador) throws Exception {
		ConnectionBBDD connection = LoginBBDD.getConnection();

		//String sql = "UPDATE MONEDA  SET STATE_COIN=? WHERE TYPE_COIN=?";
		String sql = "INSERT INTO JUGADOR VALUES(?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setString(1, jugador.getNom());
	

		if (pst.executeUpdate() != 1)
			throw new Exception("JUGADOR NO GUARDAD!");
	}
}
