package org.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<RoleDto> roleList;
}
