package su.medsoft.mis.er.terminal.responseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import su.medsoft.mis.er.terminal.dto.AppointmentDayDto;

import java.util.List;

public class AppointmentResponseMessage extends BaseResponseMessage {

    @JsonProperty("appointments")
    private List<AppointmentDayDto> appointmentDtoList;

    public List<AppointmentDayDto> getAppointmentDtoList() {
        return appointmentDtoList;
    }

    public void setAppointmentDtoList(List<AppointmentDayDto> appointmentDtoList) {
        this.appointmentDtoList = appointmentDtoList;
    }
}
