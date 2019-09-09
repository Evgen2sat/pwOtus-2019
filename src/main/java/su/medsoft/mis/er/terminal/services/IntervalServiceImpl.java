package su.medsoft.mis.er.terminal.services;

import su.medsoft.mis.er.terminal.dto.AggregateIntervalDto;
import su.medsoft.mis.er.terminal.repositories.IntervalRepositoryImpl;
import su.medsoft.mis.er.terminal.repositories.interfaces.IntervalRepository;
import su.medsoft.mis.er.terminal.services.interfaces.IntervalService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class IntervalServiceImpl implements IntervalService {

    private final IntervalRepository intervalRepository;

    @Inject
    public IntervalServiceImpl(IntervalRepository intervalRepository) {
        this.intervalRepository = intervalRepository;
    }

    @Override
    public List<AggregateIntervalDto> getIntervals(List<Long> schedule_day_ids, Boolean aggregate, String source) throws Exception {
        return intervalRepository.getIntervals(schedule_day_ids, aggregate, source);
    }
}
