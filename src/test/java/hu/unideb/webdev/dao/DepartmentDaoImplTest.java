package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.DepartmentAlreadyExistsException;
import hu.unideb.webdev.exceptions.UnknownDepartmentException;
import hu.unideb.webdev.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentDaoImplTest {

    @Spy
    @InjectMocks
    private DepartmentDaoImpl dao;
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testCreateDepartment() throws UnknownDepartmentException, DepartmentAlreadyExistsException{

        dao.createDepartment(getDepartment());

        verify(departmentRepository, times(1)).save(any());

    }

    private Department getDepartment(){
        return new Department(
                "d015",
                "IT"
        );
    }


}
