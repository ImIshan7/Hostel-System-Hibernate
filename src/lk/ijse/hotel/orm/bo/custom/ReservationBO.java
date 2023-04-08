package lk.ijse.hotel.orm.bo.custom;

import lk.ijse.hotel.orm.bo.SuperBO;
import lk.ijse.hotel.orm.dto.ReservationDTO;
import lk.ijse.hotel.orm.dto.RoomDTO;
import lk.ijse.hotel.orm.dto.StudentDTO;
import lk.ijse.hotel.orm.projection.StudentDetailsDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> loadAll() throws Exception;
    String saveReservation(ReservationDTO reservationDTO) throws Exception;
    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;
    StudentDTO getStudent(String id) throws Exception;
    RoomDTO getRoom(String id) throws Exception;
    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;
    String generateNextReservationID() throws Exception;
    List<String> getStudentIds();
    List<String> getRoomIds();
    List<StudentDTO> geAllStudents() throws Exception;
    List<RoomDTO>getAllRooms() throws Exception;
    boolean updateRoomQty(RoomDTO roomDTO) throws Exception;
    List<StudentDetailsDTO>getAllProjection();
    boolean checkStatusClick(String id,String status);

}
