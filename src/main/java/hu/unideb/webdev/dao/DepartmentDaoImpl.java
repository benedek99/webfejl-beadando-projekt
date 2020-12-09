package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.DepartmentEntity;
import hu.unideb.webdev.exceptions.DepartmentAlreadyExistsException;
import hu.unideb.webdev.exceptions.UnknownDepartmentException;
import hu.unideb.webdev.model.Department;
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
public class DepartmentDaoImpl implements DepartmentDao{

    private final DepartmentRepository departmentRepository;

    @Override
    public Collection<Department> readAll() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(),false)
                .map(entity -> new Department(
                        entity.getDepartmentNumber(),
                        entity.getDepartmentName()
                ))
                .limit(100)
                .collect(Collectors.toList());
    }

    @Override
    public void createDepartment(Department department) throws UnknownDepartmentException, DepartmentAlreadyExistsException {

        DepartmentEntity departmentEntity;

        departmentEntity = DepartmentEntity.builder()
                .departmentNumber(department.getDeptNo())
                .departmentName(department.getDeptName())
                .build();

        log.info("DepartmentEntity: {}", departmentEntity);

        if(departmentEntity.getDepartmentNumber().length()>4 || departmentEntity.getDepartmentNumber().length()==0){
            throw new UnknownDepartmentException(String.format("Invalid Department Number: %s", departmentEntity.getDepartmentNumber()));
        }

        Optional<DepartmentEntity> departmentEntitySearcher = StreamSupport.stream(departmentRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return department.getDeptNo().equals(entity.getDepartmentNumber()) || department.getDeptName().equals(entity.getDepartmentName());
                }
        ).findFirst();
        if (departmentEntitySearcher.isPresent()) {
            throw new DepartmentAlreadyExistsException(String.format("Department Already Exists: %s", department));
        }

        try{
            departmentRepository.save(departmentEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteDepartment(Department department) throws UnknownDepartmentException {

        Optional<DepartmentEntity> departmentEntity = StreamSupport.stream(departmentRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return department.getDeptNo().equals(entity.getDepartmentNumber());
                }
        ).findFirst();
        if (!departmentEntity.isPresent()) {
            throw new UnknownDepartmentException(String.format("Department Not Found: %s", department), department);
        }
        log.info("DepartmentEntity: {}", departmentEntity);
        departmentRepository.delete(departmentEntity.get());
    }

    @Override
    public void updateDepartment(Department departmentOld, Department departmentNew) throws UnknownDepartmentException, DepartmentAlreadyExistsException {
        Optional<DepartmentEntity> departmentEntity = StreamSupport.stream(departmentRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return departmentOld.getDeptNo().equals(entity.getDepartmentNumber()) && departmentOld.getDeptName().equals(entity.getDepartmentName());
                }
        ).findFirst();
        if (!departmentEntity.isPresent()) {
            throw new UnknownDepartmentException(String.format("Department Not Found: %s", departmentOld), departmentOld);
        }

        if(departmentNew.getDeptNo().length()>4 || departmentNew.getDeptNo().length()==0){
            throw new UnknownDepartmentException(String.format("Invalid Department Number: %s", departmentNew.getDeptName()));
        }


        Optional<DepartmentEntity> departmentEntitySearcher = StreamSupport.stream(departmentRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return (departmentNew.getDeptNo().equals(entity.getDepartmentNumber()) && !departmentNew.getDeptNo().equals(departmentOld.getDeptNo())) || (departmentNew.getDeptName().equals(entity.getDepartmentName()) && !departmentOld.getDeptName().equals(departmentNew.getDeptName()));
                }
        ).findFirst();
        if (departmentEntitySearcher.isPresent()) {
            throw new DepartmentAlreadyExistsException(String.format("Department Already Exists: %s", departmentNew.toString()));
        }


        departmentEntity.get().setDepartmentNumber(departmentNew.getDeptNo());
        departmentEntity.get().setDepartmentName(departmentNew.getDeptName());

        log.info("DepartmentEntity: {}", departmentEntity);

        try {
            departmentRepository.save(departmentEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }
}
