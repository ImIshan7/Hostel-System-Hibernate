package lk.ijse.hotel.orm.util;


import lk.ijse.hotel.orm.entity.Reservation;
import lk.ijse.hotel.orm.entity.Room;
import lk.ijse.hotel.orm.entity.Student;
import lk.ijse.hotel.orm.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguaration {


        private static SessionFactoryConfiguaration factoryConfiguration;
        private SessionFactory sessionFactory;

        private SessionFactoryConfiguaration(){

            sessionFactory = new Configuration().mergeProperties(Utility.getProperties())
                    .addAnnotatedClass(Reservation.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Users.class)
                    .buildSessionFactory();
        }

        public static SessionFactoryConfiguaration getInstance() {
            return (null == factoryConfiguration) ?
                    factoryConfiguration = new SessionFactoryConfiguaration() : factoryConfiguration;
        }

        public Session getSession() throws HibernateException {

            Session session = sessionFactory.openSession();

            return session;
        }

    }

