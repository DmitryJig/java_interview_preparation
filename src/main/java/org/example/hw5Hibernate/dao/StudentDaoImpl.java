package org.example.hw5Hibernate.dao;

import org.example.hw5Hibernate.HibernateUtil;
import org.example.hw5Hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            students = session.createQuery("from Student")
                    .getResultList();
            session.getTransaction().commit();
        }
        return students;
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(student);
        }
    }

    @Override
    public void savaAllStudents(List<Student> students){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            students.forEach(session::save);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("delete from Student where id= :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        }
    }
}
