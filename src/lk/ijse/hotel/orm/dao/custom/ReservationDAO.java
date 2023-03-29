package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.CrudDAO;
import lk.ijse.hotel.orm.entity.Reservation;
import org.hibernate.Session;

public interface ReservationDAO extends CrudDAO<Reservation> {
    void setSession(Session session) throws Exception;
}
