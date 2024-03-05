
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

import ambDAO.*;
import InterfacesDAO.*;
import model.Partida;
import model.Casella;
import model.CasellaEspecial;
import model.Fitxa;
import model.Jugador;

public class main {

	// creacio DAO
	private static JugadorDAO jugadorDAO;
	private static PartidaDAO partidaDAO;
	private static FitxaDAO fitxaDAO;
	private static CasellaDAO casellaDAO;
	private static CasellaEspecialDAO especialDAO;

	private static Scanner sc = new Scanner(System.in);

	// Atributs de partida
	private static Partida joc;
	private static List<Jugador> jugadors;
	private static List<Fitxa> fitxes;

	public static void main(String[] args) {
		// Iniciar instancias
		jugadorDAO = new JugadorDAOImp();
		partidaDAO = new PartidaDAOImp();
		fitxaDAO = new FitxaDAOImpl();
		casellaDAO = new CasellaDAOImpl();
		especialDAO = new CasellaEspecialImp();
		// crear partida
		iniciarPartida();
		// S'assignen els jugadors a la partida, indicant nom i color.
		creaciójugadors();

		// crear Fitxes
		crearBaseDadesFitxa();

		// crearTauler
		crearCaselles();

		// iniciar el joc
		inciarJugar();

		cloenda();

	}

	private static void inciarJugar() {
		int torn = buscarAleatoriamentJugador();
		int[] tirada = new int[2];
		boolean guanyador = false;
		int contTirades;
		List<Fitxa> fitxesList = fitxaDAO.getAllFitxesPartida(joc);
		do {
			// jugador que fem la jugada
			Jugador tornJugador = jugadors.get(torn);
			List<Fitxa> fitxesJugador = new ArrayList<>();
			for (Fitxa tipe : fitxesList) {
				if (tipe.getJugador() == tornJugador) {
					fitxesJugador.add(tipe);
				}
			}
			// posem a zero és control de 3 tirades repetides
			// fiquem bucle per repetir la tirada repetida d'un mateix jugador
			contTirades = 0;
			while (true) {

				// primera tirada
				tirada = tiradaDaus();
				List<Fitxa> fitxesActives = llistaFitxesActives(fitxesJugador);

				if (tirada[0] == tirada[1]) {
					// cas que els dos daus tinguin el mateix resultat
					if (contTirades < 2) {// evitem que no repeteixi per tercera vegada
						++contTirades;// incrementar el contador
						// cas que tenim fitxes a casa//entradaALTablero
						if (fitxesActives.size() > 4) {
							posarEntrada(fitxesJugador, tornJugador.getColor());
						} else {
							determinarMovimentFitxa(fitxesActives, tirada);
						}

					} else {
						// cas s'ha em matar una ftixa excepte que estigui a casa, pasadisMeta o Meta
						// tenim cambiar el nom realment selecciona que fitxa de
						matarUnafitxa(fitxesActives);
						// reiniciem el contador de tirades per repetició
						contTirades = 0;
						// canvi de torn
						torn = (torn == jugadors.size() - 1) ? 0 : torn + 1;
						break;

					}

				} else {
					determinarMovimentFitxa(fitxesActives, tirada);
					
					//comprovar si totes estan

					guanyador=comprobarTotesMeta(fitxesJugador);
					if(guanyador) {
						int victories= tornJugador.getVictories()+1;
						tornJugador.setVictories(victories);
						jugadorDAO.saveOrUpdate(tornJugador);
					}
					// break per sortir buccle
					break;
				}

			}
			//canvi de torn
			torn =(torn+1>=jugadors.size())?torn+1:0;
		} while (!guanyador);

	}

	private static boolean comprobarTotesMeta(List<Fitxa> fitxesJugador) {
	    boolean meta = true;
	    for (Fitxa tip : fitxesJugador) {
	        if (tip.getPosicio() != 76) {
	            meta = false;
	            break;
	        }
	    }
	    return meta;
	}

