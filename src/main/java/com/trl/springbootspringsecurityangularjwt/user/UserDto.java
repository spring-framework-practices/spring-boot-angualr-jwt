package com.trl.springbootspringsecurityangularjwt.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private List<UserRole> roles;
    private List<UserAuthority> authorities;
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
        this.roles = builder.roles;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserAuthority> authorities) {
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
                Objects.equals(roles, userDto.roles) &&
                Objects.equals(authorities, userDto.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, username, password, email, profileImageUrl, lastLoginDate, lastLoginDateDisplay, joinDate, roles, authorities, isActive, isNotLocked);
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
                ", roles=" + roles +
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
        private Date lastLoginDate;
        private Date lastLoginDateDisplay;
        private Date joinDate;
        private List<UserRole> roles;
        private List<UserAuthority> authorities;
        private boolean isActive;
        private boolean isNotLocked;

        public Builder withId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Parameter 'id' must be not equals to null.");
            }
            this.id = id;
            return this;
        }

        public Builder withUserId(String userId) {
            if (userId == null) {
                throw new IllegalArgumentException("Parameter 'userId' must be not equals to null.");
            }
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            if (firstName == null) {
                throw new IllegalArgumentException("Parameter 'firstName' must be not equals to null.");
            }
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            if (lastName == null) {
                throw new IllegalArgumentException("Parameter 'lastName' must be not equals to null.");
            }
            this.lastName = lastName;
            return this;
        }

        public Builder withUsername(String username) {
            if (username == null) {
                throw new IllegalArgumentException("Parameter 'username' must be not equals to null.");
            }
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            if (password == null) {
                throw new IllegalArgumentException("Parameter 'password' must be not equals to null.");
            }
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            if (email == null) {
                throw new IllegalArgumentException("Parameter 'email' must be not equals to null.");
            }
            this.email = email;
            return this;
        }

        public Builder withProfileImageUrl(String profileImageUrl) {
            if (profileImageUrl == null) {
                throw new IllegalArgumentException("Parameter 'profileImageUrl' must be not equals to null.");
            }
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public Builder withLastLoginDate(Date lastLoginDate) {
            if (lastLoginDate == null) {
                throw new IllegalArgumentException("Parameter 'lastLoginDate' must be not equals to null.");
            }
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public Builder withLastLoginDateDisplay(Date lastLoginDateDisplay) {
            if (lastLoginDateDisplay == null) {
                throw new IllegalArgumentException("Parameter 'lastLoginDateDisplay' must be not equals to null.");
            }
            this.lastLoginDateDisplay = lastLoginDateDisplay;
            return this;
        }

        public Builder withJoinDate(Date joinDate) {
            if (joinDate == null) {
                throw new IllegalArgumentException("Parameter 'joinDate' must be not equals to null.");
            }
            this.joinDate = joinDate;
            return this;
        }

        public Builder withRoles(List<UserRole> roles) {
            if (roles == null) {
                throw new IllegalArgumentException("Parameter 'roles' must be not equals to null.");
            }
            this.roles = new ArrayList<>(roles);
            return this;
        }

        public Builder withAuthorities(List<UserAuthority> authorities) {
            if (authorities == null) {
                throw new IllegalArgumentException("Parameter 'authorities' must be not equals to null.");
            }
            this.authorities = new ArrayList<>(authorities);
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
