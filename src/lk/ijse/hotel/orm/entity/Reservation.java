package lk.ijse.hotel.orm.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reservation {


    @Id
    @Column(length = 10,name = "res_id")
    private String resID;

    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "student_id",insertable = false ,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id",updatable = false,insertable = false)
    private Room room;

    @Column(name = "status")
    private String status;

    public Reservation(String resId, Date date, String status) {
    }

    public Reservation(String resID, Date date, Student student, Room room, String status) {
        this.resID = resID;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation() {

    }


    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resID='" + resID + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }
}
