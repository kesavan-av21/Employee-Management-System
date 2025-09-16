package com.ems.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.project.dto.DeptDto;
import com.ems.project.dto.DeptUpdateDto;
import com.ems.project.entity.Dept;
import com.ems.project.service.DepartmentsSerive;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/dept")
@Tag(
    name = "Departments API's",
    description = "API Operations related to managing departments"
)
public class DepartmentsController {

    private final DepartmentsSerive departmentsSerive;

    public DepartmentsController(DepartmentsSerive departmentsSerive) {
        this.departmentsSerive = departmentsSerive;
    }

    @Operation(
        summary = "Add departments",
        description = "Adding new department details"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Department created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid department data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(
    		path = "/POST/depts",
    				consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
    	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Dept> addDeptNames(@RequestBody DeptDto deptDto) {
        Dept dept = departmentsSerive.addDepts(deptDto);
        if (dept != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dept);
        }
        return ResponseEntity.ok(dept);
    }

    @Operation(
        summary = "Update departments",
        description = "Update department names"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Department updated successfully"),
        @ApiResponse(responseCode = "404", description = "Department not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(
    		path = "/UPDATE",
    		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Dept> updateDepts(@RequestBody DeptUpdateDto deptDto) {
        return ResponseEntity.ok(departmentsSerive.updateDepts(deptDto));
    }

    @Operation(
        summary = "Get departments",
        description = "Retrieve all department details"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Departments retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No departments found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/alldepts",
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<DeptDto>> getDeptNames() {
        return ResponseEntity.ok(departmentsSerive.findAll());
    }

    @Operation(
        summary = "Get department by ID",
        description = "Retrieve department details by ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Department retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Department not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/{id}",
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DeptDto> getDeptName(
        @Parameter(description = "Department ID") 
        @PathVariable long id
    ) {
        DeptDto deptDto = departmentsSerive.findById(id);
        return ResponseEntity.ok(deptDto);
    }

    @Operation(
        summary = "Delete department by ID",
        description = "Delete department details by ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Department deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Department not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<String> deleteDept(
        @Parameter(description = "Department ID to delete") 
        @PathVariable long id
    ) {
        departmentsSerive.deleteById(id);
        return ResponseEntity.ok("Department detail deleted successfully");
    }
}
