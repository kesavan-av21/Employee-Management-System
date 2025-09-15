package com.ems.project.controller;

import java.util.List;

import com.ems.project.dto.AddressDto;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@Tag(
    name = "Address API's",
    description = "API Operations related to managing address"
)
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
        summary = "Address details",
        description = "Add address details of employee using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addresses added successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PostMapping("/POST/address/{empNo}")
    public ResponseEntity<List<AddressDto>> addEmpAddress(
        @Parameter(description = "Employee number to which addresses will be added")
        @PathVariable long empNo,
        @RequestBody List<AddressDto> addressDto
    ) {
        List<AddressDto> savedAddresses = addressService.addEmpAddressByEmpNo(empNo, addressDto);
        return ResponseEntity.ok(savedAddresses);
    }

    @Operation(
        summary = "Update address details",
        description = "Update address details of employee using employee number and address type"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address updated successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PutMapping("/PUT/update/{empNo}/{addressType}")
    public ResponseEntity<AddressDto> updateAddressByEmpNo(
        @Parameter(description = "Employee number of the address to update")
        @PathVariable long empNo,
        @Parameter(description = "Address type of the address to update")
        @PathVariable String addressType,
        @RequestBody AddressDto dto
    ) {
        AddressDto updatedAddress = addressService.updateAddressByEmpNo(empNo, addressType, dto);
        if (updatedAddress == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedAddress);
    }

    @Operation(
        summary = "Retrieving address details",
        description = "Retrieving address of employee using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/GET/{empNo}")
    public ResponseEntity<List<AddressDto>> getEmpAddressByEmpNo(
        @Parameter(description = "Employee number of the address to retrieve")
        @PathVariable Long empNo
    ) {
        List<AddressDto> addresses = addressService.getEmpAddressByEmpNo(empNo);
        if (!addresses.isEmpty()) {
            return ResponseEntity.ok(addresses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(
        summary = "Retrieving address",
        description = "Retrieving addresses of all employees using address type"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No addresses found with given type")
    })
    @GetMapping("/GET/type/{addressType}")
    public ResponseEntity<List<AddressDto>> findByAddressType(
        @Parameter(description = "Address type of the address to retrieve")
        @PathVariable String addressType
    ) {
        List<AddressDto> address = addressService.findByAddressType(addressType);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(address);
    }

    @Operation(
        summary = "Retrieving address count of an employee",
        description = "Retrieve employees by address count"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No employees found with given address count")
    })
    @GetMapping("/GET/address/{count}")
    public ResponseEntity<List<AddressDto>> findByEmployeeWithAddressCount(
        @Parameter(description = "Employee address count to retrieve the data")
        @PathVariable int count
    ) {
        List<AddressDto> list = addressService.findByEmployeeWithAddressCount(count);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    @Operation(
        summary = "Retrieving address details of all",
        description = "Retrieve address details of all employees using pagination and sorting"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No employee present")
    })
    @GetMapping("/GET/address")
    public ResponseEntity<List<AddressDto>> getAllAddress(
        @Parameter(description = "Page number")
        @RequestParam int pageNo,
        @Parameter(description = "Address count per page")
        @RequestParam int addressCountPerPage,
        @Parameter(description = "Sorting order")
        @RequestParam String sortDirection,
        @Parameter(description = "Sorting field to retrieve the data")
        @RequestParam String field
    ) {
        Page<AddressDto> list = addressService.getAllAddress(pageNo, addressCountPerPage, sortDirection, field);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No employee present");
        }
        return ResponseEntity.ok(list.getContent());
    }

    @Operation(
        summary = "Deleting address of an employee",
        description = "Delete all addresses of an employee using employee number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "All addresses deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/DELETE/address/{empNo}")
    public ResponseEntity<String> deleteAddress(
        @Parameter(description = "Employee number")
        @PathVariable long empNo
    ) {
        addressService.deleteEmployeeByEmpNo(empNo);
        return ResponseEntity.ok("All addresses of employee no " + empNo + " deleted successfully");
    }

    @Operation(
        summary = "Deleting specific address of an employee",
        description = "Delete address of an employee using employee number and address type"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/DELETE/address/{empNo}/{addressType}")
    public ResponseEntity<String> deleteAddress(
        @Parameter(description = "Employee number")
        @PathVariable long empNo,
        @Parameter(description = "Address type")
        @PathVariable String addressType
    ) {
        addressService.findByEmployeeEmpNoAndAddressType(empNo, addressType);
        return ResponseEntity.ok(addressType + " address of employee no " + empNo + " deleted successfully");
    }

    @Operation(
        summary = "Get address by ID",
        description = "Retrieve a single address using its unique address ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/GET/id/{id}")
    public ResponseEntity<AddressDto> getAddressById(
        @Parameter(description = "Unique ID of the address")
        @PathVariable Long id
    ) {
        AddressDto dto = addressService.getAddressById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
        summary = "Delete address by ID",
        description = "Delete a specific address using its unique address ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/DELETE/id/{id}")
    public ResponseEntity<String> deleteAddressById(
        @Parameter(description = "ID")
        @PathVariable Long id
    ) {
        addressService.deleteAddressById(id);
        return ResponseEntity.ok("Address with id " + id + " deleted successfully");
    }

    @Operation(
        summary = "Update address by ID",
        description = "Update a specific address using its unique address ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address updated successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PutMapping("/PUT/id/{id}")
    public ResponseEntity<AddressDto> updateAddressById(
        @Parameter(description = "ID")
        @PathVariable Long id,
        @RequestBody AddressDto dto
    ) {
        AddressDto updated = addressService.updateAddressById(id, dto);
        return ResponseEntity.ok(updated);
    }
}
