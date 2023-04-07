package lk.ijse.hotel.orm.dao.custom.impl;

import lk.ijse.hotel.orm.dao.custom.UsersDAO;
import lk.ijse.hotel.orm.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UsersDAOImpl implements UsersDAO {

    private Session session;
    @Override
    public List<Users> loadAll() throws Exception {
        String sqlQuery="From Users ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Users users) throws Exception {
        return (String) session.save(users);
    }

    @Override
    public void update(Users users) throws Exception {
        session.update(users);
    }

    @Override
    public void delete(Users users) throws Exception {
        session.delete(users);
    }

    @Override
    public Users getObject(String id) throws Exception {
        return session.get(Users.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sql="FROM Users ORDER BY id DESC";
        Users student= (Users) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (student!=null){
            String lastId=student.getId();
            int newCustomerId=Integer.parseInt(lastId.replace("U00-",""))+1;
            return String.format("U00-%03d",newCustomerId);
        }
        return "U00-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }
}
