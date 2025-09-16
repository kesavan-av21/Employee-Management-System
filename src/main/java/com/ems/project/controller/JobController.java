package com.ems.project.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.project.dto.JobDto;
import com.ems.project.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/job")
@Tag(
    name = "Job APIs",
    description = "API Operations related to managing jobs"
)
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    
    @Operation(
        summary = "Add Job",
        description = "Add a new job to the job table"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Job created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(
    		path = "/POST",
    	    		consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE  },
    	    	    produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<JobDto> addJobDetails(@RequestBody JobDto jobDto) {
        return jobService.addJobDetails(jobDto);
    }

    
    @Operation(
        summary = "Delete All Jobs",
        description = "Delete all jobs from the job table"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "All jobs deleted successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/DELETE")
    public ResponseEntity<String> deleteAll() {
        jobService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    
    @Operation(
        summary = "Get Job by Employee Number",
        description = "Retrieve job details using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Job details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Job not found for the given employee number"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/jobdetails/{empNo}",
    				produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<JobDto> getEmpJobDetailsUsingEmpNo(
        @Parameter(description = "Employee number to get the job detail")
        @PathVariable Long empNo
    ) {
        return jobService.getEmpJobDetailsUsingEmpNo(empNo);
    }

    
    @Operation(
        summary = "Get All Jobs",
        description = "Retrieve all jobs from the job table"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Jobs retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(
    		path = "/GET/jobs",
    				produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<JobDto>> gettingAllJobs() {
        List<JobDto> jobDto = jobService.getAllJobs();
        return ResponseEntity.ok(jobDto);
    }

    
    @Operation(
        summary = "Delete Job by Employee Number",
        description = "Delete a job using the employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Job deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Job not found for the given employee number"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/DELETE/{empNo}")
    public ResponseEntity<String> deleteJobByEmpNo(
        @Parameter(description = "Employee number to delete the job")
        @PathVariable long empNo
    ) {
        jobService.deleteJobByEmpNo(empNo);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
