package su.medsoft.mis.er.terminal.services.interfaces;

import su.medsoft.mis.er.terminal.dto.AggregateIntervalDto;

import java.util.List;

public interface IntervalService {
    List<AggregateIntervalDto> getIntervals(List<Long> schedule_day_ids, Boolean aggregate, String source) throws Exception;
}
