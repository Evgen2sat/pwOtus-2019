package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.external_services.ErService;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.DoctorService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DoctorServiceImpl implements DoctorService {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private ErService erService;

    public HttpResponse<Single<DoctorResponseMessage>> getDoctors(long moId, String postCode, String channel, Long departmentId) {
        DoctorResponseMessage responseMessage = null;

        try {
            responseMessage = erService.getDoctors(moId, postCode, channel, departmentId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new DoctorResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
