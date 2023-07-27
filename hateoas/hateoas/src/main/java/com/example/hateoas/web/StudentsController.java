package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.model.entity.OrderEntity;
import com.example.hateoas.model.entity.StudentEntity;
import com.example.hateoas.model.mapping.StudentMapper;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    // WARNING: Normally we never inject repos in the controller
    // we do this just for test and fun here
    public StudentsController(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> allStudents = studentRepository
                .findAll()
                .stream()
                .map(studentMapper::mapEntityToDTO)
                .map(dto -> EntityModel.of(dto, createStudentLinks(dto)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders(@PathVariable("id") Long id) {
       StudentEntity student = studentRepository
                .findById(id).orElseThrow();

      List<EntityModel<OrderDTO>> orders = student.getOrders().stream()
               .map(this::map)
               .map(EntityModel::of)
              .collect(Collectors.toList());

      return ResponseEntity.ok(CollectionModel.of(orders));
    }

    private OrderDTO map(OrderEntity orderEntity) {
        return new OrderDTO().setId(orderEntity.getId()).setStudentId(orderEntity.getStudent().getId())
                .setCourseId(orderEntity.getCourse().getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentsById(@PathVariable("id") Long id) {
        StudentDTO student = studentRepository
                .findById(id)
                .map(studentMapper::mapEntityToDTO)
                .orElseThrow();

        return ResponseEntity.ok(EntityModel.of(student, createStudentLinks(student)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(@PathVariable Long id, StudentDTO studentDTO) {
        //IMPLEMENTATION NOT IMPORTANT for the demo

        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLinks(StudentDTO studentDTO) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentsController.class)
                .getStudentsById(studentDTO.getId())).withSelfRel();
        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentsController.class)
                .update(studentDTO.getId(), studentDTO)).withRel("update");
        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentsController.class)
                .getOrders(studentDTO.getId())).withRel("orders");
        result.add(orderLink);

        return result.toArray(new Link[0]);
    }
}
