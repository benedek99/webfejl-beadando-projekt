package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.EmployeeDto;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    }
}
