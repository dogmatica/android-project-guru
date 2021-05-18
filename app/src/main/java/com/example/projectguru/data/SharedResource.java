package com.example.projectguru.data;

public class SharedResource extends Resource {

    MainDatabase db;

    public String requestEmail(int resource_id) {
        Resource resource = db.resourceDao().getResource(resource_id);
        String resourceEmail = resource.getResource_email();
        return resourceEmail;
    }

    public String requestMessage(int resource_id) {
        Resource resource = db.resourceDao().getResource(resource_id);
        String resourceName = resource.getResource_name();
        String message = "Hello " + resourceName + ", I am writing to ask about your ability to contribute to an ongoing" +
                " project.  Please advise me on your availability.";
        return message;
    }
}
