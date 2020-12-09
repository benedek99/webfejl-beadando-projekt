package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.SalaryDao;
import hu.unideb.webdev.exceptions.InvalidDatesException;
import hu.unideb.webdev.exceptions.InvalidSalaryException;
import hu.unideb.webdev.exceptions.SalaryNotFoundException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Salary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService{

    private final SalaryDao salaryDao;

    @Override
    public Collection<Salary> readAll() {
        return salaryDao.readAll();
    }

    @Override
    public void createSalary(Salary salary) throws UnknownEmployeeException, InvalidSalaryException, InvalidDatesException {

        if (salary.getSalary()<0){
            throw new InvalidSalaryException("Salary Must Be Positive");
        }
        if (salary.getFromDate().after(salary.getToDate())){
            throw new InvalidDatesException("Invalid Dates");
        }
        salaryDao.createSalary(salary);
    }

    @Override
    public void deleteSalary(Salary salary) throws UnknownEmployeeException, SalaryNotFoundException {
        salaryDao.deleteSalary(salary);
    }

    @Override
    public void updateSalary(Salary salary) throws SalaryNotFoundException, InvalidSalaryException, InvalidDatesException {
        if (salary.getSalary()<0){
            throw new InvalidSalaryException("Salary Must Be Positive");
        }
        if (salary.getFromDate().after(salary.getToDate())){
            throw new InvalidDatesException("Invalid Dates");
        }
        salaryDao.updateSalary(salary);
    }
}
