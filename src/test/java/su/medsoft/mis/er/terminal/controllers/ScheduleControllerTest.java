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
import su.medsoft.mis.er.terminal.responseMessages.ScheduleResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.ScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ScheduleControllerTest {

    private static HttpHeaders headers;

    @BeforeClass
    public static void initHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb0lkIjoxLCJzeXN0ZW1JZCI6IjA1ZjA0NmRkLTI3Y2YtNDdhOS1hMjA1LWJlYmI0MTNkOGRmMSIsInN5c3RlbU5hbWUiOiLQotC10YDQvNC40L3QsNC7INCc0LXQtNCh0L7RhNGCIiwiaXNzIjoibWVkc29mdCIsInN1YiI6ItCf0LXRgtGA0LXQu9C10LLQuNGHINCh0LXRgNCz0LXQuSJ9.Uyv0SzrNwd82KGUOjiIlkVxt8CghsmTWoI6STV1tBwo");

        headers = new SimpleHttpHeaders(header, new DefaultConversionService());
    }

    @Test
    public void getScheduleWithOutErrorTest() {

        ScheduleResponseMessage responseMessage = new ScheduleResponseMessage();
        responseMessage.setScheduleDtoList(new ArrayList<>());

        ScheduleService scheduleService = Mockito.mock(ScheduleService.class);
        Mockito.when(scheduleService.getSchedule(Mockito.any(),
                Mockito.any(),
                Mockito.anyLong(),
                Mockito.any()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        ScheduleController controller = new ScheduleController(scheduleService);

        HttpResponse<Single<ScheduleResponseMessage>> response = controller.getSchedule(headers, 1L, false, "String");

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getScheduleDtoList());
    }

    @Test
    public void getScheduleWithErrorTest() {

        ScheduleResponseMessage responseMessage = new ScheduleResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        ScheduleService scheduleService = Mockito.mock(ScheduleService.class);

        ScheduleController controller = new ScheduleController(scheduleService);

        HttpResponse<Single<ScheduleResponseMessage>> schedule = controller.getSchedule(null, null, null, null);

        assertNotNull(schedule.getBody());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
        assertEquals(schedule.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(schedule.getBody().get().blockingGet().getScheduleDtoList());
    }
}
