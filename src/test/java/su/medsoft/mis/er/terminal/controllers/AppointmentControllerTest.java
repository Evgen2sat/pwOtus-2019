package su.medsoft.mis.er.terminal.controllers;

import io.micronaut.core.convert.DefaultConversionService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.simple.SimpleHttpHeaders;
import io.reactivex.Single;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.dto.CreatedAppointmentInfoDto;
import su.medsoft.mis.er.terminal.dto.ErrorDto;
import su.medsoft.mis.er.terminal.responseMessages.AppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.AppointmentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AppointmentControllerTest {

    private static HttpHeaders headers;

    @BeforeClass
    public static void initHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb0lkIjoxLCJzeXN0ZW1JZCI6IjA1ZjA0NmRkLTI3Y2YtNDdhOS1hMjA1LWJlYmI0MTNkOGRmMSIsInN5c3RlbU5hbWUiOiLQotC10YDQvNC40L3QsNC7INCc0LXQtNCh0L7RhNGCIiwiaXNzIjoibWVkc29mdCIsInN1YiI6ItCf0LXRgtGA0LXQu9C10LLQuNGHINCh0LXRgNCz0LXQuSJ9.Uyv0SzrNwd82KGUOjiIlkVxt8CghsmTWoI6STV1tBwo");

        headers = new SimpleHttpHeaders(header, new DefaultConversionService());
    }

    @Test
    public void getAppointmentsByPatientWithoutError() {
        AppointmentResponseMessage responseMessage = new AppointmentResponseMessage();
        responseMessage.setAppointmentDtoList(new ArrayList<>());

        AppointmentService service = Mockito.mock(AppointmentService.class);
        Mockito.when(service.getAppointmentsByPatient(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        AppointmentController controller = new AppointmentController(service);

        HttpResponse<Single<AppointmentResponseMessage>> response = controller.getAppointmentsByPatient(1, "2019-09-12", "2019-09-12");

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getAppointmentDtoList());
    }

    @Test
    public void getAppointmentsByPatientWithError() {

        AppointmentResponseMessage responseMessage = new AppointmentResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        AppointmentService service = Mockito.mock(AppointmentService.class);
        Mockito.when(service.getAppointmentsByPatient(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        AppointmentController controller = new AppointmentController(service);

        HttpResponse<Single<AppointmentResponseMessage>> response = controller.getAppointmentsByPatient(1, "2019-09-12", "2019-09-12");

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(response.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(response.getBody().get().blockingGet().getAppointmentDtoList());
    }

    @Test
    public void createdAppointmentWithoutError() {
        CreatedAppointmentResponseMessage responseMessage = new CreatedAppointmentResponseMessage();
        responseMessage.setAppointmentDto(new CreatedAppointmentInfoDto());

        AppointmentService service = Mockito.mock(AppointmentService.class);
        Mockito.when(service.createdAppointment(Mockito.any(CreateAppointmentBodyDto.class)))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        AppointmentController controller = new AppointmentController(service);

        HttpResponse<Single<CreatedAppointmentResponseMessage>> response = controller.createdAppointment(headers, new CreateAppointmentBodyDto());

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getAppointmentDto());
    }

    @Test
    public void createdAppointmentWithoutError_withoutHeaders() {

        AppointmentService service = Mockito.mock(AppointmentService.class);

        AppointmentController controller = new AppointmentController(service);

        HttpResponse<Single<CreatedAppointmentResponseMessage>> response = controller.createdAppointment(null, new CreateAppointmentBodyDto());

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(response.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(response.getBody().get().blockingGet().getAppointmentDto());
    }
}
