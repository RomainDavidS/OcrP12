package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.employee.EmployeeCreateDto;
import p12.ocr.prestataire.controller.dto.employee.EmployeeUpdateDto;
import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.service.employee.IEmployeeService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/find/{id}")
    public Employee findById(@PathVariable Long id){ return employeeService.findById( id );}

    @GetMapping("/all")
    public List<Employee> findAll(){return employeeService.findAll();}

    @GetMapping("/all/actif/{id}")
    public List<Employee> findAllActif(@PathVariable Long id){return employeeService.findAllActifBySite( id );}

    @GetMapping("/all/inactif/{id}")
    public List<Employee> findAllInactif(@PathVariable Long id){return employeeService.findAllInactifBySite( id );}

    @PostMapping("/save")
    public Employee save(@DTO(EmployeeCreateDto.class) @RequestBody Employee employee){ return employeeService.save( employee );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(EmployeeUpdateDto.class) @RequestBody Employee employee){ employeeService.save( employee );}

}
