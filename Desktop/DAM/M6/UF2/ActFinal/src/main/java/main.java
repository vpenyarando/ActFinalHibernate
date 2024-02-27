User
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
import model.Jugador;

public class main {
	//part de la connexio
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	
	public static synchronized SessionFactory getSessionFactory () {
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
	
	//Atributs implementats
	static JugadorDAOImp jugadorDAO;
	

	public static void main(String[] args) {
		// crear partida
		iniciarPartida();
		// S'assignen els jugadors a la partida, indicant nom i color.
		creaciójugadors();

		// joc.assignarJugadors()
		// farem per controlar la partida

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
			String nomJugador=sc.nextLine();
			System.out.println("Si us plau selecciona quin color vols");
			for(int j=0; i<colors.size();i++) {
				System.out.println(i+" color "+colors.get(i));
			}
			do {
				System.out.println("Si us plau, ha de se entre 0 i "+colors.size()+":");
				while (!sc.hasNextInt()) {
					System.out.println("Si us plau el número ha de ser entre 2 i 4:");
					sc.next();
				}
				opcio = sc.nextInt();
				sc.close();
			} while (opcio <= 0|| opcio > colors.size());
			String colorSeleccionat = colors.get(opcio);
			jugadors.add(new Jugador(nomJugador,colorSeleccionat,0));
			colors.remove(opcio);
			
			
		}
		//ficar la les dades
		inciarDadesJugador(jugadors);
		
			
	}

	private static void inciarDadesJugador(List<Jugador> jugadorsCreats) {
		try {
			session = getSessionFactory().openSession();
			jugadorDAO = new JugadorDAO(getSessionFactory().getCurrentSession());
			
			session.beginTransaction();
			for(Jugador jugador :jugadorsCreats) {
				session.save(jugador);
			}
		
			session.getTransaction().commit();
			
			
			System.out.println("Mica a mica ho estem fent");

			System.out.println("Fet");
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	private static void afegirJugadorsBBDDJugador(List<Jugador> jugadors2) {
		
		
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
		// S'assignen els jugadors a la partida, indicant nom i color.
		// Crear Jugadors i colors
		// joc.assignarJugadors()
		// farem per controlar la partida

	}