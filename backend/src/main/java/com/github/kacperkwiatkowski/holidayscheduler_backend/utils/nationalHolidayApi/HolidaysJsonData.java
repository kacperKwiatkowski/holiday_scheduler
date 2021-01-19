package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HolidaysJsonData {
    @SerializedName("meta")
    @Expose
    private ServerInfo serverInfo;
    @SerializedName("response")
    @Expose
    private ServerResponse serverResponse;

    public HolidaysJsonData() {
    }

    public HolidaysJsonData(ServerInfo serverInfo, ServerResponse serverResponse) {
        this.serverInfo = serverInfo;
        this.serverResponse = serverResponse;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    @Override
    public String toString() {
        return "Server info: " + serverInfo.toString() + "\n";
    }

    public static List<Holidays> readNationalHolidaysFromApiUrl(String apiUrl) {

        Gson gson = new Gson();
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getHolidays(gson, content);
    }

    private static List<Holidays> getHolidays(Gson gson, StringBuilder content) {
        if(isApiRequestSuccessful(gson.fromJson(content.toString(), HolidaysJsonData.class).getServerInfo().getCode())){
            return gson.fromJson(content.toString(), HolidaysJsonData.class).getServerResponse().getHolidays();
        } else {
            List<Holidays> emptyList;
            return  emptyList = new ArrayList<>();
        }
    }

    private static boolean isApiRequestSuccessful(Integer requestStatus){
        return requestStatus > 199 & requestStatus < 300;
    }
}

