package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.AllStudents;
import fontys.sot.rest.service.model.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("students")
public class StudentsResources {

    private AllStudents students = new AllStudents();

    public StudentsResources() {
        students.add(new Student(0, "Joe Smith"));
        students.add(new Student(1, "Ann Johnsson"));
        students.add(new Student(2, "Miranda Winslet"));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Student> getStudentByQuery(@QueryParam("id") Integer id) {
        if (id != null) {
            if (students.exists(id)) {
                List<Student> filteredStudentList = new ArrayList<Student>();
                filteredStudentList.add(students.get(id));
                return filteredStudentList;
            } else {
                throw new RuntimeException("Student wit id " + id + " doesn't exist");
            }
        }else{
            return students.getAll();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createStudent(Student student) {
        if(!students.exists(student.getId())){
            students.add(student);
        }else{
            throw new RuntimeException("Student with id " + student.getId() + " already exist");
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void createStudent(@FormParam("id") int id, @FormParam("name") String name) {
        if(!students.exists(id)){
            students.add(new Student(id, name));
        }else{
            throw new RuntimeException("Student with id " + id + " already exist");
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateStudent(Student student) {
        if(students.exists(student.getId())){
            students.add(student);
            return Response.noContent().build();
        }else{
            return Response.serverError().entity("Student with id " + student.getId() + " doesn't exist").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getStudentByQuery(@PathParam("id") int id) {
        if (students.exists(id)) {
            return Response.ok().entity(students.get(id)).build();
        } else {
            return Response.serverError().entity("Student with id " + id + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteStudent(@PathParam("id") int id) {
         students.remove(id);
    }

    @GET
    @Path("hello")
    @Produces({MediaType.TEXT_PLAIN})
    public String sayHello() {
        return "Hello, your service works!";
    }

    @GET
    @Path("count")
    @Produces({MediaType.TEXT_PLAIN})
    public int getNumberOfStudents() {
        return students.total();
    }

    @GET
    @Path("first")
    @Produces({MediaType.APPLICATION_JSON})
    public Response GetFirstStudent() {
        if (students.exists(0)){
            return Response.ok().entity(students.get(0)).build();
        }else {
            return Response.serverError().entity("Student with id 0 doesn't exist").build();
        }
    }

}
