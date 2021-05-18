package com.example.projectguru.data;

public class DedicatedResource extends Resource {

    MainDatabase db;

    public String assignEmail(int resource_id) {
        Resource resource = db.resourceDao().getResource(resource_id);
        String resourceEmail = resource.getResource_email();
        return resourceEmail;
    }

    public String assignMessage(int resource_id) {
        Resource resource = db.resourceDao().getResource(resource_id);
        String resourceName = resource.getResource_name();
        String message = "Hello " + resourceName + ", you have been assigned to a work unit for an" +
                " ongoing project.";
        return message;
    }
}