	private static void determinarMovimentFitxa(List<Fitxa> fitxesActives, int[] tirada) {
		int sumaTirada = tirada[0] + tirada[1];
		ArrayList<MovimentsPossibles> moviments = new ArrayList<>();

		for (Fitxa tip : fitxesActives) {
			int posicioActual = tip.getPosicio();
			int novaPosicio = posicioActual + sumaTirada;
			String colorFitxa = tip.getJugador().getColor();
			if (posicioActual <= 68) {
				if (novaPosicio > 69) {
					if (!existeixBarrera(posicioActual, 68, tip)) {
						// cas que supera al 68
						if (colorFitxa.equals("Groc") && novaPosicio <= 76) {

							// cas que no tingui obstacles de barreres
							moviments.add(new MovimentsPossibles(tip, novaPosicio));

						} else {
							int casellaSelecionada = novaPosicio - 68;
							moviments.add(new MovimentsPossibles(tip, casellaSelecionada));
						}
					}

				} else {
					int casellaEntrada = especialDAO.casellaEntradaPasadisMeta(colorFitxa);
					if (posicioActual < casellaEntrada && novaPosicio > casellaEntrada) {
						if (!existeixBarrera(posicioActual, casellaEntrada, tip)) {
							// nova Posició li restem casella entrada i sumen 68 ens dona posicio Pasadis
							int casellaPosicionar = novaPosicio - casellaEntrada + 68;
							if (casellaPosicionar <= 76) {
								moviments.add(new MovimentsPossibles(tip, casellaPosicionar));
							}

						}
					} else {
						// cas qualsevol casella que no especial
						if (!existeixBarrera(posicioActual, novaPosicio, tip)) {
							moviments.add(new MovimentsPossibles(tip, novaPosicio));
						}
					}
				}

			}
		}
		seleccionarMoviment(moviments);

	}

	private static void seleccionarMoviment(ArrayList<MovimentsPossibles> moviments) {
		if (moviments.size() < 1) {
			System.out.println("No tens Moviments per poder realitzar");
		} else {
			System.out.println("Tens seleccionar número de casella");
			for (MovimentsPossibles mogut : moviments) {
				System.out.println("Posicio " + mogut.getPosicioFinal());
			}
			int opcio;
			boolean seleccioValida = false;
			do {
				while (!sc.hasNextInt()) {
					System.out.println("Si us plau el número ha de ser de la casella");
					sc.next();
				}
				opcio = sc.nextInt();

				sc.nextLine();
				for (MovimentsPossibles mogut : moviments) {
					if (mogut.getPosicioFinal() == opcio) {
						System.out.println("Posicio " + mogut.getPosicioFinal());
						seleccioValida = true;
						List<Fitxa> victimes=fitxaDAO.getAllFitxesByPosicio(mogut.getPosicioFinal(), joc);
						if(!(victimes.size()<2)) {//seria ella mateixa i la segona fitxa
							int cont=0;
							//cas que hi hagi una fitxa en la casella
							for (Fitxa tip: victimes) {
								
								if(tip.getJugador().getColor().equals(mogut.getFitxa().getJugador().getColor())) {
									++cont;
									if(cont==02) {
										System.out.println("Barrera");
									}
								//s'ens escapa el casq que fos dos fitxes en seguro no podriem escollir
								}else {
									if(!casellaDAO.verificarCasaSegura(mogut.getPosicioFinal())){
										//casella que no te seguro
										fitxaDAO.capturarFitxa(tip);
										
									}
								}
								
							}
							
							
						}
						fitxaDAO.moureFitxa(mogut.getFitxa(), opcio);
						break;
					}

				}
				if (!seleccioValida) {
					System.out.println("Nùmero no vàlid.");
				}
			} while (!seleccioValida);
		}

	}

