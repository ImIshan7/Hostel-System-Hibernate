package lk.ijse.hotel.orm.dao;

import lk.ijse.hotel.orm.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotel.orm.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel.orm.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotel.orm.dao.custom.impl.UsersDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null) {
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        Student,Rooms,Reservation,Users
    }

    public  SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Student :
                return new StudentDAOImpl();
            case Rooms:
                return new RoomDAOImpl();
            case Reservation:
                return new ReservationDAOImpl();
            case Users:
                return new UsersDAOImpl();
            default:
                return null;
        }
    }
}
