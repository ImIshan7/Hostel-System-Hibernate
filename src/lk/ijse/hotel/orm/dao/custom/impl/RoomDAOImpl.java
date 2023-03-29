package lk.ijse.hotel.orm.dao.custom.impl;

import lk.ijse.hotel.orm.dao.custom.RoomDAO;
import lk.ijse.hotel.orm.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private Session session;
    @Override
    public List<Room> loadAll() throws Exception {
        String sqlQuery="From Room ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Room room) throws Exception {
        return (String) session.save(room);
    }

    @Override
    public void update(Room room) throws Exception {
        session.update(room);

    }

    @Override
    public void delete(Room room) throws Exception {
        session.delete(room);
    }

    @Override
    public Room getObject(String id) throws Exception {
        return session.get(Room.class,id);

    }

    @Override
    public String generateID() throws Exception {
        Room room = null;
        try {
            String sqlQuery="FROM Reservation ORDER BY id DESC";
            Query query = session.createQuery(sqlQuery);
            query.setMaxResults(1);
            room = (Room) query.uniqueResult();
        }catch (Exception e){

        }

        String lastID=room.getId();

        if (lastID != null){
            int newRoomID=Integer.parseInt(lastID.replace("RM-",""))+1;
            return String.format("RM-%03d",newRoomID);
        }
        return "RM-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }
}
