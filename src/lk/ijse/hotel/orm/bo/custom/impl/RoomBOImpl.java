package lk.ijse.hotel.orm.bo.custom.impl;

import lk.ijse.hotel.orm.bo.custom.RoomBO;
import lk.ijse.hotel.orm.dao.DAOFactory;
import lk.ijse.hotel.orm.dao.custom.RoomDAO;
import lk.ijse.hotel.orm.dto.RoomDTO;
import lk.ijse.hotel.orm.entity.Room;
import lk.ijse.hotel.orm.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Rooms);

    private Session session;
    @Override

    public List<RoomDTO> loadAllRoom() throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        roomDAO.setSession(session);
        List<Room> rooms = roomDAO.loadAll();
        List<RoomDTO> roomDTOS=new ArrayList<>();

        for(Room room: rooms){
            roomDTOS.add(
                    new RoomDTO(
                            room.getId(),
                            room.getType(),
                            room.getKeyMoney(),
                            room.getQty()
                    )
            );
        }

        return roomDTOS;

    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {

        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomDAO.setSession(session);
            roomDAO.save(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();

            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
        }

        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {


        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomDAO.setSession(session);
            roomDAO.update(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {


        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomDAO.setSession(session);
            roomDAO.delete(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
                    )
            );
            transaction.commit();
            session.close();

            return true;

        }catch (Exception e){
            transaction.rollback();
            session.close();
        }

        return false;
    }

    @Override
    public String generateNextRoomID() throws Exception {
        return roomDAO.generateID();
    }
}
