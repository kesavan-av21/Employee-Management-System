package com.ems.project.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.project.dto.SalaryDto;
import com.ems.project.service.SalaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/salary")
@Tag(
    name = "Salary APIs",
    description = "API Operations related to managing Salary"
)
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @Operation(
        summary = "Add salary details",
        description = "Add salary details using the employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Salary details added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid salary data provided"),
        @ApiResponse(responseCode = "404", description = "Employee not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(
    		path = "/POST/{empNo}",
    	    		consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE  },
    	    	    produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<SalaryDto> addEmpSalaryUsingEmpNo(
        @Parameter(description = "Employee number of the employee to add the salary details")
        @PathVariable Long empNo,
        @RequestBody SalaryDto salDto
    ) {
        return salaryService.addEmpSalaryUsingEmpNo(empNo, salDto);
    }

    @Operation(
        summary = "Retrieve salary details",
        description = "Retrieve salary details greater than the given salary"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salaries retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No employees found with salary greater than given amount"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/by-salary/{salary}",
    				 produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<SalaryDto>> findEmpDetailsSalaryGreaterThan(
        @Parameter(description = "Minimum salary filter")
        @PathVariable double salary
    ) {
        return salaryService.findEmpDetailsSalGreaterthan(salary);
    }

    @Operation(
        summary = "Retrieve salary count",
        description = "Retrieve count of employees with salary greater than the given amount"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salary count retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/amount",
    				 produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Integer> salaryCountGreaterThan(
        @Parameter(description = "Salary amount filter")
        @RequestParam double salary
    ) {
        return salaryService.SalaryCountGreaterThan(salary);
    }

    @Operation(
        summary = "Update salary",
        description = "Update salary using the employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salary updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid salary data provided"),
        @ApiResponse(responseCode = "404", description = "Employee not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(
    		path = "/PUT/{empNo}",
    	    		consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE  },
    	    	    produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<SalaryDto> updateSalary(
        @Parameter(description = "Employee number to update salary")
        @PathVariable long empNo,
        @RequestBody SalaryDto dto
    ) {
        SalaryDto salaryDto = salaryService.updateSalary(empNo, dto);
        return ResponseEntity.ok(salaryDto);
    }

    @Operation(
        summary = "Retrieve salary details",
        description = "Retrieve salary details using the employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salary details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Salary not found for the employee"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/by-emp/{empNo}",
    				 produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<SalaryDto> getEmpSalaryUsingEmpNo(
        @Parameter(description = "Employee number to get salary details")
        @PathVariable long empNo
    ) {
        SalaryDto salDto = salaryService.getEmpSalaryUsingEmpNo(empNo);
        return ResponseEntity.ok(salDto);
    }

    @Operation(
        summary = "Retrieve all salary details",
        description = "Retrieve all salary details from the system"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salaries retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/salaries",
    				 produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<SalaryDto>> getAllSalaries() {
        List<SalaryDto> salDto = salaryService.getAllSalary();
        return ResponseEntity.ok(salDto);
    }

    @Operation(
        summary = "Delete salary details",
        description = "Delete salary details using the employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Salary deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Transactional
    @DeleteMapping("/DELETE/{empNo}")
    public ResponseEntity<String> deleteEmpSalaryUsingEmpNo(
        @Parameter(description = "Employee number to delete salary details")
        @PathVariable long empNo
    ) {
        salaryService.deleteEmpSalaryUsingEmpNo(empNo);
        return ResponseEntity.ok("Employee salary deleted successfully");
    }
}
