
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
		int maxJugador= jugadors.size();
		int[] tirada= new int[2];
		boolean guanyador= false;
		int contTirades;
		ordenarJugadors(torn);
		List <Fitxa> fitxesList= fitxaDAO.getAllFitxesPartida(joc);
		do {
			//jugador que fem la jugada
			Jugador tornJugador= jugadors.get(torn);
			List <Fitxa> fitxesJugador= new ArrayList<>();
			for (Fitxa tipe:fitxesList){
				if(tipe.getJugador()==tornJugador){
                fitxesJugador.add(tipe);
				}
			}
			contTirades=0;
			while (true) {
				
				//primera tirada
				tirada=tiradaDaus();
				List <Fitxa> fitxesActives=llistaFitxesActives(fitxesJugador);
				if(fitxesActives.size()<4){
					//cas qeu tenim fitxes en casella
					if(tirada[0]==tirada[1]){
						// cas que els dos daus tinguin el mateix resultat
						if(contTirades<2){
							++contTirades;
							//cas que tenim fitxes a casa//entradaALTablero
							posarEntrada(fitxesJugador,tornJugador.getColor());
							if (llistaFitxesActives(fitxesJugador).size()<4) {										
								
													
							
						}else{
							// cas s'ha me matar una ftixa excepte que estigui a casa, pasadisMeta o Meta
							matarUnafitxa(fitxesActives);	
						}
					}
				else{
					////comprovar moviments amb dau color fitxa jugador
								//usuari selecciona casella
								//posar fitxa en casella seleccionada
								//comprobar si mata alguna casella
							   //comprovar si ha guanyat
							   //break per sortir buccle
							   break;
				}
				}
				
				
			}
		} while (!guanyador);
	
		
	}



	private static void matarUnafitxa(List<Fitxa> fitxesActives) {
		// ha de complir les seguents condicions ni ha de estar fora de pasa
for(Fitxa tip: fitxesActives){
	if(tip.getPosicio()<69){
		//esta dintr del tauler
		fitxaDAO.moureFitxa(tip, 0);
	}
}
	}

	private static void posarEntrada(List<Fitxa> fitxesJugador,String color) {
		// obetenir la posicio entrada i de color
		for (Fitxa tip : fitxesJugador) {
			Casella entrada = especialDAO.getCasellaByColor(color,"Entrada");
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



	private static void ordenarJugadors(int torn) {

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
				sc.close();
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