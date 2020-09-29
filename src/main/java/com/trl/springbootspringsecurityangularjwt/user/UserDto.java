package com.trl.springbootspringsecurityangularjwt.user;

import com.trl.springbootspringsecurityangularjwt.enumeration.Authority;
import com.trl.springbootspringsecurityangularjwt.enumeration.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;
    private String userId;

    @NotBlank(message = "First Name is mandatory.")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory.")
    private String lastName;

    @NotBlank(message = "Username Name is mandatory.")
    private String username;

    private String password;

    @Email
    @NotBlank(message = "Email Address is mandatory.")
    private String email;

    private String profileImageUrl;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastLoginDateDisplay;
    private LocalDateTime joinDate;
    private Role role;
    private Authority authorities;
    private boolean isActive;
    private boolean isNotLocked;

    public UserDto() {
    }

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.profileImageUrl = builder.profileImageUrl;
        this.lastLoginDate = builder.lastLoginDate;
        this.lastLoginDateDisplay = builder.lastLoginDateDisplay;
        this.joinDate = builder.joinDate;
        this.role = builder.role;
        this.authorities = builder.authorities;
        this.isActive = builder.isActive;
        this.isNotLocked = builder.isNotLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateTime getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(LocalDateTime lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Authority getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authority authorities) {
        this.authorities = authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return isActive == userDto.isActive &&
                isNotLocked == userDto.isNotLocked &&
                Objects.equals(id, userDto.id) &&
                Objects.equals(userId, userDto.userId) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(profileImageUrl, userDto.profileImageUrl) &&
                Objects.equals(lastLoginDate, userDto.lastLoginDate) &&
                Objects.equals(lastLoginDateDisplay, userDto.lastLoginDateDisplay) &&
                Objects.equals(joinDate, userDto.joinDate) &&
                role == userDto.role &&
                authorities == userDto.authorities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, username, password, email, profileImageUrl, lastLoginDate, lastLoginDateDisplay, joinDate, role, authorities, isActive, isNotLocked);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginDateDisplay=" + lastLoginDateDisplay +
                ", joinDate=" + joinDate +
                ", role=" + role +
                ", authorities=" + authorities +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                '}';
    }

    public static class Builder {
        private Long id;
        private String userId;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String email;
        private String profileImageUrl;
        private LocalDateTime lastLoginDate;
        private LocalDateTime lastLoginDateDisplay;
        private LocalDateTime joinDate;
        private Role role;
        private Authority authorities;
        private boolean isActive;
        private boolean isNotLocked;

        public Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public Builder withLastLoginDate(LocalDateTime lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public Builder withLastLoginDateDisplay(LocalDateTime lastLoginDateDisplay) {
            this.lastLoginDateDisplay = lastLoginDateDisplay;
            return this;
        }

        public Builder withJoinDate(LocalDateTime joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withAuthorities(Authority authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder isNotLocked(boolean isNotLocked) {
            this.isNotLocked = isNotLocked;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
