package CapaAplicacio.DTO;

import CapaDomini.Partida;

public class PartidaDTO {

	private int dau1, dau2;

	public String getDau1() {
		return String.valueOf(dau1);
	}

	public String getDau2() {
		return String.valueOf(dau2);
	}

	public String getResult() {
		String resultat;

		if (getGuanyada()) {
			resultat = "guanyada";
		} else {
			resultat = "perduda";
		}
		return "Dau1: " + dau1 + ", Dau2: " + dau2 + ", Resultat: " + resultat;
	}

	private boolean getGuanyada() {
		 return dau1 + dau2 == 7;
	}

	public PartidaDTO(Partida p) {
		this.dau1 = p.getDau1();
		this.dau2 = p.getDau2();
	}
}
