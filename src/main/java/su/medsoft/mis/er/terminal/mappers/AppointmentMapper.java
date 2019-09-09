package su.medsoft.mis.er.terminal.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.dto.AppointmentDayDto;
import su.medsoft.mis.er.terminal.dto.AppointmentDto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentMapper {

    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    public static List<AppointmentDayDto> appointment(ResultSet rs) {
        List<AppointmentDayDto> result = new ArrayList<>();
        Map<String, AppointmentDayDto> mapAppointmentDay = new HashMap<>();
        try {
            while (rs.next()) {
                String schedule_day = rs.getDate("date").toString();

                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.setAppointment_id(rs.getLong("appointment_id"));
                appointmentDto.setSchedule_day_id(rs.getLong("schedule_day_id"));
                appointmentDto.setStaff_last_name(rs.getString("staff_last_name"));
                appointmentDto.setStaff_first_name(rs.getString("staff_first_name"));
                appointmentDto.setStaff_middle_name(rs.getString("staff_middle_name"));
                appointmentDto.setPost_code(rs.getString("post_code"));
                appointmentDto.setPost_name(rs.getString("post_name"));
                appointmentDto.setBegin_time(rs.getTime("begin_time").toString());
                appointmentDto.setEnd_time(rs.getTime("end_time").toString());
                appointmentDto.setSchedule_type_code(rs.getString("schedule_type_code"));
                appointmentDto.setSchedule_type_name(rs.getString("schedule_type_name"));
                appointmentDto.setCabinet(rs.getString("cabinet"));

                if(mapAppointmentDay.containsKey(schedule_day)) {
                    AppointmentDayDto appointmentDayDto = mapAppointmentDay.get(schedule_day);

                    if(appointmentDayDto.getAppointmentDtoList() == null) {
                        appointmentDayDto.setAppointmentDtoList(new ArrayList<>());
                    }

                    appointmentDayDto.getAppointmentDtoList().add(appointmentDto);

                } else {
                    AppointmentDayDto appointmentDayDto = new AppointmentDayDto();
                    appointmentDayDto.setDate(schedule_day);
                    appointmentDayDto.setPatient_id(rs.getLong("patient_id"));

                    appointmentDayDto.setAppointmentDtoList(new ArrayList<AppointmentDto>() { {add(appointmentDto);} });

                    mapAppointmentDay.put(schedule_day, appointmentDayDto);
                }
            }

            for (Map.Entry<String, AppointmentDayDto> entry : mapAppointmentDay.entrySet()) {
                result.add(entry.getValue());
            }

            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
