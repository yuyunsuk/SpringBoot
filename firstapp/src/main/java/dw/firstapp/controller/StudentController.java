package dw.firstapp.controller;

import dw.firstapp.model.Student;
import dw.firstapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // @Autowired란 스프링 컨테이너에 등록한 빈에게 의존관계주입이 필요할 때, DI(의존성 주입)을 도와주는 어노테이션이다.
    // Bean 의 대상이 되는 것 => 애플리케이션 컨텍스트(Application Context)에서 선택
    // 어노테이션 없는것은 대상이 안됨
    @Autowired
    StudentService studentService; // DI 의존성(어떤 모듈 또는 객체가 다른 모듈 또는 객체를 사용하거나 그것으로 부터 영향을 받는 것) 주입
    // 객체에 의존성(Dependency)을 주입(Injection)하는 것
    // 애플리케이션 컨텍스트(Application Context) 가 의존성을 준다.

    @GetMapping("/student")
    public Student getStudent() {
        return new Student("Tom", "Smith");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Tom","Smith"));
        students.add(new Student("John","Long"));
        students.add(new Student("Steve","white"));
        students.add(new Student("Leon","Red"));
        students.add(new Student("Mike","Tyson"));
        return students;
    }

    @GetMapping("/student/{firstName}/{lastName}")
    // http://localhost:8080/student/Tom/Smith
    public Student studentPathVariable(@PathVariable String firstName,
                                       @PathVariable String lastName) {
        return new Student(firstName, lastName);
    }

    @GetMapping("/student/query")
    // http://localhost:8080/student/query?firstName=Tom&lastName=Smith
    public Student studentRequestParam(@RequestParam String firstName,
                                       @RequestParam String lastName) {
        return new Student(firstName, lastName);
    }

    @PostMapping("/student/post")
    // http://localhost:8080/student/post => postman 에서 JSON 으로 실행
    public Student studentPost(@RequestBody Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return new Student(student.getFirstName(), student.getLastName());
    }

    @GetMapping("/student/score/{firstName}/{lastName}")
    public int getStudentScore(@PathVariable String firstName,
                               @PathVariable String lastName) {
        Student student = new Student(firstName, lastName);
        //StudentService 는 Bean 이므로 인스턴스화 하지 않는다.
        //StudentService studentService = new StudentService();
        return studentService.getStudentScore(student);
    }

}