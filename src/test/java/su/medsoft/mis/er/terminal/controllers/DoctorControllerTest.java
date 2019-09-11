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
import su.medsoft.mis.er.terminal.dto.ErrorDto;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.DoctorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class DoctorControllerTest {

    private static HttpHeaders headers;

    @BeforeClass
    public static void initHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb0lkIjoxLCJzeXN0ZW1JZCI6IjA1ZjA0NmRkLTI3Y2YtNDdhOS1hMjA1LWJlYmI0MTNkOGRmMSIsInN5c3RlbU5hbWUiOiLQotC10YDQvNC40L3QsNC7INCc0LXQtNCh0L7RhNGCIiwiaXNzIjoibWVkc29mdCIsInN1YiI6ItCf0LXRgtGA0LXQu9C10LLQuNGHINCh0LXRgNCz0LXQuSJ9.Uyv0SzrNwd82KGUOjiIlkVxt8CghsmTWoI6STV1tBwo");

        headers = new SimpleHttpHeaders(header, new DefaultConversionService());
    }

    @Test
    public void getDoctorsWithoutErrorTest() {

        DoctorResponseMessage responseMessage = new DoctorResponseMessage();
        responseMessage.setDoctorDtoList(new ArrayList<>());

        DoctorService doctorService = Mockito.mock(DoctorService.class);
        Mockito.when(doctorService.getDoctors(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));


        DoctorController controller = new DoctorController(doctorService);
        HttpResponse<Single<DoctorResponseMessage>> response = controller.getDoctors(headers, "", "ТЕР", null);

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getDoctorDtoList());
    }

    @Test
    public void getDoctorsWithErrorTest_withoutHeaders() {

        DoctorService doctorService = Mockito.mock(DoctorService.class);

        DoctorController controller = new DoctorController(doctorService);

        HttpResponse<Single<DoctorResponseMessage>> response = controller.getDoctors(null, "", "ТЕР", null);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(response.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(response.getBody().get().blockingGet().getDoctorDtoList());
    }

    @Test
    public void getDoctorsWithErrorTest() {

        DoctorResponseMessage responseMessage = new DoctorResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        DoctorService doctorService = Mockito.mock(DoctorService.class);

        Mockito.when(doctorService.getDoctors(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(HttpResponse.serverError(Single.just(responseMessage)));

        DoctorController controller = new DoctorController(doctorService);

        HttpResponse<Single<DoctorResponseMessage>> response = controller.getDoctors(null, "", "ТЕР", null);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, response.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(response.getBody().get().blockingGet().getDoctorDtoList());
    }
}
