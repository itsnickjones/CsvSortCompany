package com.company;

import java.util.HashMap;

public class CompanyDto {
    private HashMap<String, EnrolleeDto> userIdToEnrollee;

    public CompanyDto(){
        userIdToEnrollee = new HashMap<>();
    }

    public HashMap<String, EnrolleeDto> getUserIdToEnrollee() {
        return userIdToEnrollee;
    }

    public void setUserIdToEnrollee(HashMap<String, EnrolleeDto> userIdToEnrollee) {
        this.userIdToEnrollee = userIdToEnrollee;
    }
}
