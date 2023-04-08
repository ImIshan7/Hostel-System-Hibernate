package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.CrudDAO;
import lk.ijse.hotel.orm.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    void setSession(Session session) throws Exception;
    List<String> getIds() throws Exception;
}
