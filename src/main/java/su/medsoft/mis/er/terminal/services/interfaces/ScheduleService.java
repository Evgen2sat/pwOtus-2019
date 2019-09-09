package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.responseMessages.ScheduleResponseMessage;

public interface ScheduleService {
    HttpResponse<Single<ScheduleResponseMessage>> getSchedule(Long departmentId, Boolean aggregate, long moId, String source);
}
