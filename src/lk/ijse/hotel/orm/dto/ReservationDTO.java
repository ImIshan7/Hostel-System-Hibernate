package lk.ijse.hotel.orm.dto;

import lk.ijse.hotel.orm.entity.Room;
import lk.ijse.hotel.orm.entity.Student;

import java.sql.Date;

public class ReservationDTO {
    private String resID;
    private Date date;
    private Student student;
    private Room room;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(String resID, Date date, Student student, Room room, String status) {
        this.resID = resID;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resID='" + resID + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }
}
