package com.qmatic.uk;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.qmatic.qp.domain.configuration.branch.BranchGroup;
import com.qmatic.qp.domain.configuration.branch.SmallBranch;
import com.qmatic.qp.domain.configuration.user.Role;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Start {

    public static void main(String[] args) throws UnirestException, IOException {
        List<CSVUser> csvUsers = null;
        try (Reader reader = Files.newBufferedReader(Paths.get("E:\\github\\UserCreationScript\\src\\main\\java\\com\\qmatic\\uk\\test.csv"));) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvUsers = csvToBean.parse();

            for (CSVUser csvUser : csvUsers) {
                System.out.println(csvUser.toString());
            }
        }

        if (csvUsers.isEmpty()) {
            System.out.println("end");
            return;
        }


        System.out.println("test");
        REST rest = new REST();
        List<BranchGroup> branchGroups = rest.getBranchGroups();
        List<Integer> barclaysBranchGroups = new ArrayList<Integer>();
        for (BranchGroup branchGroup : branchGroups) {
            if (branchGroup.getName().toLowerCase().equals("trial")) {
                //TODO we want?
                //barclaysBranchGroups.add(branchGroup.getId());
            }
        }

        List<SmallBranch> branches = rest.getBranches();

        List<Role> roles = rest.getRoles();
        List<Integer> barclaysRoles = new ArrayList<Integer>();

        for (Role role : roles) {
            if (role.getName().toLowerCase().contains("barclays"))
                barclaysRoles.add(role.getId());
        }


        for (CSVUser csvUser : csvUsers) {
            DTOUser user = new DTOUser();
            user.setFirstName(csvUser.getFirstName());
            user.setLastName(csvUser.getLastName());
            String userPass = csvUser.getFirstName().toLowerCase().substring(0, 3) + csvUser.getLastName().toLowerCase().substring(0, 3);
            user.setUserName(userPass);
            user.setPassword(userPass + "1");

            user.setBranchGroups(barclaysBranchGroups);

            List<Integer> branchIds = new ArrayList<>();
            for (SmallBranch branch : branches) {
                String branchName = branch.getName();
                if(branchName.toLowerCase().equals(csvUser.getBranch().toLowerCase())) {
                    branchIds.add(branch.getId());
                }
            }
            user.setBranches(branchIds);

            Map<String, String> data = user.getData();
            data.put("label.phone", "");
            data.put("label.email", "");
            user.setData(data);
            user.setRoles(barclaysRoles);

            JsonNode dtoUser = rest.setUser(user);
            System.out.println(dtoUser.toString());
        }
    }
}