	private static boolean existeixBarrera(int posicioActual, int novaPosicio, Fitxa tip) {
		boolean barrera = false;

		if (posicioActual > novaPosicio) {// cas que pasi del 68

			for (int i = posicioActual; i < 69; i++) {
				List<Fitxa> fitxesEnCasella = fitxaDAO.getAllFitxesByPosicio(i, joc);
				if (!fitxesEnCasella.isEmpty()) {
					if (fitxesEnCasella.size() > 1) {
						barrera = true;
						return barrera;
					}

				}
			}
			// comprobar fitxes despues de 68
			for (int i = 1; i <= novaPosicio; i++) { // Comprovar les caselles desde 1 fins nova posició
				List<Fitxa> fitxesEnCasella = fitxaDAO.getAllFitxesByPosicio(i, joc);
				if (!fitxesEnCasella.isEmpty()) {
					if (fitxesEnCasella.size() > 1) {
						barrera = true;
						return barrera;
					}
				}
			}

		} else {
			for (int i = posicioActual; i <= novaPosicio; i++) { // Comprovar les caselles desde actual fins nova
																	// posició
				List<Fitxa> fitxesEnCasella = fitxaDAO.getAllFitxesByPosicio(i, joc);
				if (!fitxesEnCasella.isEmpty()) {
					if (fitxesEnCasella.size() > 1) {
						barrera = true;
						return barrera;
					}
				}
			}

		}
		return barrera;
	}

	private static void matarUnafitxa(List<Fitxa> fitxesActives) {
		// ha de complir les seguents condicions ni ha de estar fora de pasa
		for (Fitxa tip : fitxesActives) {
			// son les posicions entre 1 i 69
			if (tip.getPosicio() < 69) {
				// esta dintr del tauler la posem en el posicio 0 i la tenim de desactivar
				tip.setPosicio(0);
				tip.setActiva(false);
				fitxaDAO.saveOrUpdate(tip);

			}
		}
	}

	private static void posarEntrada(List<Fitxa> fitxesJugador, String color) {
		// obetenir la posicio entrada i de color
		for (Fitxa tip : fitxesJugador) {
			Casella entrada = especialDAO.getCasellaByColor(color, "Entrada");

			fitxaDAO.moureFitxa(tip, entrada.getPosicio());
			break;
		}

	}

	private static List<Fitxa> llistaFitxesActives(List<Fitxa> fitxesJugador) {
		List<Fitxa> fitxesActives = new ArrayList<>();

		for (Fitxa fitxa : fitxesJugador) {
			if (fitxa.isActiva()) {
				fitxesActives.add(fitxa);
			}
		}

		return fitxesActives;
	}

	private static int[] tiradaDaus() {
		Dau dau = new Dau();

		int[] resposta = { dau.tirar(), dau.tirar() };

		return resposta;
	}

	private static int buscarAleatoriamentJugador() {
		Random random = new Random();
		return random.nextInt(jugadors.size());
	}

	private static void cloenda() {
		System.out.println("Partida acabada");

		sc.close();
	}

	private static void crearCaselles() {
		// caselles numeriques/ casa =0 /69-70-71-72-73-74-75 pasadis meta/ 76 Meta
		int numCaselles = 76;

		// assginar número posicions i caselles
		for (int i = 0; i <= numCaselles; i++) {
			Casella casella;
			switch (i) {
			case 0:
				casella = new Casella("casa", i, joc);
				casellaDAO.saveOrUpdate(casella);
				break;
			case 12:
			case 29:
			case 46:
			case 63:
				casella = new Casella("seguro", i, joc);
				casellaDAO.saveOrUpdate(casella);
				break;
			case 5:
				CasellaEspecial entradaGroc = new CasellaEspecial("seguro", i, joc, "entrada", "groc");
				especialDAO.saveOrUpdate(entradaGroc);
				break;
			case 17:
				CasellaEspecial sortidaBlau = new CasellaEspecial("blau", i, joc, "sortida", "blau");
				especialDAO.saveOrUpdate(sortidaBlau);
				break;

			case 22:
				CasellaEspecial entradaBlau = new CasellaEspecial("blau", i, joc, "entrada", "blau");
				especialDAO.saveOrUpdate(entradaBlau);
				break;

			case 34:
				CasellaEspecial sortidaVermell = new CasellaEspecial("vermell", i, joc, "sortida", "vermell");
				especialDAO.saveOrUpdate(sortidaVermell);
				break;

			case 39:
				CasellaEspecial entradaVermell = new CasellaEspecial("vermell", i, joc, "entrada", "vermell");
				especialDAO.saveOrUpdate(entradaVermell);
				break;

			case 51:
				CasellaEspecial sortidaVerd = new CasellaEspecial("verd", i, joc, "sortida", "verd");
				especialDAO.saveOrUpdate(sortidaVerd);
				break;

			case 56:
				CasellaEspecial entradaVerd = new CasellaEspecial("verd", i, joc, "entrada", "verd");
				especialDAO.saveOrUpdate(entradaVerd);
				break;

			case 68:
				CasellaEspecial sortidaGroc = new CasellaEspecial("groc", i, joc, "sortida", "groc");
				especialDAO.saveOrUpdate(sortidaGroc);
				break;

			case 69:
			case 70:
			case 71:
			case 72:
			case 73:
			case 74:
			case 75:
				casella = new Casella("seguro", i, joc);
				casellaDAO.saveOrUpdate(casella);
				break;
			case 76:
				casella = new Casella("meta", i, joc);
				break;
			default:
				casella = new Casella("blanca", i, joc);
				casellaDAO.saveOrUpdate(casella);
				break;
			}

		}
		// tipus de casella casa,blanca,seguro,pasadisMeta,Meta
		// Creació de les caselles

	}

