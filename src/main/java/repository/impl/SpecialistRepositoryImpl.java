package repository.impl;

import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;
import repository.SpecialistRepository;
import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("unused")
public class SpecialistRepositoryImpl  extends UserRepositoryImpl<Specialist> implements SpecialistRepository {
    public SpecialistRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Specialist> getEntityClass() {
        return Specialist.class;
    }

}
