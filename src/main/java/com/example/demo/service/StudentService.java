package com.example.demo.service;

import com.example.demo.repository.StudentRepository;
import com.example.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student with email = " + studentOptional.get().getEmail() + " is exist!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id : " + id
                    + " doesn't exists");
        }
        studentRepository.deleteById(id);
    }
    @Transactional
    public void editStudent(Long id, String name, String email ) {

        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new IllegalStateException("Student with id: " + id
                    + " doesn't exists");
        }

        if(email != null){
            student.get().setEmail(email);
        }

        if(name != null){
            student.get().setName(name);
        }


    }
}

