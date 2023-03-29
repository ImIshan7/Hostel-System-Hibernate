package lk.ijse.hotel.orm.bo.custom.impl;

import lk.ijse.hotel.orm.bo.custom.ReservationBO;
import lk.ijse.hotel.orm.dao.DAOFactory;
import lk.ijse.hotel.orm.dao.custom.ReservationDAO;
import lk.ijse.hotel.orm.dao.custom.RoomDAO;
import lk.ijse.hotel.orm.dao.custom.StudentDAO;
import lk.ijse.hotel.orm.dto.ReservationDTO;
import lk.ijse.hotel.orm.dto.RoomDTO;
import lk.ijse.hotel.orm.dto.StudentDTO;
import lk.ijse.hotel.orm.entity.Reservation;
import lk.ijse.hotel.orm.entity.Room;
import lk.ijse.hotel.orm.entity.Student;
import lk.ijse.hotel.orm.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Reservation);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Student);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Rooms);
    private Session session;

    @Override
    public List<ReservationDTO> loadAll() throws Exception {

        List<Reservation> reservations = reservationDAO.loadAll();
        List<ReservationDTO> reservationDTOS=new ArrayList<>();

        for (Reservation reservation:reservations) {
            reservationDTOS.add(
                    new ReservationDTO(
                            reservation.getResID(),
                            reservation.getDate(),
                            reservation.getStudent(),
                            reservation.getRoom(),
                            reservation.getStatus()
                    )
            );
        }

        return reservationDTOS;
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) throws Exception {

        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();
        try{
            reservationDAO.setSession(session);
            reservationDAO.save(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();
        try{
            reservationDAO.setSession(session);
            reservationDAO.update(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {

        session= SessionFactoryConfiguration.getInstance().getSession();
        try {
            studentDAO.setSession(session);
            Student student = studentDAO.getObject(id);
            session.close();
            return new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            );
        } catch (Exception ex){

        }
        return null;
    }

    @Override
    public RoomDTO getRoom(String id) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        try {
            roomDAO.setSession(session);
            Room room = roomDAO.getObject(id);
            session.close();
            return new RoomDTO(
                    room.getId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            );

        } catch (Exception ex){

        }
        return null;

    }


    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();
        try{
            reservationDAO.setSession(session);
            reservationDAO.delete(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextReservationID() throws Exception {
        return reservationDAO.generateID();
    }
}
