package CapaAplicacio;

import java.util.ArrayList;
import CapaAplicacio.DTO.JugadorDTO;
import CapaAplicacio.DTO.PartidaDTO;
import CapaDomini.Dau;
import CapaDomini.Jugador;
import CapaDomini.Partida;
import CapaPersistencia.JugadorBBDD;
import CapaPersistencia.PartidaBBDD;

public class ControladorJocDaus {

	private Dau dau1, dau2;
	private final int CARES_DAUS = 6;
	private Jugador jugador;
	private Partida partida;

	public ControladorJocDaus() {
		dau1 = new Dau(CARES_DAUS);
		dau2 = new Dau(CARES_DAUS);
		jugador = new Jugador("Anonim");
	}

	public void jugar() {
		int tirada1 = this.tirarDau(dau1);
		int tirada2 = this.tirarDau(dau2);
		jugador.addPartida(tirada1, tirada2);
	}

	private int tirarDau(Dau dau) {
		dau.llenca();
		return dau.valorCara();
	}

	public JugadorDTO getJugadorDTO() {
		return new JugadorDTO(jugador);
	}

	public PartidaDTO resultatPartidaEnCurs() {
		return new PartidaDTO(jugador.getPartidaEnCurs());
	}

	public double guanyadesPercent() {

		return jugador.nombreGuanyades() / (float) jugador.nombrePartides()
				* 100;
	}

	public void nouJugador(String nom) throws Exception {
		if (!nom.equalsIgnoreCase("Anonim")) {
			jugador = new Jugador(nom);
			try {
				JugadorBBDD.storeJugador(jugador);
			} catch (Exception e) {
				throw new Exception("Aquest jugador ja està registrat!");
			}
		}
	}

	public void novaPartida(String nom, int ti1, int ti2) throws Exception {

		if (!nom.equalsIgnoreCase("Anonim")) {
			partida = new Partida(ti1, ti2, PartidaBBDD.quantesPartides());
			try {
				PartidaBBDD.storePartida(jugador, partida);
			} catch (Exception e) {
				throw new Exception("No es guarda partida");
			}
		}
	}

	public ArrayList<PartidaDTO> resultatPartides() throws Exception {
		ArrayList<PartidaDTO> result = new ArrayList<>();
		ArrayList<Partida> partides;

		if (jugador.getNom() == "Anonim") {
			partides = jugador.getPartides();

			for (Partida p : partides) {
				result.add(new PartidaDTO(p));
			}

			return result;
		} else {
			try {
				partides = PartidaBBDD.llistatPArtidesBDD(jugador);
				for (Partida p : partides) {
					result.add(new PartidaDTO(p));
				}
				return result;

			} catch (Exception e) {
				throw new Exception(
						"No s'ha pogut recuperar partides guardades");
			}
		}
	}

	public String nomsBDD() {

		try {
			return JugadorBBDD.getJugadors();
		} catch (Exception e) {
			return null;
		}

	}
}
