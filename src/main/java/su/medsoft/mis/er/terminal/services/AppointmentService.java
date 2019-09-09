package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.dto.AppointmentDayDto;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.external_services.ErService;
import su.medsoft.mis.er.terminal.repositories.AppointmentRepository;
import su.medsoft.mis.er.terminal.responseMessages.AppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AppointmentService {

    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private AppointmentRepository appointmentRepository;

    @Inject
    private ErService erService;

    public HttpResponse<Single<AppointmentResponseMessage>> getAppointmentsByPatient(long patient_id, String beginDate, String endDate) {
        AppointmentResponseMessage responseMessage = new AppointmentResponseMessage();

        try {
            List<AppointmentDayDto> appointmentDtoList = appointmentRepository.getAppointmentsByPatient(patient_id, beginDate, endDate);
            responseMessage.setAppointmentDtoList(appointmentDtoList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }


        return Application.getResponse(responseMessage);
    }

    public HttpResponse<Single<CreatedAppointmentResponseMessage>> createdAppointment(CreateAppointmentBodyDto createAppointmentBodyDto) {
        CreatedAppointmentResponseMessage responseMessage = null;

        try {
            responseMessage = erService.createAppointment(createAppointmentBodyDto);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new CreatedAppointmentResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
