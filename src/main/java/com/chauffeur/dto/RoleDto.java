package com.chauffeur.dto;


import com.chauffeur.enumeration.RoleName;
import com.chauffeur.models.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	
	private long id;
	
    private RoleName name;
	
	public RoleDto fromEntityToDto(Role role) {
		if (role == null) {
			return null;
		}
		
		return RoleDto.builder()
				.name(role.getName())
				.build();
		
	}
	
	public Role fromDtoToEntity(RoleDto roleDto) {
		if (roleDto == null) {
			return null;
		}
		Role role = new Role();
		role.setName(roleDto.getName());
	
		return role;
	}
	
	

}
