package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortCsvService {

    public void sortCsv(String s) {
        mapCompanies(s);
    }

    private void mapCompanies(String s) {
        HashMap<String, CompanyDto> companiesMapping = new HashMap<>();

        try {
            FileReader fr = new FileReader(new File(s));
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] valArr; //0-UserId 1-Firstname 2-Last 3-Version 4- Company
            while ((line = br.readLine()) != null) {//while has next
                valArr = line.split(",");
                if(!isNumeric(valArr[3]))continue;//if not a header
                Integer version = Integer.valueOf(valArr[3]);
                String userId = valArr[0];
                String comp = valArr[4];

                EnrolleeDto enrolle = new EnrolleeDto(userId, valArr[1], valArr[2], version, comp);
                addToMapAvoidingDuplicates(comp,userId,version,enrolle,companiesMapping);
            }
            br.close();
            sortCompanies(companiesMapping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToMapAvoidingDuplicates( String comp,String userId ,Integer version,EnrolleeDto enrolle, HashMap<String, CompanyDto> companiesMapping ){
        if (!companiesMapping.containsKey(comp)) {//add to map if company not recorded
            companiesMapping.put(comp, new CompanyDto());
        }
        CompanyDto tempComp = companiesMapping.get(comp);//get pointer
        if(!tempComp.getUserIdToEnrollee().containsKey(userId)// if not found or has old version.. add
                || (tempComp.getUserIdToEnrollee().containsKey(userId) && (version > tempComp.getUserIdToEnrollee().get(userId).version))){
            tempComp.getUserIdToEnrollee().put(userId, enrolle);
        }
    }
    private void sortCompanies(HashMap<String, CompanyDto> companiesMapping){//sort list
        for(String company : companiesMapping.keySet()){
            CompanyDto tempComp = companiesMapping.get(company);
            List<EnrolleeDto> enrolleeDtoList = new ArrayList<EnrolleeDto>(tempComp.getUserIdToEnrollee().values());
            Collections.sort(enrolleeDtoList);
            createCsvForCompany(company,enrolleeDtoList);
        }
        System.out.println("done");
    }

    private void createCsvForCompany(String Company,List<EnrolleeDto>enrollees){//create csv file
        List<String[]>csvLine = new ArrayList<>();
        for(EnrolleeDto enrollee : enrollees){
            csvLine.add(new String[]{enrollee.user_Id ,enrollee.first_Name,enrollee.last_Name,enrollee.version.toString(),enrollee.insurance_Company});
        }
        File csvOutput = new File(Company+"SortedCsv.csv");
        try(PrintWriter pw = new PrintWriter(csvOutput)){
            csvLine.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }


    private static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
