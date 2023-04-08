package lk.ijse.hotel.orm.dao;

import lk.ijse.hotel.orm.dao.custom.impl.*;

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
        Student,Rooms,Reservation,Users,Query
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
            case Query:
                return new QueryDAOImpl();

            default:
                return null;
        }
    }
}
