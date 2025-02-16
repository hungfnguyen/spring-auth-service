package com.hungfnguyen.identity_service.mapper;

import com.hungfnguyen.identity_service.dto.request.UserCreationRequest;
import com.hungfnguyen.identity_service.dto.request.UserUpdateRequest;
import com.hungfnguyen.identity_service.dto.response.UserResponse;
import com.hungfnguyen.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //@Mapping(source = "firstName", target = "lastName")
    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);
}
