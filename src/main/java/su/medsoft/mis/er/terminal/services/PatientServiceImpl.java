package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.dto.InsuranceDto;
import su.medsoft.mis.er.terminal.dto.MpiPatientDto;
import su.medsoft.mis.er.terminal.dto.PatientDto;
import su.medsoft.mis.er.terminal.responseMessages.PatientResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.PatientService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class PatientServiceImpl implements PatientService {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private su.medsoft.mis.er.terminal.external_services.PatientService patientService;

    @Override
    public HttpResponse<Single<PatientResponseMessage>> getPatientByInsuranceAndBirthDate(String npolis, String birthDate) {
        PatientResponseMessage responseMessage = new PatientResponseMessage();
        InsuranceDto insuranceDto = new InsuranceDto(null, npolis);

        try {
            List<MpiPatientDto> mpiPatientDtos = patientService.getPatientByENP(npolis, true);

            if(mpiPatientDtos.isEmpty()) {
                mpiPatientDtos = patientService.getPatientByInsuranceNumber(insuranceDto, true);
            }

            if(mpiPatientDtos.isEmpty()) {
                mpiPatientDtos = patientService.getPatientByENP(npolis, false);
            }

            if(mpiPatientDtos.isEmpty()) {
                mpiPatientDtos = patientService.getPatientByInsuranceNumber(insuranceDto, false);
            }

            if(mpiPatientDtos.isEmpty()) {
                responseMessage.setErrorDto(Application.getError(2, "Пациент не найден"));
                return Application.getResponse(responseMessage);
            }

            List<MpiPatientDto> mpiPatientDtosByBirthDate = mpiPatientDtos.stream().filter(r -> r.getBirthdate().equals(birthDate)).collect(Collectors.toList());

            if(mpiPatientDtosByBirthDate.size() > 1) {
                responseMessage.setErrorDto(Application.getError(3, "Найдено несколько пациентов"));
                return Application.getResponse(responseMessage);
            } else if(mpiPatientDtosByBirthDate.isEmpty()) {
                responseMessage.setErrorDto(Application.getError(2, "Пациент не найден"));
                return Application.getResponse(responseMessage);
            }

            PatientDto patientDto = patientService.getPatientInfo(mpiPatientDtosByBirthDate.get(0));
            responseMessage.setPatientDto(patientDto);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }

    @Override
    public HttpResponse<Single<PatientResponseMessage>> createPatient(PatientDto patientDto) {
        patientDto.setNot_confirmed(true);
        patientDto.setComment("Добавлен через терминал");
        PatientResponseMessage responseMessage = new PatientResponseMessage();

        try {
            if(patientDto != null && patientDto.getPersonDto() != null && patientDto.getPersonDto().getInsuranceList() != null
                    && (patientDto.getPersonDto().getInsuranceList().get(0).getType().getCode().equals("П") || patientDto.getPersonDto().getInsuranceList().get(0).getType().getCode().equals("Э"))) {
                patientDto.setEnp(patientDto.getPersonDto().getInsuranceList().get(0).getNumber());
            }

            Long patientId = patientService.createPatient(patientDto);

            if(patientId == null) {
                responseMessage.setErrorDto(Application.getError(2, "Не удалось добавить пациента"));
                return Application.getResponse(responseMessage);
            }

            patientDto.setId(patientId);
            responseMessage.setPatientDto(patientDto);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
