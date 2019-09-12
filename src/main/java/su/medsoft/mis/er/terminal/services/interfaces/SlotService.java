package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.responseMessages.SlotResponseMessage;

public interface SlotService {
    HttpResponse<Single<SlotResponseMessage>> getSlots(String channel, Long departmentId, long doctorId, String beginDate, String endDate, long moId);
}
