import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;

public class main {

    public static void main(String[] args) {
        /*// Configura y construye la SessionFactory desde hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Abre una nueva sesión
        org.hibernate.Session session = sessionFactory.openSession();

        // Comienza una transacción
        session.beginTransaction();

        try {
            // Crea instancias de las entidades y persiste para que se creen las tablas
            Casella casella = new Casella();  
            session.persist(casella);

            Fitxa fitxa = new Fitxa();  
            session.persist(fitxa);

            Jugador jugador = new Jugador();  
            session.persist(jugador);

            Partida partida = new Partida();  
            session.persist(partida);

            // Confirma la transacción
            session.getTransaction().commit();
            
            
        } catch (Exception e) {
            // Revierte la transacción en caso de error
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cierra la sesión y la SessionFactory al finalizar
            session.close();
            sessionFactory.close();
        }*/
    	
    	
    	Scanner scanner = new Scanner(System.in);
        int cantidadJugadores;

        // Bucle hasta que se ingrese una cantidad válida de jugadores
        do {
            System.out.print("Ingrese la cantidad de jugadores (entre 2 y 4): ");
            cantidadJugadores = scanner.nextInt();

            if (cantidadJugadores < 2 || cantidadJugadores > 4) {
                System.out.println("La cantidad de jugadores debe estar entre 2 y 4. Inténtelo nuevamente.");
            }

        } while (cantidadJugadores < 2 || cantidadJugadores > 4);

        // Crear una lista para almacenar los colores de los jugadores
        List<String> colores = new ArrayList<>();

        // Pregunta por el color de las fichas de cada jugador
        for (int i = 1; i <= cantidadJugadores; i++) {
            System.out.print("Ingrese el color de las fichas del Jugador " + i + ": ");
            String color = scanner.next();
            colores.add(color);
        }

        // Muestra los colores ingresados
        System.out.println("\nColores de las fichas de los jugadores:");
        for (int i = 0; i < colores.size(); i++) {
            System.out.println("Jugador " + (i + 1) + ": " + colores.get(i));
        }

        // Cierra el scanner
        scanner.close();
	    }
	    
	    private static String obtenerColorPorOpcion(int opcionColor) {
	        switch (opcionColor) {
	            case 1:
	                return "Rojo";
	            case 2:
	                return "Azul";
	            case 3:
	                return "Verde";
	            case 4:
	                return "Amarillo";
	            default:
	                return "Desconocido";
	        }
	    }
}
