package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.SalaryDto;
import hu.unideb.webdev.model.Salary;
import hu.unideb.webdev.service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService service;

    @GetMapping("/salary/list")
    public Collection<SalaryDto> listSalaries(){
        return service.readAll()
                .stream()
                .map(model ->SalaryDto.builder()
                        .empNo(String.valueOf(model.getEmpNo()))
                        .salary(String.valueOf(model.getSalary()))
                        .fromDate(model.getFromDate().toString())
                        .toDate(model.getToDate().toString())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/salary/record")
    public  void recordSalary(@RequestBody SalaryDto requestDto){
        try{
            service.createSalary(new Salary(
                    Integer.parseInt(requestDto.getEmpNo()),
                    Integer.parseInt(requestDto.getSalary()),
                    Timestamp.valueOf(requestDto.getFromDate()),
                    Timestamp.valueOf(requestDto.getToDate())
            ));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}