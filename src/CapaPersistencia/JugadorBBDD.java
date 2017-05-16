package CapaPersistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CapaDomini.Jugador;

public class JugadorBBDD {


	public static void storeJugador(Jugador jugador) throws Exception {
		
		ConnectionBBDD connection = LoginBBDD.getConnection();

		String sql = "INSERT INTO JUGADOR VALUES(?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setString(1, jugador.getNom());
	

		if (pst.executeUpdate() != 1)
			throw new Exception("JUGADOR NO GUARDAD!");
	}
}
