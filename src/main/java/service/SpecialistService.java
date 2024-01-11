package service;

import entity.user.Specialist;
@SuppressWarnings("unused")
public interface SpecialistService extends UserService<Specialist> {

    Specialist setSpecialistInfo();
}
