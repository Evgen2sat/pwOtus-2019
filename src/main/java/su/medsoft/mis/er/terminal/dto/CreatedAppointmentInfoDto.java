package su.medsoft.mis.er.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class CreatedAppointmentInfoDto {
    @JsonProperty("appointment_id")
    @SerializedName("id")
    private long id;

    @JsonProperty("date")
    @SerializedName("date")
    private String createDate;

    @JsonProperty("patientLastName")
    @SerializedName("patientLastName")
    private String patientLastName;

    @JsonProperty("patientFirstName")
    @SerializedName("patientFirstName")
    private String patientFirstName;

    @JsonProperty("patientMiddleName")
    @SerializedName("patientMiddleName")
    private String patientMiddleName;

    @JsonProperty("insuranceNumber")
    @SerializedName("insuranceNumber")
    private String insuranceNumber;

    @JsonProperty("doctorLastName")
    @SerializedName("doctorLastName")
    private String doctorLastName;

    @JsonProperty("doctorFirstName")
    @SerializedName("doctorFirstName")
    private String doctorFirstName;

    @JsonProperty("doctorMiddleName")
    @SerializedName("doctorMiddleName")
    private String doctorMiddleName;

    @JsonProperty("doctorPostName")
    @SerializedName("doctorPostName")
    private String doctorPostName;

    @JsonProperty("appointmentDate")
    @SerializedName("appointmentDate")
    private String appointmentDate;

    @JsonProperty("appointmentTime")
    @SerializedName("appointmentTime")
    private String appointmentTime;

    @JsonProperty("cabinet")
    @SerializedName("cabinet")
    private String cabinet;

    @JsonProperty("moName")
    @SerializedName("moName")
    private String moName;

    @JsonProperty("departmentName")
    @SerializedName("departmentName")
    private String departmentName;

    @JsonProperty("departmentAddress")
    @SerializedName("departmentAddress")
    private String departmentAddress;

    @JsonProperty("departmentPhone")
    @SerializedName("departmentPhone")
    private String departmentPhone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorMiddleName() {
        return doctorMiddleName;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public String getDoctorPostName() {
        return doctorPostName;
    }

    public void setDoctorPostName(String doctorPostName) {
        this.doctorPostName = doctorPostName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }
}
