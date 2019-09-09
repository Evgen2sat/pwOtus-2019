package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class AppointmentDto {
    @JsonProperty("appointment_id")
    @SerializedName("id")
    private long appointment_id;

    @JsonProperty("schedule_day_id")
    private Long schedule_day_id;

    @JsonProperty("staff_last_name")
    private String staff_last_name;

    @JsonProperty("staff_first_name")
    private String staff_first_name;

    @JsonProperty("staff_middle_name")
    private String staff_middle_name;

    @JsonProperty("post_code")
    private String post_code;

    @JsonProperty("post_name")
    private String post_name;

    @JsonProperty("begin_time")
    private String begin_time;

    @JsonProperty("end_time")
    private String end_time;

    @JsonProperty("schedule_type_code")
    private String schedule_type_code;

    @JsonProperty("schedule_type_name")
    private String schedule_type_name;

    @JsonProperty("cabinet")
    private String cabinet;

    public long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Long getSchedule_day_id() {
        return schedule_day_id;
    }

    public void setSchedule_day_id(Long schedule_day_id) {
        this.schedule_day_id = schedule_day_id;
    }

    public String getStaff_last_name() {
        return staff_last_name;
    }

    public void setStaff_last_name(String staff_last_name) {
        this.staff_last_name = staff_last_name;
    }

    public String getStaff_first_name() {
        return staff_first_name;
    }

    public void setStaff_first_name(String staff_first_name) {
        this.staff_first_name = staff_first_name;
    }

    public String getStaff_middle_name() {
        return staff_middle_name;
    }

    public void setStaff_middle_name(String staff_middle_name) {
        this.staff_middle_name = staff_middle_name;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSchedule_type_code() {
        return schedule_type_code;
    }

    public void setSchedule_type_code(String schedule_type_code) {
        this.schedule_type_code = schedule_type_code;
    }

    public String getSchedule_type_name() {
        return schedule_type_name;
    }

    public void setSchedule_type_name(String schedule_type_name) {
        this.schedule_type_name = schedule_type_name;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
}
