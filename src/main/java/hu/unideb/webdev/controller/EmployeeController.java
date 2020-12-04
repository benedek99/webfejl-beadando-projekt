package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.EmployeeDto;
import hu.unideb.webdev.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
