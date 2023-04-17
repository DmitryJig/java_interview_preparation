package org.example.hw5Hibernate;

import org.example.hw5Hibernate.dao.StudentDao;
import org.example.hw5Hibernate.dao.StudentDaoImpl;
import org.example.hw5Hibernate.model.Student;
import org.hibernate.Session;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.fullDatabase();
        StudentDao studentDao = new StudentDaoImpl();

        // создал студента, распечатал его, удалил, потом снова попробовал распечатать, нет его после удаления, значит удаление работает
        Student student1 = new Student(null, "firstStudent", 5);
        studentDao.saveOrUpdateStudent(student1);  // create
        studentDao.findStudentById(1L).ifPresent(System.out::println); // Student{id=1, name='firstStudent', mark=5}
        studentDao.deleteStudent(student1);         // delete
        System.out.println(studentDao.findStudentById(1L)); // empty after delete


        List<Student> students = main.createListStudents(10);
        studentDao.savaAllStudents(students);
        List<Student> all = studentDao.findAll();
        all.forEach(System.out::println);

        // достал студента из коллекции, поменял ему имя, сохранил, запросил из базы, распечатал, имя студента изменилось, все ок
        Student studentForChangeName = all.get(2);
        Long id = studentForChangeName.getId();
        studentForChangeName.setName("Boris");
        studentDao.saveOrUpdateStudent(studentForChangeName);
        studentDao.findStudentById(id).ifPresent(System.out::println);


        HibernateUtil.shutdown();
    }

    public void fullDatabase(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String sql = Files.lines(Paths.get("src/main/java/org/example/hw5Hibernate/init.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> createListStudents(int count){
        Random random = new Random();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            students.add(new Student(null, "student" + i, random.nextInt(6)));
        }
        return students;
    }
}
