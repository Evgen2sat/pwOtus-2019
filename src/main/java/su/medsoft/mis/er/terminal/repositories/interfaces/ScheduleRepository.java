package su.medsoft.mis.er.terminal.repositories.interfaces;

import su.medsoft.mis.er.terminal.dto.ScheduleDto;

import java.util.List;

public interface ScheduleRepository {
    List<ScheduleDto> getSchedule(Long departmentId, long moId, String source) throws Exception;
}
