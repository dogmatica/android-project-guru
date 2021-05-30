package com.example.projectguru.data;

public class DedicatedResource extends Resource {

    public static String assignEmail(Resource resource) {
        String resourceEmail = resource.getResource_email();
        return resourceEmail;
    }

    public static String assignMessage(Resource resource) {
        String resourceName = resource.getResource_name();
        String message = "Hello " + resourceName + ", you have been assigned to a work unit for an" +
                " ongoing project.";
        return message;
    }
}
