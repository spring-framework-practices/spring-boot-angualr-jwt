package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.constant.UserConverterConstant;
import com.trl.springbootspringsecurityangularjwt.constant.UserConverterLoggerHeaderConstant;

import static com.trl.springbootspringsecurityangularjwt.constant.UserConverterConstant.ILLEGAL_ARGUMENT;
import static com.trl.springbootspringsecurityangularjwt.constant.UserConverterLoggerHeaderConstant.MAP_LIST_ENTITIES_TO_LIST_DTO_LOGGER_HEADER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    public UserConverter() {
    }

    public UserDto mapEntityToDto(UserEntity entity) {
        UserDto result = null;

        if (entity == null) {
            LOGGER.error("************ mapEntityToDto() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOGGER.debug("************ mapEntityToDto() ---> userEntity = " + entity
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
                .withRole(entity.getRole())
                .withAuthorities(entity.getAuthorities())
                .isActive(entity.isActive())
                .isNotLocked(entity.isNotLocked())
                .build();

        LOGGER.debug("************ mapEntityToDto() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    public List<UserDto> mapListEntitiesToListDto(List<UserEntity> entityList) {
        List<UserDto> userListResult = null;
        // TODO: This mithod is dangerous with over data. Need to do pagination.
        LOGGER.debug(MAP_LIST_ENTITIES_TO_LIST_DTO_LOGGER_HEADER + "entityList = " + entityList);
        if (entityList == null || entityList.isEmpty()) {
            LOGGER.error(MAP_LIST_ENTITIES_TO_LIST_DTO_LOGGER_HEADER + ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        userListResult = entityList.stream().map(userEntity -> {
            UserDto userDto = new UserDto.Builder()
                    .withId(userEntity.getId())
                    .withUserId(userEntity.getUserId())
                    .withFirstName(userEntity.getLastName())
                    .withLastName(userEntity.getLastName())
                    .withUsername(userEntity.getUsername())
                    .withPassword(userEntity.getPassword())
                    .withEmail(userEntity.getEmail())
                    .withProfileImageUrl(userEntity.getProfileImageUrl())
                    .withLastLoginDate(userEntity.getLastLoginDate())
                    .withLastLoginDateDisplay(userEntity.getLastLoginDateDisplay())
                    .withJoinDate(userEntity.getJoinDate())
                    .withRole(userEntity.getRole())
                    .withAuthorities(userEntity.getAuthorities())
                    .isActive(userEntity.isActive())
                    .isNotLocked(userEntity.isNotLocked())
                    .build();
            return userDto;
        }).collect(Collectors.toList());
        LOGGER.debug(MAP_LIST_ENTITIES_TO_LIST_DTO_LOGGER_HEADER + "userListResult = " + userListResult);
        return userListResult;
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        UserEntity result = null;

        if (dto == null) {
            LOGGER.error("************ mapDtoToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOGGER.debug("************ mapDtoToEntity() ---> userDto = " + dto
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
                .withRole(dto.getRole())
                .withAuthorities(dto.getAuthorities())
                .isActive(dto.isActive())
                .isNotLocked(dto.isNotLocked())
                .build();

        LOGGER.debug("************ mapDtoToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }
}
