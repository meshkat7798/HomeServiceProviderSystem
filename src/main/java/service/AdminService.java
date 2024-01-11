package service;

import entity.SubService;
import entity.user.Specialist;

public interface AdminService {

    void addSpecialistToSubService(SubService service, Specialist specialist);

    void removeSpecialistFromSubService(SubService service, Specialist specialist);

    Specialist confirmSpecialist(int specialistId);

}

