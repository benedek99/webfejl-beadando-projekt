package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.DepartmentDto;
import hu.unideb.webdev.model.Department;
import hu.unideb.webdev.service.DepartmentService;
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
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping("/department/list")
    public  Collection<DepartmentDto> listDepartments(){
        return  service.readAll()
                .stream()
                .map(model -> DepartmentDto.builder()
                    .deptNo(String.valueOf(model.getDeptNo()))
                    .deptName(model.getDeptName())
                    .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/department/record")
    public void recordDepartment(@RequestBody DepartmentDto requestDto){
        try {
            service.createDepartment(new Department(
                    requestDto.getDeptNo(),
                    requestDto.getDeptName()
            ));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/department/delete")
    public void deleteDepartment(@RequestBody DepartmentDto requestDto){
        try {
            service.deleteDepartment(new Department(
                    requestDto.getDeptNo(),
                    requestDto.getDeptName()
            ));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/department/update")
    public void updateDepartment(@RequestBody DepartmentDto requestDtoOld, DepartmentDto requestDtoNew){
        try{
            service.updateDepartment(new Department(
                    requestDtoOld.getDeptNo(),
                    requestDtoOld.getDeptName()),
                    new Department(
                    requestDtoNew.getDeptNo(),
                    requestDtoNew.getDeptName()));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
