package com.ems.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.project.dto.EmpDto;
import com.ems.project.dto.EmpMainDetailsDto;
import com.ems.project.service.EmpService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/emp")
@Tag(
    name = "Employee API's",
    description = "API Operations related to managing employees"
)
public class EmpController {

    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @Operation(
        summary = "Adding employee details",
        description = "Adding only employee name, age, email, phone number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(path = "/POST/emp")
    public ResponseEntity<EmpMainDetailsDto> addEmployees(
        @RequestBody EmpMainDetailsDto empDto
    ) {
        EmpMainDetailsDto empdto = empService.saveAll(empDto);
        return ResponseEntity.ok(empdto);
    }

    @Operation(
        summary = "Updating employee details",
        description = "Updating only employee name, age, email, phone number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
        @ApiResponse(responseCode = "204", description = "Employee not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping(
        path = "/PUT/{empNo}",
        consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<EmpDto> updateEmpById(
        @Parameter(description = "EmpNo")
        @PathVariable("empNo") long id,
        @RequestBody EmpDto empDto
    ) {
        EmpDto emp = empService.updateEmpById(id, empDto);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Finding employee details",
        description = "Retrieving employee all tables details using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee found"),
        @ApiResponse(responseCode = "204", description = "Employee not found")
    })
    @GetMapping(path = "/GET/{empNo}")
    public ResponseEntity<EmpDto> getEmpAllDetailsById(
        @Parameter(description = "EmpNo of the employee to be retrieved")
        @PathVariable("empNo") long id
    ) {
        EmpDto emp = empService.getEmpById(id);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Retrieving employees details",
        description = "Retrieving all employees details from employee table"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No employees found")
    })
    @GetMapping("/GET/emp")
    public ResponseEntity<List<EmpDto>> getAllEmpDetails() {
        List<EmpDto> list = empService.getAllEmpDetails();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    @Operation(
        summary = "Getting employee details",
        description = "Finding only employee name, age, email, phone number using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee found"),
        @ApiResponse(responseCode = "204", description = "Employee not found")
    })
    @GetMapping("/GET/emp/details/{empNo}")
    public ResponseEntity<EmpDto> getEmpEmpTableDetails(
        @Parameter(description = "EmpNo")
        @PathVariable("empNo") long id
    ) {
        EmpDto emp = empService.getEmpEmpTableDetails(id);
        if (emp != null) {
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Sorting employee details",
        description = "Sorting employee details using name, age, phone, email in asc or desc order, "
                    + "with pagination (page number and employee count per page)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees sorted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid sort parameters")
    })
    @GetMapping("/GET/emp/sort")
    public ResponseEntity<Page<EmpDto>> sortingEmp(
    	@Parameter(description = "Field")
        @RequestParam String field,
        @Parameter(description = "direction")
        @RequestParam String direction,
        @Parameter(description = "pageNo")
        @RequestParam int pageNo,
        @Parameter(description = "empCount")
        @RequestParam int empCount
    ) {
        return ResponseEntity.ok(empService.sortingEmp(field, direction, pageNo - 1, empCount));
    }

    @Operation(
        summary = "Sort employee by age",
        description = "Retrieve employees with age >= given age, with sorting direction"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid age or sort parameters")
    })
    @GetMapping("/GET/emp/sort/age/greater")
    public ResponseEntity<List<EmpDto>> sortEmpByAgeGreaterThanAgeByDir(
    	@Parameter(description = "direction")
        @RequestParam Long age,
        @Parameter(description = "direction")
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(empService.sortEmpByAgeGreaterThanAgeByDirection(age, direction));
    }

    @Operation(
        summary = "Sort employee by age",
        description = "Retrieve employees with age <= given age, with pagination and sorting"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid age or pagination parameters")
    })
    @GetMapping("/GET/emp/sort/age/less")
    public ResponseEntity<Page<EmpDto>> sortEmpByAgeLessThanAgeByDir(
    	@Parameter(description = "age")
        @RequestParam Long age,
        @Parameter(description = "pageNo")
        @RequestParam int pageNo,
        @Parameter(description = "empCount")
        @RequestParam int empCount,
        @Parameter(description = "direction")
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(empService.findByAgeLessThanEqual(age, direction, pageNo, empCount));
    }

    @Operation(
        summary = "Delete employee by empNo",
        description = "Delete employee details from the database using employee empNo"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/DELETE/emp/{empNo}")
    public ResponseEntity<String> deleteById(
        @Parameter(description = "EmpNo")
        @PathVariable("empNo") Long id
    ) {
        return empService.deleteById(id);
    }

    @Operation(
        summary = "Delete all employees",
        description = "Delete all employee details from the database"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "All employees deleted successfully")
    })
    @DeleteMapping("/DELETE/emp")
    public ResponseEntity<String> deleteAllEmployee() {
        empService.deleteAllEmployees();
        return ResponseEntity.ok("All employees deleted successfully");
    }
}
