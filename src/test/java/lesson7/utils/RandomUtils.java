package lesson7.utils;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    Faker faker = new Faker();

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%s", faker.number().numberBetween(10, 30)),
            month = faker.options().option("January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October", "November", "December"),
            year = String.format("%s", faker.number().numberBetween(1920, 2024)),
            subject = faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                    "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History",
                    "Civics"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            picture = faker.options().option("picfortest.jpg", "picfortest2.jpg"),
            currentAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = selectCity(state);

    public String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };

    }


}
