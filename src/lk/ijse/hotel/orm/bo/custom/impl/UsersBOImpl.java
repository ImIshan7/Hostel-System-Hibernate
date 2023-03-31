package lk.ijse.hotel.orm.bo.custom.impl;

import lk.ijse.hotel.orm.bo.custom.UsersBO;
import lk.ijse.hotel.orm.dao.DAOFactory;
import lk.ijse.hotel.orm.dao.custom.UsersDAO;
import lk.ijse.hotel.orm.dto.UsersDTO;
import lk.ijse.hotel.orm.entity.Users;
import lk.ijse.hotel.orm.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UsersBOImpl implements UsersBO {

    UsersDAO userDAO = (UsersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Users);
    private Session session;
    @Override
    public List<UsersDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();

        List<Users> users = userDAO.loadAll();
        List<UsersDTO> usersDTOS=new ArrayList<>();

        for (Users users1:users) {
            usersDTOS.add(
                    new UsersDTO(
                            users1.getId(),
                            users1.getUserName(),
                            users1.getPassword(),
                            users1.getContact(),
                            users1.getType(),
                            users1.isEnabled()
                    )
            );
        }

        return usersDTOS;
    }

    @Override
    public boolean saveUsers(UsersDTO usersDTO) throws Exception {

        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userDAO.setSession(session);
            userDAO.save(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
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
    public boolean updateUsers(UsersDTO usersDTO) throws Exception {

        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            userDAO.update(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
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
    public boolean deleteUsers(UsersDTO usersDTO) throws Exception {
        session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try {
            userDAO.setSession(session);
            userDAO.delete(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
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
    public String generateNextUserID() throws Exception {
        return userDAO.generateID();
    }
}
