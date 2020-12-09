package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.EmployeeEntity;
import hu.unideb.webdev.dao.entity.SalaryEntity;
import hu.unideb.webdev.dao.entity.SalaryEntityId;
import hu.unideb.webdev.exceptions.SalaryNotFoundException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.model.Salary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryDaoImpl implements SalaryDao{

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Collection<Salary> readAll() {
        return StreamSupport.stream(salaryRepository.findAll().spliterator(),false)
                .limit(100)
                .map(entity -> new Salary(
                        entity.getSalaryEntityId().getEmployee().getId(),
                        entity.getSalary(),
                        entity.getSalaryEntityId().getFromDate(),
                        entity.getToDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createSalary(Salary salary) throws UnknownEmployeeException {

        SalaryEntity salaryEntity;

        Optional<EmployeeEntity> employeeEntity = StreamSupport.stream(employeeRepository.findAll().spliterator(),false).filter(
                entity -> {
                    return salary.getEmpNo()==(entity.getId());
                }).findFirst();

        if(!employeeEntity.isPresent()){
            throw new UnknownEmployeeException("Employee Not Found");
        }

        SalaryEntityId salaryEntityId = new SalaryEntityId();
        salaryEntityId.setEmployee(employeeEntity.get());
        salaryEntityId.setFromDate(salary.getFromDate());

        salaryEntity = SalaryEntity.builder()
                .salaryEntityId(salaryEntityId)
                .salary(salary.getSalary())
                .toDate(salary.getToDate())
                .build();

        try{
            salaryRepository.save(salaryEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteSalary(Salary salary) throws SalaryNotFoundException {

        Optional<SalaryEntity> salaryEntity= StreamSupport.stream(salaryRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return salary.getEmpNo() == entity.getSalaryEntityId().getEmployee().getId() && salary.getFromDate().equals(entity.getSalaryEntityId().getFromDate());
                }
        ).findFirst();

        if(!salaryEntity.isPresent()){
            throw new SalaryNotFoundException("Salary not found");
        }
        try{
            salaryRepository.delete(salaryEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateSalary(Salary salary) throws SalaryNotFoundException {
        Optional<SalaryEntity> salaryEntity= StreamSupport.stream(salaryRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return salary.getEmpNo() == entity.getSalaryEntityId().getEmployee().getId() && salary.getFromDate().equals(entity.getSalaryEntityId().getFromDate());
                }
        ).findFirst();

        if(!salaryEntity.isPresent()){
            throw new SalaryNotFoundException("Salary not found");
        }

        salaryEntity.get().setSalary(salary.getSalary());
        salaryEntity.get().setToDate(salary.getToDate());

        try{
            salaryRepository.save(salaryEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }
}