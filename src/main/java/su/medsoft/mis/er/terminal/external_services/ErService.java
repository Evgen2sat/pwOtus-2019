package su.medsoft.mis.er.terminal.external_services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micronaut.context.annotation.Value;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.gson_adapters.LocalDateAdapter;
import su.medsoft.mis.er.terminal.gson_adapters.LocalDateTimeAdapter;
import su.medsoft.mis.er.terminal.gson_adapters.LocalTimeAdapter;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.PostResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.SlotResponseMessage;

import javax.inject.Singleton;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Singleton
public class ErService {
    private final ErServicesAPI api;

    public ErService(@Value("${external-services.er.url}") String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        api = retrofit.create(ErServicesAPI.class);
    }

    public PostResponseMessage getPosts(long moId, Long separateDepartId, String channel) throws IOException {
        return api.getPosts(moId, separateDepartId, channel).execute().body();
    }

    public DoctorResponseMessage getDoctors(long moId, String postCode, String channel, Long departmentId) throws IOException {
        return api.getDoctors(moId, postCode, channel, departmentId).execute().body();
    }

    public SlotResponseMessage getSlots(String channel, Long departmentId, long doctorId, String beginDate, String endDate, long moId) throws IOException {
        return api.getSlots(channel, departmentId, doctorId, beginDate, endDate, moId).execute().body();
    }

    public CreatedAppointmentResponseMessage createAppointment(CreateAppointmentBodyDto createAppointmentBodyDto) throws IOException {
        return api.createAppointment(createAppointmentBodyDto).execute().body();
    }
}
