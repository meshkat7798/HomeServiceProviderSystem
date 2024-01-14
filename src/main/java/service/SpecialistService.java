package service;

import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;

import java.util.List;

@SuppressWarnings("unused")
public interface SpecialistService extends UserService<Specialist> {

    Specialist setSpecialistInfo();


    void showAllSpecialists();

    void showSpecialistsByStatus(SpecialistStatus specialistStatus);

    List<Specialist> loadBySpecialistStatus(SpecialistStatus specialistStatus);

    void confirmSpecialist(int specialistId);
}
