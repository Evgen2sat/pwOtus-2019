package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.external_services.ErService;
import su.medsoft.mis.er.terminal.responseMessages.SlotResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.SlotService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SlotServiceImpl implements SlotService {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private ErService erService;

    @Override
    public HttpResponse<Single<SlotResponseMessage>> getSlots(String channel, Long departmentId, long doctorId, String beginDate, String endDate, long moId) {
        SlotResponseMessage responseMessage = null;

        try {
            responseMessage = erService.getSlots(channel, departmentId, doctorId, beginDate, endDate, moId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new SlotResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
