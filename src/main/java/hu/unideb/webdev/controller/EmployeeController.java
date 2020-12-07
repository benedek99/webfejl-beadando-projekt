package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.EmployeeDto;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.exceptions.UnknownGenderException;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World", required = false) String name){
        return String.format("Hello %s!", name);
    }

    @ApiOperation("Say Hello from Path")
    @GetMapping("/hello/{name}")
    public String helloPath(@PathVariable("name") String name){
        return String.format("Hello %s!", name);
    }

    @GetMapping
    public Collection<EmployeeDto> listEmployees(){
        return service.getAllEmployee()
                .stream()
                .map(model -> EmployeeDto.builder()
                    .birth_date(model.getBirth_date())
                    .first_name(model.getFirst_name())
                    .last_name(model.getLast_name())
                    .gender(model.getGender())
                    .hire_date(model.getHire_date())
                    .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/employee")
    public void record(@RequestBody EmployeeDto requestDto){
        try {
            service.recordEmployee(new Employee(
                    requestDto.getBirth_date(),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getGender(),
                    requestDto.getHire_date()
                    ));
        }
        catch (UnknownGenderException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/employee")
    public void delete(@RequestBody EmployeeDto requestDto){
        try {
            service.deleteEmployee(new Employee(
                    requestDto.getBirth_date(),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getGender(),
                    requestDto.getHire_date()
            ));
        }
        catch (UnknownEmployeeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
