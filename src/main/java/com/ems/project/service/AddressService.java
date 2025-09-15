package com.ems.project.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.project.dto.AddressDto;
import com.ems.project.entity.Address;
import com.ems.project.entity.Employee;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.repository.AddressRepository;
import com.ems.project.repository.EmployeeRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    public AddressService(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<AddressDto> addEmpAddressByEmpNo(Long empNo, List<AddressDto> addressDto) {
        Employee employee = employeeRepository.findById(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not present with this employee number"));

        List<Address> newAddresses = addressDto.stream().map(dto -> {
            Address address = new Address();
            address.setAddressType(dto.getAddressType());
            address.setDoorNo(dto.getDoorNo());
            address.setStreet(dto.getStreet());
            address.setArea(dto.getArea());
            address.setState(dto.getState());
            address.setPincode(dto.getPincode());
            address.setEmployee(employee);
            return address;
        }).toList();

        employee.getAddressMappings().addAll(newAddresses);
        employeeRepository.save(employee);

        return addressToAddressDto(newAddresses);
    }

    public List<AddressDto> getEmpAddressByEmpNo(Long empNo) {
        List<Address> list = addressRepository.findByEmployeeEmpNo(empNo);
        return addressToAddressDto(list);
    }

    public List<AddressDto> findByAddressType(String addressType) {
        List<Address> list = addressRepository.findByAddressType(addressType);
        return addressToAddressDto(list);
    }

    public List<AddressDto> findByEmployeeWithAddressCount(int count) {
        List<Address> addresses = addressRepository.findByEmployeeWithAddressCount(count);
        return addressToAddressDto(addresses);
    }

    @Transactional
    public AddressDto updateAddressByEmpNo(long empNo, String addressType, AddressDto dto) {
        Address addresses = addressRepository.findByEmployee_EmpNoAndAddressType(empNo, addressType)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeNot Found"));

        if (dto.getAddressType() != null) addresses.setAddressType(dto.getAddressType());
        if (dto.getDoorNo() != null) addresses.setDoorNo(dto.getDoorNo());
        if (dto.getStreet() != null) addresses.setStreet(dto.getStreet());
        if (dto.getArea() != null) addresses.setArea(dto.getArea());
        if (dto.getState() != null) addresses.setState(dto.getState());
        if (dto.getPincode() != null) addresses.setPincode(dto.getPincode());

        return new AddressDto(addresses);
    }

    public Page<AddressDto> getAllAddress(int pageNo, int addressCountPerPage, String sortDirection, String field) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, field)
                : Sort.by(Sort.Direction.DESC, field);
        Pageable pageable = PageRequest.of(pageNo - 1, addressCountPerPage, sort);
        Page<Address> addresses = addressRepository.findAll(pageable);
        return addresses.map(AddressDto::new);
    }


    @Transactional
    public void deleteEmployeeByEmpNo(long empNo) {
        List<Address> addresses = addressRepository.findByEmployeeEmpNo(empNo);
        if (addresses.isEmpty()) throw new ResourceNotFoundException("Employee address not found");
        addressRepository.deleteAll(addresses);
    }

    @Transactional
    public void findByEmployeeEmpNoAndAddressType(long empNo, String addressType) {
        Address address = addressRepository.findByEmployeeEmpNoAndAddressType(empNo, addressType);
        if (address != null) {
            addressRepository.delete(address);
        } else {
            throw new ResourceNotFoundException("Employee address not present with this addresstype");
        }
    }

    public AddressDto getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
        return new AddressDto(address);
    }

    @Transactional
    public void deleteAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
        addressRepository.delete(address);
    }

    @Transactional
    public AddressDto updateAddressById(Long id, AddressDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));

        if (dto.getAddressType() != null) address.setAddressType(dto.getAddressType());
        if (dto.getDoorNo() != null) address.setDoorNo(dto.getDoorNo());
        if (dto.getStreet() != null) address.setStreet(dto.getStreet());
        if (dto.getArea() != null) address.setArea(dto.getArea());
        if (dto.getState() != null) address.setState(dto.getState());
        if (dto.getPincode() != null) address.setPincode(dto.getPincode());

        Address saved = addressRepository.save(address);
        return new AddressDto(saved);
    }
    
    public List<AddressDto> addressToAddressDto(List<Address> addresses) {
        return addresses.stream().map(AddressDto::new).toList();
    }
}
