package su.medsoft.mis.er.terminal.services;

import static org.junit.Assert.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.reactivex.Single;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import su.medsoft.mis.er.terminal.repositories.interfaces.ScheduleRepository;
import su.medsoft.mis.er.terminal.responseMessages.ScheduleResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.IntervalService;
import su.medsoft.mis.er.terminal.services.interfaces.ScheduleService;

import java.util.ArrayList;

public class ScheduleServiceTest {

    @Test
    public void getScheduleWithoutErrorTest() throws Exception {
        ScheduleRepository scheduleRepository = Mockito.mock(ScheduleRepository.class);
        IntervalService intervalService = Mockito.mock(IntervalService.class);

        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository, intervalService);

        Mockito.when(scheduleRepository.getSchedule(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(new ArrayList<>());

        Mockito.when(intervalService.getIntervals(Mockito.anyList(), Mockito.anyBoolean(), Mockito.anyString()))
                .thenReturn(new ArrayList<>());

        HttpResponse<Single<ScheduleResponseMessage>> schedule = scheduleService.getSchedule(null, null, 1, null);

        assertEquals(schedule.getStatus(), HttpStatus.OK);
        assertNotNull(schedule.getBody().get().blockingGet().getScheduleDtoList());
        assertNull(schedule.getBody().get().blockingGet().getErrorDto());
    }

    @Test
    public void getScheduleWithErrorTest() throws Exception {
        ScheduleRepository scheduleRepository = Mockito.mock(ScheduleRepository.class);
        IntervalService intervalService = Mockito.mock(IntervalService.class);

        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository, intervalService);

        Mockito.when(scheduleRepository.getSchedule(Mockito.any(), Mockito.anyLong(), Mockito.any()))
                .thenReturn(null);

        Mockito.when(intervalService.getIntervals(Mockito.anyList(), Mockito.anyBoolean(), Mockito.anyString()))
                .thenReturn(null);

        HttpResponse<Single<ScheduleResponseMessage>> schedule = scheduleService.getSchedule(null, null, 1, null);

        assertEquals(schedule.getStatus(), HttpStatus.BAD_REQUEST);
        assertNull(schedule.getBody().get().blockingGet().getScheduleDtoList());
        assertNotNull(schedule.getBody().get().blockingGet().getErrorDto());
    }

}
