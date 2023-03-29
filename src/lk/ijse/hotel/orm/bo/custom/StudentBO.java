package lk.ijse.hotel.orm.bo.custom;

import lk.ijse.hotel.orm.bo.SuperBO;
import lk.ijse.hotel.orm.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentDTO> loadAll() throws Exception;
    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    String generateNextStudentID() throws Exception;

}
