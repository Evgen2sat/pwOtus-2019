package su.medsoft.mis.er.terminal.repositories;

import su.medsoft.mis.er.terminal.database.QueryParam;
import su.medsoft.mis.er.terminal.dto.AppointmentDayDto;
import su.medsoft.mis.er.terminal.mappers.AppointmentMapper;
import su.medsoft.mis.er.terminal.services.DatabaseServiceImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class AppointmentRepository {
    @Inject
    private DatabaseServiceImpl databaseService;

    public List<AppointmentDayDto> getAppointmentsByPatient(long patient_id, String beginDate, String endDate) throws SQLException {

        StringBuilder queryBuilder = new StringBuilder(
                "SELECT er_appointment.id AS appointment_id, er_appointment.person_id AS patient_id, er_slot.begin_date::TIME AS begin_time,\n" +
                        "       (er_slot.end_date + INTERVAL '1' SECOND)::TIME AS end_time,\n" +
                        "       dict_er_schedule_type.code AS schedule_type_code, dict_er_schedule_type.name AS schedule_type_name,\n" +
                        "       er_schedule_day.id AS schedule_day_id, er_schedule_day.date,\n" +
                        "       person_staff.last_name AS staff_last_name, person_staff.first_name AS staff_first_name, person_staff.middle_name AS staff_middle_name,\n" +
                        "       dict_post.code AS post_code, dict_post.short_name AS post_name, er_schedule_day.room AS cabinet\n" +
                        "FROM er_appointment\n" +
                        "    INNER JOIN er_slot ON er_slot.id = er_appointment.slot_id\n" +
                        "    INNER JOIN er_schedule_interval ON er_schedule_interval.id = er_slot.schedule_interval_id\n" +
                        "    INNER JOIN dict_er_schedule_type ON dict_er_schedule_type.id = er_schedule_interval.schedule_type_id\n" +
                        "    INNER JOIN er_schedule_day ON er_schedule_day.id = er_schedule_interval.schedule_day_id\n" +
                        "    INNER JOIN staff ON staff.id = er_schedule_day.staff_id\n" +
                        "    INNER JOIN person AS person_staff ON person_staff.id = staff.person_id\n" +
                        "    INNER JOIN dict_post ON dict_post.id = staff.post_id\n" +
                        "    INNER JOIN department ON department.id = staff.department_id\n" +
                        "WHERE er_appointment.deleted = FALSE\n" +
                        "    AND er_appointment.person_id = ?\n" +
                        "    AND er_slot.deleted = FALSE\n" +
                        "    AND er_schedule_interval.deleted = FALSE\n" +
                        "    AND er_schedule_day.deleted = FALSE\n" +
                        "    AND department.deleted = FALSE\n" +
                        "    AND er_schedule_day.date BETWEEN ?::DATE AND ?::DATE\n");

        return databaseService.executeSelectQueryWithAllItems(queryBuilder.toString(),
                AppointmentMapper::appointment,
                QueryParam.getLong(patient_id),
                QueryParam.getString(beginDate),
                QueryParam.getString(endDate));
    }
}
