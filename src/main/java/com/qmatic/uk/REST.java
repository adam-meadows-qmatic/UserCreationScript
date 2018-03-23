package com.qmatic.uk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.qmatic.qp.domain.configuration.branch.BranchGroup;
import com.qmatic.qp.domain.configuration.user.Role;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class REST {

    public static final String URL = "https://redacted";
    public static final String PASSWORD = "redacted";

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public static List<BranchGroup> getBranchGroups() throws UnirestException {
        HttpResponse<BranchGroup[]> asObject = Unirest.get(URL + "/qsystem/rest/config/branchGroups/")
                .basicAuth("superadmin", PASSWORD)
                .asObject(BranchGroup[].class);
        return Arrays.asList(asObject.getBody());
    }

    public static List<Role> getRoles() throws UnirestException {
        HttpResponse<Role[]> asObject = Unirest.get(URL + "/qsystem/rest/config/aaa/roles/")
                .basicAuth("superadmin", PASSWORD)
                .asObject(Role[].class);
        return Arrays.asList(asObject.getBody());
    }

    public static DTOUser getUser() throws UnirestException {
        HttpResponse<DTOUser> asObject = Unirest.get(URL + "/qsystem/rest/config/aaa/users/")
                .basicAuth("superadmin", PASSWORD)
                .asObject(DTOUser.class);
        return asObject.getBody();
    }

    public static JsonNode setUser(DTOUser user) throws UnirestException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String s = gson.toJson(user);
        JSONObject obj = new JSONObject(s);
        System.out.println(obj);
        HttpResponse<JsonNode> asObject = Unirest.post(URL + "/qsystem/rest/config/aaa/users/")
                .basicAuth("superadmin", PASSWORD)
                .header("Referer", URL)
                .header("Content-type", "application/json")
                .body(obj)
                .asJson();
        System.out.println(asObject.getStatus());
        return asObject.getBody();
    }

}
