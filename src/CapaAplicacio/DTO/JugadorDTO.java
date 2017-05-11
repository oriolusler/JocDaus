package CapaAplicacio.DTO;

import java.util.ArrayList;
import java.util.List;

import CapaDomini.Jugador;
import CapaDomini.Partida;

public class JugadorDTO {

	private String nom;
	
	public String getNom() {
		return nom;
	}

	public ArrayList<PartidaDTO> getPartides() {
		return partides;
	}
	
	private ArrayList<PartidaDTO> partides = new ArrayList<>();

	public JugadorDTO(Jugador jugador) {
		this.nom = jugador.getNom();
		initPartides(jugador.getPartides());
	}

	private void initPartides(List<Partida> partides) {
		for (Partida p : partides) {
			this.partides.add(new PartidaDTO(p));
		}
	}
}
