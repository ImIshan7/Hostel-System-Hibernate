package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.CrudDAO;
import lk.ijse.hotel.orm.entity.Users;
import org.hibernate.Session;

public interface UsersDAO extends CrudDAO<Users> {
    void setSession(Session session) throws Exception;
}
