package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.dto.PatientDto;
import su.medsoft.mis.er.terminal.responseMessages.PatientResponseMessage;

public interface PatientService {
    HttpResponse<Single<PatientResponseMessage>> getPatientByInsuranceAndBirthDate(String npolis, String birthDate);

    HttpResponse<Single<PatientResponseMessage>> createPatient(PatientDto patientDto);
}
