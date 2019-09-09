package su.medsoft.mis.er.terminal.responseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import su.medsoft.mis.er.terminal.dto.CreatedAppointmentInfoDto;

public class CreatedAppointmentResponseMessage extends BaseResponseMessage {
    @JsonProperty("appointment")
    @SerializedName("appointment")
    private CreatedAppointmentInfoDto appointmentDto;

    public CreatedAppointmentInfoDto getAppointmentDto() {
        return appointmentDto;
    }

    public void setAppointmentDto(CreatedAppointmentInfoDto appointmentDto) {
        this.appointmentDto = appointmentDto;
    }
}
