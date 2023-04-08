package lk.ijse.hotel.orm.dao.custom;

import lk.ijse.hotel.orm.dao.SuperDAO;
import lk.ijse.hotel.orm.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<StudentDetailsDTO> getAllStudentProjection();
}
