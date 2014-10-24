package com.oxiane.caveavin.rest;

public class ResourceTestContext {
// ------------------------------ FIELDS ------------------------------

    private final String url;
    private final String json;
    private final String id;

// --------------------------- CONSTRUCTORS ---------------------------

    public ResourceTestContext(String url, String id, String json) {
        this.url = url;
        this.id = id;
        this.json = json;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getId() {
        return id;
    }

    public String getJson() {
        return json;
    }

    public String getUrl() {
        return url;
    }

// -------------------------- PUBLIC METHODS --------------------------

    public String getIdUrl() {
        return url + "/" + id;
    }
}

