package service.impl;

import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;
import repository.SpecialistRepository;
import service.SpecialistService;
import utility.InputHandling;
import utility.Validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SpecialistServiceImpl extends UserServiceImpl<Specialist, SpecialistRepository> implements SpecialistService {
    public SpecialistServiceImpl(SpecialistRepository repository) {
        super(repository);
    }

    @Override
    public Specialist setSpecialistInfo() {
        System.out.println("*** Please Fill The Form Below: ***");

        System.out.println("firstname:");
        String firstname = InputHandling.nameInput();

        System.out.println("lastname:");
        String lastname = InputHandling.nameInput();


        System.out.println("Please enter the path to the image file:");
        String imagePath = InputHandling.stringInput();
        byte[] profileImage = null;
        try {
            profileImage = Validation.readImageBinary(imagePath);
            while (!Validation.isValidImage(profileImage)){
                System.out.println("Image Type Should Be jpg And Size Should be Below 300KB. TRy Again:");
            imagePath = InputHandling.stringInput();
            profileImage = Validation.readImageBinary(imagePath);}
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error reading image file");
        }


        System.out.println("email:");
        String email = InputHandling.stringInput();
        while (!Validation.isValidEmail(email)) {
            System.out.println("Please Enter A Valid Email Address!");
            System.out.println("email:");
            email = InputHandling.stringInput();
            while (existByEmail(email)) {
                System.out.println("This Email Already Exists! Please Choose Another One:");
                System.out.println("email:");
                email = InputHandling.stringInput();
            }
        }

        System.out.println("Please Name Your specialities:");
        String specialities = InputHandling.sentenceInput();

        System.out.println("username:");
        String username = InputHandling.stringInput();
        while (existByUserName(username)) {
            System.out.println("This Username Already Exists! Please Choose Another One:");
            System.out.println("username:");
            username = InputHandling.stringInput();
        }
        System.out.println("password:");
        String password = InputHandling.stringInput();
        while (!Validation.isValidPassword(password)) {
            System.out.println("""
                    Password should be at least 8 characters\040
                    and must contain at least 1 sign(!@#$%), 1 number and 1 Capital letter!""");
            password = InputHandling.stringInput();
        }

        return new Specialist(firstname, lastname,email,username,password,profileImage,specialities);
    }

    public float averageScore(Specialist specialist){
        List<Integer> scores = specialist.getSpecialistScores();
        Integer sum = 0;
        for (Integer score: scores
             ) {
            sum += score;
        }

        return sum/(scores.size());
    }
    @Override
    public void showAllSpecialists(){
        List<Specialist> specialists = (List<Specialist>) findAll();
        for (Specialist specialist: specialists
        ) {
            System.out.print("ID "+specialist.getId()+": " + specialist);
            System.out.println();
        }
    }
    @Override
    public void showSpecialistsByStatus(SpecialistStatus specialistStatus){
        List<Specialist> specialists = loadBySpecialistStatus(specialistStatus);
        for (Specialist specialist: specialists
        ) {
            System.out.print("ID "+specialist.getId()+": " + specialist);
            System.out.println();
        }
    }

    @Override
    public List<Specialist> loadBySpecialistStatus(SpecialistStatus specialistStatus) {
        List<Specialist> statusSpecialists = new ArrayList<>();
        List<Specialist> specialists = (List<Specialist>) findAll();
        for (Specialist specialist: specialists
        ) { if (specialist.getSpecialistStatus().equals(specialistStatus)){
            statusSpecialists.add(specialist);
        }

        }
        return statusSpecialists;
    }

    @Override
    public void confirmSpecialist(int specialistId) {
        while (!existsById(specialistId) ||
                !findById(specialistId).getSpecialistStatus().equals(SpecialistStatus.NEW)) {
            System.out.println("Specialist With This Id does Not Exist Or Already Confirmed!");
            System.out.println("ID:");
            specialistId = InputHandling.integerInput();
        }
        Specialist specialist = findById(specialistId);
        specialist.setSpecialistStatus(SpecialistStatus.CONFIRMED);
        specialist.setId(specialist.getId());
        creatOrUpdate(specialist);
    }

    @Override
    public void addProfilePicture(Specialist specialist){
    System.out.println("Please enter the path to the image file:");
    String imagePath = InputHandling.stringInput();
    byte[] profileImage = null;
        try {
        profileImage = Validation.readImageBinary(imagePath);
        while (!Validation.isValidImage(profileImage)){
            System.out.println("Image Type Should Be jpg And Size Should be Below 300KB. TRy Again:");
            imagePath = InputHandling.stringInput();
            profileImage = Validation.readImageBinary(imagePath);}
    }catch (IOException e){
        e.printStackTrace();
        System.out.println("Error reading image file");
    }
        specialist.setProfilePicture(profileImage);
        specialist.setId(specialist.getId());
        creatOrUpdate(specialist);
        System.out.println("Image Added To Your Profile Successfully!");
    }





}
