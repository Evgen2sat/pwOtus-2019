package su.medsoft.mis.er.terminal.external_services;

import retrofit2.Call;
import retrofit2.http.*;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.PostResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.SlotResponseMessage;

public interface ErServicesAPI {
    @Headers("Content-Type: application/json")
    @GET("/mis/api/er/services/posts")
    Call<PostResponseMessage> getPosts(@Query("moId") long moId, @Query("separateDepartId") Long separateDepartId, @Query("channel") String channel);

    @Headers("Content-Type: application/json")
    @GET("/mis/api/er/services/doctors")
    Call<DoctorResponseMessage> getDoctors(@Query("moId") long moId, @Query("postCode") String postCode, @Query("channel") String channel, @Query("departmentId") Long departmentId);

    @Headers("Content-Type: application/json")
    @GET("/mis/api/er/services/slots")
    Call<SlotResponseMessage> getSlots(@Query("channel") String channel, @Query("departmentId") Long departmentId, @Query("doctorId") long doctorId,
                                       @Query("beginDate") String beginDate, @Query("endDate") String endDate, @Query("moId") long moId);

    @Headers("Content-Type: application/json")
    @POST("/mis/api/er/services/appointments")
    Call<CreatedAppointmentResponseMessage> createAppointment(@Body CreateAppointmentBodyDto createAppointmentBodyDto);
}
