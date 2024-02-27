package ambDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Utils {
	static Session session;
	static SessionFactory sF;
	static ServiceRegistry sR;
	static MetadataSources sources;

	public static synchronized SessionFactory getSessionFactory() {
		if (sF == null) {

			// exception handling omitted for brevityaa
			// usar listeners
			/*
			 * BootstrapServiceRegistry bootstrapRegistry = new
			 * BootstrapServiceRegistryBuilder() .applyIntegrator(new
			 * EventListenerIntegrator()) .build();
			 */
			sR = new StandardServiceRegistryBuilder().configure("/recourses/hibernate.cfg1.xml").build();

			sources = new MetadataSources(sR);

			Metadata metadata = sources.getMetadataBuilder().build();

			/// comentar o descomentar la linea del interceptor
			sF = metadata.getSessionFactoryBuilder()
					// .applyInterceptor(new LoggingInterceptor())
					.build();

		}
		return sF;

	}

}
