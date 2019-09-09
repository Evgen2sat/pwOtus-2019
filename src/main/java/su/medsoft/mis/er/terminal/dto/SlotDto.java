package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class SlotDto {
    @JsonProperty("id")
    @SerializedName("id")
    private long id;

    @JsonProperty("begin_date")
    @SerializedName("begin_date")
    private String begin_date;

    @JsonProperty("end_date")
    @SerializedName("end_date")
    private String end_date;

    @JsonProperty("cabinet")
    @SerializedName("cabinet")
    private String cabinet;

    @JsonProperty("status")
    @SerializedName("status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