	private static void crearBaseDadesFitxa() {
		for (Fitxa fitxa : fitxes) {
			fitxaDAO.saveOrUpdate(fitxa);
		}

	}

	public static void creaciójugadors() {
		int numJugadors;

		// Saludar
		System.out.println("Hola Bon dia, Quan jugadors sou?");

		numJugadors = preguntarNumJugadors();
		// Crear Jugadors i colors
		crearJugadors(numJugadors);

	}

	private static void crearJugadors(int numJugadors) {
		int opcio;

		List<String> colors = new ArrayList<>();
		colors.add("Vermell");
		colors.add("Verd");
		colors.add("Groc");
		colors.add("Blau");

		// Creació dels jugadors podem seleccionar
		for (int i = 0; i < numJugadors; i++) {
			System.out.println("Escriu el teu nom :");
			String nomJugador = sc.nextLine();
			System.out.println("Si us plau selecciona quin color vols");
			for (int j = 0; j < colors.size(); j++) {
				System.out.println(j + " color " + colors.get(j));
			}
			do {
				System.out.println("Si us plau, ha de se entre 0 i " + colors.size() + ":");
				while (!sc.hasNextInt()) {
					System.out.println("Si us plau el número ha de ser entre 0 i " + colors.size() + ":");
					sc.next();
				}
				opcio = sc.nextInt();

			} while (opcio <= 0 || opcio > colors.size());
			String colorSeleccionat = colors.get(opcio);
			jugadors.add(new Jugador(nomJugador, colorSeleccionat, 0));
			colors.remove(opcio);

		}
		// ficar la les dades
		inciarDadesJugador(jugadors);

	}

	private static void inciarDadesJugador(List<Jugador> jugadorsCreats) {

		for (Jugador jugador : jugadorsCreats) {
			// asignar fitxes al jugador
			for (int i = 0; i < 4; i++) {
				// creem l'objecte ifem la relació
				Fitxa fitxaAfegir = new Fitxa(0, false, jugador, joc);
				fitxes.add(fitxaAfegir);
			}
			jugadorDAO.saveOrUpdate(jugador);
		}

		System.out.println("Mica a mica ho estem fent");

	}

	public static int preguntarNumJugadors() {

		int opcio;

		do {
			System.out.println("Si us plau, ha de se entre 2 i 4:");
			while (!sc.hasNextInt()) {
				System.out.println("Si us plau el número ha de ser entre 2 i 4:");
				sc.next();
			}
			opcio = sc.nextInt();
			sc.close();
		} while (opcio < 2 || opcio > 4);
		return opcio;
	}

	public static void iniciarPartida() {
		joc = new Partida();
		// Es crea una partida, registrant la data d'inici i establint la
		// condició de “EnCurso”.
		joc.setEnCurso(true);
		// crear partida a la base dades
		crearPartidaBBDD();

	}

	private static void crearPartidaBBDD() {
		partidaDAO.saveOrUpdate(joc);
		partidaDAO.iniciarPartida();
		System.out.println("Partida Creada");

	}
}