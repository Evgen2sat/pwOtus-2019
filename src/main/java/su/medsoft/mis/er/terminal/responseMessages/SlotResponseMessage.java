package su.medsoft.mis.er.terminal.responseMessages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import su.medsoft.mis.er.terminal.dto.SlotDto;

import java.util.List;

public class SlotResponseMessage extends BaseResponseMessage {
    @JsonProperty("slots")
    @SerializedName("slots")
    private List<SlotDto> slotDtoList;

    public List<SlotDto> getSlotDtoList() {
        return slotDtoList;
    }

    public void setSlotDtoList(List<SlotDto> slotDtoList) {
        this.slotDtoList = slotDtoList;
    }
}
