package su.medsoft.mis.er.terminal.responseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import su.medsoft.mis.er.terminal.dto.DoctorDto;

import java.util.List;

public class DoctorResponseMessage extends BaseResponseMessage {
    @JsonProperty("doctors")
    @SerializedName("doctors")
    private List<DoctorDto> doctorDtoList;

    public List<DoctorDto> getDoctorDtoList() {
        return doctorDtoList;
    }

    public void setDoctorDtoList(List<DoctorDto> doctorDtoList) {
        this.doctorDtoList = doctorDtoList;
    }
}
