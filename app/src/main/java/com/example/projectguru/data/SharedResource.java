package com.example.projectguru.data;

public class SharedResource extends Resource {

    public static String requestEmail(Resource resource) {
        String resourceEmail = resource.getResource_email();
        return resourceEmail;
    }

    public static String requestMessage(Resource resource) {
        String resourceName = resource.getResource_name();
        String message = "Hello " + resourceName + ", I am writing to ask about your ability to contribute to an ongoing" +
                " project.  Please advise me on your availability.";
        return message;
    }
}
