package lk.ijse.hotel.orm.bo.custom.impl;

import lk.ijse.hotel.orm.bo.custom.StudentBO;
import lk.ijse.hotel.orm.dao.DAOFactory;
import lk.ijse.hotel.orm.dao.custom.StudentDAO;
import lk.ijse.hotel.orm.dto.StudentDTO;
import lk.ijse.hotel.orm.entity.Student;
import lk.ijse.hotel.orm.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {


    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Student);

    private Session session;

    @Override
    public List<StudentDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student> students = studentDAO.loadAll();
        List<StudentDTO>studentDTOS=new ArrayList<>();

        for (Student student:students) {
            studentDTOS.add(
                    new StudentDTO(
                            student.getId(),
                            student.getName(),
                            student.getAddress(),
                            student.getContactNo(),
                            student.getDob(),
                            student.getGender()
                    )
            );
        }

        return studentDTOS;
    }

    @Override
    public String saveStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            studentDAO.setSession(session);
           String id= studentDAO.save(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return null;
        }


    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {


        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            studentDAO.setSession(session);
            studentDAO.update
                    (new Student
                            (studentDTO.getId(),
                                    studentDTO.getName(),
                                    studentDTO.getAddress(),
                                    studentDTO.getContactNo(),
                                    studentDTO.getDob(),
                                    studentDTO.getGender()
                            ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        session=SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try {
            studentDAO.setSession(session);
            studentDAO.delete(
                   new Student(
                           studentDTO.getId(),
                           studentDTO.getName(),
                           studentDTO.getAddress(),
                           studentDTO.getContactNo(),
                           studentDTO.getDob(),
                           studentDTO.getGender()
                   )
           );
           transaction.commit();
           session.close();
           return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
        return false;

    }

    @Override
    public String generateNextStudentID() throws Exception {
        return studentDAO.generateID();    }
}
