package com.company;


public class EnrolleeDto implements Comparable<EnrolleeDto> {


    String user_Id;
    String first_Name;
    String last_Name;
    Integer version;
    String insurance_Company;

    public  EnrolleeDto(String userId, String firstName, String lastName, Integer vers, String insuranceCompany) {
        this.user_Id = userId;
        this.first_Name = firstName;
        this.last_Name = lastName;
        this.version = vers;
        this.insurance_Company = insuranceCompany;
    }

    @Override
    public int compareTo(EnrolleeDto enrolleeDto) {
        int firstComparison = last_Name.compareTo(enrolleeDto.last_Name);
        if (firstComparison != 0) {
            return firstComparison;
        } else {
            return first_Name.compareTo(enrolleeDto.first_Name);
        }
    }



}
