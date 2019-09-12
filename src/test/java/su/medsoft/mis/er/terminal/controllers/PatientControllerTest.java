package su.medsoft.mis.er.terminal.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.reactivex.Single;
import org.junit.Test;
import org.mockito.Mockito;
import su.medsoft.mis.er.terminal.dto.ErrorDto;
import su.medsoft.mis.er.terminal.dto.PatientDto;
import su.medsoft.mis.er.terminal.responseMessages.PatientResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.ScheduleResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.PatientService;

import static org.junit.Assert.*;

public class PatientControllerTest {

    @Test
    public void getPatientByInsuranceAndBirthDateWithoutError() {
        PatientResponseMessage responseMessage = new PatientResponseMessage();
        responseMessage.setPatientDto(new PatientDto());

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.getPatientByInsuranceAndBirthDate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> response = controller.getPatientByInsuranceAndBirthDate("123", "2019-09-12");

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getPatientDto());
    }

    @Test
    public void getPatientByInsuranceAndBirthDateWithError() {

        PatientResponseMessage responseMessage = new PatientResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.getPatientByInsuranceAndBirthDate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> schedule = controller.getPatientByInsuranceAndBirthDate("123", "2019-09-12");

        assertNotNull(schedule.getBody());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, schedule.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(schedule.getBody().get().blockingGet().getPatientDto());
    }

    @Test
    public void getPatientByInsuranceAndBirthDateWithError_patientNotFound() {

        PatientResponseMessage responseMessage = new PatientResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(2);
        responseMessage.setErrorDto(errorDto);

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.getPatientByInsuranceAndBirthDate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> schedule = controller.getPatientByInsuranceAndBirthDate("123", "2019-09-12");

        assertNotNull(schedule.getBody());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
        assertEquals(2, schedule.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(schedule.getBody().get().blockingGet().getPatientDto());
    }

    @Test
    public void getPatientByInsuranceAndBirthDateWithError_multiplePatient() {

        PatientResponseMessage responseMessage = new PatientResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(3);
        responseMessage.setErrorDto(errorDto);

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.getPatientByInsuranceAndBirthDate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> schedule = controller.getPatientByInsuranceAndBirthDate("123", "2019-09-12");

        assertNotNull(schedule.getBody());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
        assertEquals(3, schedule.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(schedule.getBody().get().blockingGet().getPatientDto());
    }

    @Test
    public void createPatientWithoutError() {
        PatientResponseMessage responseMessage = new PatientResponseMessage();
        responseMessage.setPatientDto(new PatientDto());

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.createPatient(Mockito.any(PatientDto.class)))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> response = controller.createPatient(new PatientDto());

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getPatientDto());
    }

    @Test
    public void createPatientWithError() {

        PatientResponseMessage responseMessage = new PatientResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        PatientService service = Mockito.mock(PatientService.class);
        Mockito.when(service.createPatient(Mockito.any(PatientDto.class)))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        PatientController controller = new PatientController(service);

        HttpResponse<Single<PatientResponseMessage>> schedule = controller.createPatient(new PatientDto());

        assertNotNull(schedule.getBody());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, schedule.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(schedule.getBody().get().blockingGet().getPatientDto());
    }
}
