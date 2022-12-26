package com.cydeo.controller;

import com.cydeo.client.WeatherClient;
import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.AddressService;
import com.cydeo.service.ParentService;
import com.cydeo.service.StudentService;
import com.cydeo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ParentService parentService;
    private final AddressService addressService;
    private final WeatherClient weatherClient;

    public SchoolController(StudentService studentService, TeacherService teacherService, ParentService parentService, AddressService addressService, WeatherClient weatherClient) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.parentService = parentService;
        this.addressService = addressService;
        this.weatherClient = weatherClient;
    }

    @GetMapping("/teachers")
    public ResponseEntity getTeachers(){
        return ResponseEntity.ok(teacherService.findAll());

    }

    @GetMapping("/students")
        public ResponseEntity<ResponseWrapper> getStudents(){
            return ResponseEntity.ok(new ResponseWrapper(studentService.findAll(),"Students are successfully retrieved"));
        }

    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> getParents(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("parents","returned").body(new ResponseWrapper(parentService.findAll(),HttpStatus.OK,"Parents are successfully retrieved"));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> getAddressById(@PathVariable("id") Long id) throws Exception {
       return ResponseEntity.ok(new ResponseWrapper(addressService.findById(id),"Address " + id + " is successfully retrieved"));

    }

    @PutMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> updateAddressById(@PathVariable("id") Long id,@RequestBody AddressDTO addressDTO) throws Exception {
        addressDTO.setId(id);
        addressService.update(addressDTO);
        return ResponseEntity.ok(new ResponseWrapper(addressService.findById(addressDTO.getId()),"Address " + addressDTO.getId() + " is successfully updated"));

    }
}
