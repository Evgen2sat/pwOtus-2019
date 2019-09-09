package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class DoctorDto {
    @JsonProperty("id")
    @SerializedName("id")
    private long id;

    @JsonProperty("snils")
    @SerializedName("snils")
    private String snils;

    @JsonProperty("last_name")
    @SerializedName("last_name")
    private String last_name;

    @JsonProperty("first_name")
    @SerializedName("first_name")
    private String first_name;

    @JsonProperty("middle_name")
    @SerializedName("middle_name")
    private String middle_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }
}
