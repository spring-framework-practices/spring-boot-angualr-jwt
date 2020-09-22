package com.trl.springbootspringsecurityangularjwt.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    public UserConverter() {
    }

    public UserDto mapEntityToDto(UserEntity entity) {
        UserDto result = null;

        if (entity == null) {
            LOG.error("************ mapEntityToDto() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDto() ---> userEntity = " + entity
                + " ---> userEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new UserDto.Builder()
                .withId(entity.getId())
                .withUserId(entity.getUserId())
                .withFirstName(entity.getLastName())
                .withLastName(entity.getLastName())
                .withUsername(entity.getUsername())
                .withPassword(entity.getPassword())
                .withEmail(entity.getEmail())
                .withProfileImageUrl(entity.getProfileImageUrl())
                .withLastLoginDate(entity.getLastLoginDate())
                .withLastLoginDateDisplay(entity.getLastLoginDateDisplay())
                .withJoinDate(entity.getJoinDate())
                .withRoles(entity.getRoles())
                .withAuthorities(entity.getAuthorities())
                .isActive(entity.isActive())
                .isNotLocked(entity.isNotLocked())
                .build();

        LOG.debug("************ mapEntityToDto() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        UserEntity result = null;

        if (dto == null) {
            LOG.error("************ mapDtoToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDtoToEntity() ---> userDto = " + dto
                + " ---> userDto.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new UserEntity.Builder()
                .withId(dto.getId())
                .withUserId(dto.getUserId())
                .withFirstName(dto.getLastName())
                .withLastName(dto.getLastName())
                .withUsername(dto.getUsername())
                .withPassword(dto.getPassword())
                .withEmail(dto.getEmail())
                .withProfileImageUrl(dto.getProfileImageUrl())
                .withLastLoginDate(dto.getLastLoginDate())
                .withLastLoginDateDisplay(dto.getLastLoginDateDisplay())
                .withJoinDate(dto.getJoinDate())
                .withRoles(dto.getRoles())
                .withAuthorities(dto.getAuthorities())
                .isActive(dto.isActive())
                .isNotLocked(dto.isNotLocked())
                .build();

        LOG.debug("************ mapDtoToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }
}
