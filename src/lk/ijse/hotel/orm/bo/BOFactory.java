package lk.ijse.hotel.orm.bo;

import lk.ijse.hotel.orm.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hotel.orm.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotel.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotel.orm.bo.custom.impl.UsersBOImpl;

public class BOFactory {
    private static BOFactory BOFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance(){
        if (BOFactory ==null) {
            BOFactory =new BOFactory();
        }
        return BOFactory;
    }

    public enum BOTypes {
        Student,Rooms,Reservation,Users
    }

    public static SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Student :
                return new StudentBOImpl();
            case Rooms:
                return new RoomBOImpl();
            case Reservation:
                return new ReservationBOImpl();
            case Users:
                return new UsersBOImpl();
            default:
                return null;
        }
    }
}
