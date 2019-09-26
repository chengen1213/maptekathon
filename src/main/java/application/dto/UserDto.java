package application.dto;

import application.validation.ValidEmail;
import application.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import application.validation.PasswordMatches;

//@PasswordMatches
public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.userName}")
    private String userName;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    @ValidPassword
    private String password;

//    @NotNull
//    @Size(min = 1)
//    private String matchingPassword;

//    private boolean isUsing2FA;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

//    public String getMatchingPassword() {
//        return matchingPassword;
//    }
//
//    public void setMatchingPassword(final String matchingPassword) {
//        this.matchingPassword = matchingPassword;
//    }
//
//    public boolean isUsing2FA() {
//        return isUsing2FA;
//    }
//
//    public void setUsing2FA(boolean isUsing2FA) {
//        this.isUsing2FA = isUsing2FA;
//    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [userName=").append(userName).append(", email=").append(email).append(", password=").append(password)
                .append("]");
        return builder.toString();
    }

}
