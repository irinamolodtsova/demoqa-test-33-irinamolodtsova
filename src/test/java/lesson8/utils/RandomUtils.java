package lesson8.utils;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    Faker faker = new Faker();

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%s", faker.number().numberBetween(10, 30)),
            month = faker.options().option("January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October", "November", "December"),
            year = String.format("%s", faker.number().numberBetween(1920, 2024));
}
