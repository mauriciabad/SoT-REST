package fontys.sot.rest.service.model;

import java.util.ArrayList;
import java.util.List;

public class AllStudents {
    private List<Student> students = new ArrayList<>();

    public List<Student> getAll() {
        return students;
    }

    public int total() {
        return students.size();
    }

    public Student get(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean exists(int id){
        return this.get(id) != null;
    }

    public void remove(int id){
        students.remove(this.get(id));
    }

    public void add(Student student) {
        Student oldStudent = this.get(student.getId());
        if (oldStudent != null){
            oldStudent.setName(student.getName());
        }else{
            students.add(student);
        }
    }
}
