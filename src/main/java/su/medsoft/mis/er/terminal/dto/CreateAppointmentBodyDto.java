package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAppointmentBodyDto {
    @JsonProperty("moId")
    private long moId;

    @JsonProperty("slotId")
    private long slotId;

    @JsonProperty("patientId")
    private long patientId;

    @JsonProperty("author")
    private String author;

    @JsonProperty("channel")
    private String channel;

    public long getMoId() {
        return moId;
    }

    public void setMoId(long moId) {
        this.moId = moId;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
