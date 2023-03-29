package lk.ijse.hotel.orm.bo.custom;

import lk.ijse.hotel.orm.bo.SuperBO;
import lk.ijse.hotel.orm.dto.UsersDTO;

import java.util.List;

public interface UsersBO extends SuperBO {
    List<UsersDTO> loadAll() throws Exception;
    boolean saveUsers(UsersDTO usersDTO) throws Exception;
    boolean updateUsers(UsersDTO usersDTO) throws Exception;
    boolean deleteUsers(UsersDTO usersDTO) throws Exception;
    String generateNextUserID() throws Exception;

}
