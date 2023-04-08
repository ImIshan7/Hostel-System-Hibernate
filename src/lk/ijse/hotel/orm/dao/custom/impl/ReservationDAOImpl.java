package lk.ijse.hotel.orm.dao.custom.impl;

import lk.ijse.hotel.orm.dao.custom.ReservationDAO;
import lk.ijse.hotel.orm.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private Session session;
    @Override
    public List<Reservation> loadAll() throws Exception {
        try{
            String sqlQuery="From Reservation ";
            Query query = session.createQuery(sqlQuery);
            List list = query.list();
            session.close();
            return list;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String save(Reservation reservation) throws Exception {
        return (String) session.save(reservation);
    }

    @Override
    public void update(Reservation reservation) throws Exception {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) throws Exception {
        session.delete(reservation);
    }

    @Override
    public Reservation getObject(String id) throws Exception {
        return session.get(Reservation.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sql="FROM Reservation ORDER BY id DESC";
        Reservation reservation= (Reservation) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (reservation!=null){
            String lastId=reservation.getResId();
            int newCustomerId=Integer.parseInt(lastId.replace("RE0-",""))+1;
            return String.format("RE0-%03d",newCustomerId);
        }
        return "RE0-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;

    }

    @Override
    public boolean changeCheckBOXValue(String id, String status) {
        String hql="update Reservation r set r.status=:sts Where r.resId=:rid";
        Query query=session.createQuery(hql);
        query.setParameter("sts",status);
        query.setParameter("rid",id);
        int value= query.executeUpdate();
        return value>=0;
    }
}
