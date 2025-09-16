package com.ems.project.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.project.dto.EmpDeptDetailsDto;
import com.ems.project.service.EmpDeptDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/empdeptdetails")
@Tag(
    name = "EmpDeptDetails APIs",
    description = "API Operations related to managing employee-department mappings"
)
public class EmpDeptDetailsController {

    private final EmpDeptDetailsService empDeptDetailsService;

    public EmpDeptDetailsController(EmpDeptDetailsService empDeptDetailsService) {
        this.empDeptDetailsService = empDeptDetailsService;
    }

    @Operation(
        summary = "Add employee department mapping",
        description = "Add department mapping details for an employee"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mapping created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(
    		path = "/POST",
    	    		consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE  },
    	    	    produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EmpDeptDetailsDto> addEmpDeptDetails(@RequestBody EmpDeptDetailsDto dto) {
        return ResponseEntity.ok(empDeptDetailsService.addEmpDeptDetails(dto));
    }

    @Operation(
        summary = "Get departments by employee",
        description = "Fetch department details mapped to a given employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mappings retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No mappings found for employee"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/{empNo}",
    				produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<EmpDeptDetailsDto>> getEmpDeptDetails(
        @Parameter(description = "Employee number") @PathVariable long empNo
    ) {
        List<EmpDeptDetailsDto> details = empDeptDetailsService.getDeptDetails(empNo);
        return ResponseEntity.ok(details);
    }

    @Operation(
        summary = "Delete employee department mapping",
        description = "Delete mapping details using employee number and department ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mapping deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Mapping not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(
    		path = "/DELETE/{empNo}/{deptId}")
    public ResponseEntity<String> deleteEmpDeptDetails(
        @Parameter(description = "Employee number") @PathVariable long empNo,
        @Parameter(description = "Department number") @PathVariable long deptId
    ) {
        empDeptDetailsService.deleteDeptDetails(empNo, deptId);
        return ResponseEntity.ok("Mapping deleted successfully");
    }

    @Operation(
        summary = "Get all employee department mappings",
        description = "Fetch all employee-department mappings in the system"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mappings retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No mappings found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/all",
    				produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<EmpDeptDetailsDto>> getEmpDeptDetails() {
        List<EmpDeptDetailsDto> details = empDeptDetailsService.getAllMapping();
        return ResponseEntity.ok(details);
    }

    @Operation(
        summary = "Get all employees in a department",
        description = "Fetch employee mappings for a given department ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mappings retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No employees found for department"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/dept/{deptId}",
    				produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<EmpDeptDetailsDto>> getEmployeesByDept(
        @Parameter(description = "Department ID") @PathVariable long deptId
    ) {
        return ResponseEntity.ok(empDeptDetailsService.getEmployeesByDept(deptId));
    }
}
