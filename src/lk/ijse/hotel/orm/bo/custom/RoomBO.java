package lk.ijse.hotel.orm.bo.custom;

import lk.ijse.hotel.orm.bo.SuperBO;
import lk.ijse.hotel.orm.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {

    List<RoomDTO> loadAllRoom() throws Exception;

    boolean saveRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(RoomDTO roomDTO) throws Exception;

    String generateNextRoomID() throws Exception;

}
