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
        return jugador.nombreGuanyades() / (float) jugador.nombrePartides() * 100;
    }

    public void nouJugador(String nom) throws Exception {
        //Si el nom �s "Anonim" no cal fer res
        if (!nom.equalsIgnoreCase("Anonim")) {          
               jugador = new Jugador(nom);
               try {
				JugadorBBDD.storeJugador(jugador);
			} catch (Exception e) {
				throw new Exception("Aquest jugador ja està registrat!");
				//metode per sobre esciure jugador bdd?
			}
        }
    }

    public void novaPartida(String nom, int ti1, int ti2) throws Exception {
        //Si el nom �s "Anonim" no cal fer res
        if (!nom.equalsIgnoreCase("Anonim")) {          
               jugador = new Jugador(nom);
               partida = new Partida(ti1, ti2,PartidaBBDD.quantesPartides());
               try {
				PartidaBBDD.storePartida(jugador, partida);
			} catch (Exception e) {
				throw new Exception("No es guarda partida");
				//metode per sobre esciure jugador bdd?
			}
        }
    }
    
    public ArrayList<PartidaDTO> resultatPartides() {
        ArrayList<Partida> partides=jugador.getPartides();
        ArrayList<PartidaDTO> result=new ArrayList<>();
        
        for(Partida p:partides){
        	result.add(new PartidaDTO(p));
        }
        
        return result;
    }
    
    public String nomsBDD(){
   
    	try {
			return JugadorBBDD.getJugadors();
		} catch (Exception e) {
			return null;
		}
    	
   }
}
