package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SortCsvServiceTest {

    @Test
    void sortCompanies() {//checks adding higher version works
        SortCsvService sortCsvService = new SortCsvService();
        HashMap<String, CompanyDto> companiesMapping = new HashMap<>();
        CompanyDto testCo = new CompanyDto();
        String comp ="testCo";
        EnrolleeDto enrolleeDto = new EnrolleeDto("1","a","b",2,comp);
        EnrolleeDto enrolleeDtoDup = new EnrolleeDto("1","a","b",99,comp);
        testCo.getUserIdToEnrollee().put("1", enrolleeDto);
        companiesMapping.put(comp,testCo);
        sortCsvService.addToMapAvoidingDuplicates(comp,"1",99,enrolleeDtoDup,companiesMapping);
        Assertions.assertEquals(companiesMapping.get(comp).getUserIdToEnrollee().get("1").version, 99);
    }

}