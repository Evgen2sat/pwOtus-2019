package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AppointmentDayDto {
    @JsonProperty("patient_id")
    private long patient_id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("appointment")
    private List<AppointmentDto> appointmentDtoList;

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AppointmentDto> getAppointmentDtoList() {
        return appointmentDtoList;
    }

    public void setAppointmentDtoList(List<AppointmentDto> appointmentDtoList) {
        this.appointmentDtoList = appointmentDtoList;
    }
}
