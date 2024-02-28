
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import ambDAO.*;
import InterfacesDAO.*;
import model.Partida;
import model.Casella;
import model.CasellaEspecial;
import model.Fitxa;
import model.Jugador;

public class main {
	// part de la connexio
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;

	// creacio DAO
	private static JugadorDAO jugadorDAO;
	private static PartidaDAO partidaDAO;
	private static FitxaDAO fitxaDAO;
	private static CasellaDAO casellaDAO;
	private static CasellaEspecialDAO especialDAO;

	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {

			// exception handling omitted for brevityaa

			serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg2.xml").build();

			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

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
		colors.add("Blau");
		colors.add("Groc");

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