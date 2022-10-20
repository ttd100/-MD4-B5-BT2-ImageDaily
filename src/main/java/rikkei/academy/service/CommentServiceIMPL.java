package rikkei.academy.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class CommentServiceIMPL implements ICommentService {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findAll() {
        String queryStr = "select c from Comment as c where c.date = :date";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        query.setParameter("date", new Date(), TemporalType.DATE);
        return query.getResultList();
    }

    @Override
    public void save(Comment comment) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (comment.getId() != 0) {
                Comment comment1 = findById(comment.getId());
                comment1.setRate(comment.getRate());
                comment1.setAuthor(comment.getAuthor());
                comment1.setFeedback(comment.getFeedback());
                comment1.setLikeCount(comment.getLikeCount());
                comment1.setDate(comment.getDate());
            }
            session.saveOrUpdate(comment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Comment findById(int id) {
        String queryStr = "select c from Comment as c where c.id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}