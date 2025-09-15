package com.ems.project.dto;

import com.ems.project.entity.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {

	private Long id;

	private String addressType;

	private String doorNo;

	private String street;

	private String area;

	private String state;

	private String pincode;

	private long empNo;

	public AddressDto(long empNo, String addressType, String doorNo, String street, String area, String state,
			String pincode) {
		this.empNo = empNo;
		this.addressType = addressType;
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.state = state;
		this.pincode = pincode;
	}

	public AddressDto(Address addr) {
		this.id = addr.getId();
		this.empNo = addr.getEmployee().getEmpNo();
		this.addressType = addr.getAddressType();
		this.doorNo = addr.getDoorNo();
		this.street = addr.getStreet();
		this.area = addr.getArea();
		this.state = addr.getState();
		this.pincode = addr.getPincode();

	}

}
