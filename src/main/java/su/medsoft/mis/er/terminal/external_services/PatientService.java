package su.medsoft.mis.er.terminal.external_services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micronaut.context.annotation.Value;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import su.medsoft.mis.er.terminal.dto.InsuranceDto;
import su.medsoft.mis.er.terminal.dto.MpiPatientDto;
import su.medsoft.mis.er.terminal.dto.PatientDto;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class PatientService {
    private final PatientServicesAPI api;

    public PatientService(@Value("${external-services.patient.url}") String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        api = retrofit.create(PatientServicesAPI.class);
    }

    public List<MpiPatientDto> getPatientByInsuranceNumber(InsuranceDto insuranceDto, boolean inMPI) throws IOException {
        return api.getPatientByInsuranceNumber(insuranceDto, inMPI).execute().body();
    }

    public PatientDto getPatientInfo(MpiPatientDto mpiPatientDto) throws IOException {
        return api.getPatientInfo(mpiPatientDto).execute().body();
    }

    public Long createPatient(PatientDto patientDto) throws IOException {
        return api.createPatient(patientDto).execute().body();
    }

    public List<MpiPatientDto> getPatientByENP(String enp, boolean inMPI) throws IOException {
        return api.getPatientByENP(enp, inMPI).execute().body();
    }
}
