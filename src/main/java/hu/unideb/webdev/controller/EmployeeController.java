package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.EmployeeDto;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
/*
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World", required = false) String name){
        return String.format("Hello %s!", name);
    }

    @ApiOperation("Say Hello from Path")
    @GetMapping("/hello/{name}")
    public String helloPath(@PathVariable("name") String name){
        return String.format("Hello %s!", name);
    }
*/
    @GetMapping("/employee/list")
    public Collection<EmployeeDto> listEmployees(){
        return service.getAllEmployee()
                .stream()
                .map(model -> EmployeeDto.builder()
                    .birth_date(model.getBirthDate().toString())
                    .first_name(model.getFirstName())
                    .last_name(model.getLastName())
                    .gender(model.getGender())
                    .hire_date(model.getHireDate().toString())
                    .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/employee/record")
    public void recordEmployee(@RequestBody EmployeeDto requestDto){
        try {
            service.recordEmployee(new Employee(
                    Timestamp.valueOf(requestDto.getBirth_date()),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getGender(),
                    Timestamp.valueOf(requestDto.getHire_date())
                    ));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/employee/delete")
    public void deleteEmployee(@RequestBody EmployeeDto requestDto){
        try {
            service.deleteEmployee(new Employee(
                    Timestamp.valueOf(requestDto.getBirth_date()),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getGender(),
                    Timestamp.valueOf(requestDto.getHire_date())
            ));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/employee/update")
    public void updateEmployee(@RequestBody EmployeeDto requestDtoOld, EmployeeDto requestDtoNew){
        try {
            service.updateEmployee(
                    new Employee(
                        Timestamp.valueOf(requestDtoOld.getBirth_date()),
                        requestDtoOld.getFirst_name(),
                        requestDtoOld.getLast_name(),
                        requestDtoOld.getGender(),
                        Timestamp.valueOf(requestDtoOld.getHire_date())),
                    new Employee(
                        Timestamp.valueOf(requestDtoNew.getBirth_date()),
                        requestDtoNew.getFirst_name(),
                        requestDtoNew.getLast_name(),
                        requestDtoNew.getGender(),
                        Timestamp.valueOf(requestDtoNew.getHire_date()))
            );
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
