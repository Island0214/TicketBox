package com.example.bean;

/**
 * Created by island on 2018/3/21.
 */
public class ChangePasswordBean {
    private String username;

    private String OldPassword;

    private String newPassword1;

    private String newPassword2;

    public ChangePasswordBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    @Override
    public String toString() {
        return "ChangePasswordBean{" +
                "username='" + username + '\'' +
                ", OldPassword='" + OldPassword + '\'' +
                ", newPassword1='" + newPassword1 + '\'' +
                ", newPassword2='" + newPassword2 + '\'' +
                '}';
    }
}
