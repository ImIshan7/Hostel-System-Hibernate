package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.CrudDAO;
import lk.ijse.hotel.orm.entity.Room;
import org.hibernate.Session;

public interface RoomDAO extends CrudDAO<Room> {
    void setSession(Session session) throws Exception;
}
