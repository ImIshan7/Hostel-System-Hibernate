package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.CrudDAO;
import lk.ijse.hotel.orm.entity.Student;
import org.hibernate.Session;

public interface StudentDAO extends CrudDAO<Student> {
    void setSession(Session session) throws Exception;
}
