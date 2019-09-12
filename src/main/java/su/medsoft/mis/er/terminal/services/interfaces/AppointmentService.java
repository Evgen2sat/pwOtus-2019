package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.responseMessages.AppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;

public interface AppointmentService {
    HttpResponse<Single<AppointmentResponseMessage>> getAppointmentsByPatient(long patient_id, String beginDate, String endDate);

    HttpResponse<Single<CreatedAppointmentResponseMessage>> createdAppointment(CreateAppointmentBodyDto createAppointmentBodyDto);
}
