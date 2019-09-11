package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;

public interface DoctorService {
    HttpResponse<Single<DoctorResponseMessage>> getDoctors(long moId, String postCode, String channel, Long departmentId);
}
