package com.ideas2it.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;

/**
 * EmployeeDataAccessObject contains the methods to perform the datebase
 * operations for Employee Details
 *
 * @version 1.0 12 Aug 2022
 *
 * @author Ramasamy R M
 */
public class EmployeeDAO {

    private SessionFactory sessionFactory;
    /**
     * <p>
     * Insert Trainer Details
     * </p>
     *
     * @param Trainer trainer
     * @return
     */
    public Integer insertTrainer(Trainer trainer) {
	Integer rowsAffected = 0;
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	rowsAffected = (Integer) session.save(trainer);
	transaction.commit();
	session.close();
	return rowsAffected;
    }

    /**
     * <p>
     * Get all Trainer details 
     * </p>
     *
     * @param
     * @return List<Trainer> trainers
     */
    public List<Trainer> retrieveAllTrainers() throws HibernateException {
	List<Trainer> trainers = new ArrayList<Trainer>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
        String query = "FROM Trainer";
        trainers = session.createQuery(query).list();
        session.close();
	return trainers;
    }

    /**
     * <p>
     * Get the particular Trainer detail 
     * </p>
     *
     * @param Integer id
     * @return List<Trainer> trainers
     */
    public Trainer retrieveTrainerById(Integer id) throws HibernateException {
	Trainer trainer = new Trainer();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	trainer = (Trainer) session.get(Trainer.class, id);
        session.close();
	return trainer;
    }

    /**
     * <p>
     * Update the particular trainer detail
     * </p>
     *
     * @param Trainer trainer
     * @return void
     */
    public void updateTrainerById(Trainer trainer) throws HibernateException {
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	if (!trainer.isEmpty()) {
		session.merge(trainer);
	}
	transaction.commit();
	session.close();
    }

    /**
     * <p>
     * Delete the particular Trainer detail 
     * </p>
     *
     * @param Integer id
     * @return void
     */
    public void deleteTrainerById(Integer id) throws HibernateException {
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	Trainer trainer = (Trainer) session.get(Trainer.class, id);
	session.delete(trainer);
	transaction.commit();
        session.close();	
    }

    /**
     * <p>
     * Insert Trainee Details 
     * </p>
     *
     * @param Trainee trainee
     * @return void
     */
    public Integer insertTrainee(Trainee trainee) throws HibernateException {
	Integer rowsAffected = 0;
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	rowsAffected = (Integer) session.save(trainee);
	transaction.commit();
	System.out.println("Session Executed Successfully");
	session.close();
	return rowsAffected;
    }

    /**
     * <p>
     * Get all Trainee details
     * </p>
     *
     * @param
     * @return List<Trainee> trainees
     */
    public List<Trainee> retrieveAllTrainees() throws HibernateException {
	List<Trainee> trainees = new ArrayList<Trainee>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
        String query = "FROM Trainee";
        trainees = session.createQuery(query).list();
        session.close();
	return trainees;
    }

    /**
     * <p>
     * Get the particular Trainee detail 
     * </p>
     *
     * @param Integer id
     * @return List<Trainee> trainees
     */
    public Trainee retrieveTraineeById(Integer id) throws HibernateException {
	Trainee trainee = new Trainee();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	trainee = (Trainee) session.get(Trainee.class, id);
        session.close();
	return trainee;
    }

    /**
     * <p>
     * Update the particular trainee detail by id
     * </p>
     *
     * @param Trainee trainee
     * @return void
     */
    public void updateTraineeById(Trainee trainee) throws HibernateException {
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	if (!trainee.isEmpty()) {
	    session.merge(trainee);
	}
	transaction.commit();
        session.close();
    }

    /**
     * <p>
     * Delete the particular Trainee detail 
     * </p>
     *
     * @param Integer id
     * @return void
     */
    public void deleteTraineeById(Integer id) throws HibernateException {
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	Trainee trainee = (Trainee) session.get(Trainee.class, id);
	session.delete(trainee);
	transaction.commit();
        session.close();
    }

    /**
     * <p>
     * Search the particular Trainer detail by FirstName
     * </p>
     *
     * @param String detail
     * @return List<Trainer> trainers
     */
    public List<Trainer> searchTrainerByFirstName(String firstName) {
	List<Trainer> trainers = new ArrayList<Trainer>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	String hql = "from Trainer where firstName like :firstName";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", "%" + firstName + "%");
        trainers = query.list();
	session.close();
	return trainers;
    }

    /**
     * <p>
     * Search the particular Trainer detail by LastName
     * </p>
     *
     * @param String detail
     * @return List<Trainer> trainers
     */
    public List<Trainer> searchTrainerByLastName(String lastName) throws HibernateException {
	List<Trainer> trainers = new ArrayList<Trainer>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	String hql = "from Trainer where lastName like :lastName";
        Query query = session.createQuery(hql);
        query.setParameter("lastName", "%" + lastName + "%");
        trainers = query.list();
	session.close();
	return trainers; 
    }

    /**
     * <p>
     * Search the particular Trainee detail by FirstName
     * </p>
     *
     * @param String detail
     * @return List<Trainee> trainees
     */
    public List<Trainee> searchTraineeByFirstName(String firstName) {
 	List<Trainee> trainees = new ArrayList<Trainee>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	String hql = "from Trainee where firstName like :firstName";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", "%" + firstName + "%");
        trainees = (List<Trainee>) query.list();
	session.close();
	return trainees;
    }

    /**
     * <p>
     * Search the particular Trainee detail by LastName
     * </p>
     *
     * @param String detail
     * @return List<Trainee> trainees
     */
    public List<Trainee> searchTraineeByLastName(String lastName) throws HibernateException {
	List<Trainee> trainees = new ArrayList<Trainee>();
	sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	String hql = "from Trainee where lastName like :lastName";
        Query query = session.createQuery(hql);
        query.setParameter("lastName", "%" + lastName + "%");
        trainees = (List<Trainee>) query.list();
	return trainees;
    }
}
