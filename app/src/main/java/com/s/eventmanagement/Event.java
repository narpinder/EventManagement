package com.s.eventmanagement;

import java.io.Serializable;

public class Event implements Serializable {

private Response response;

public Response getResponse() {
return response;
}

public void setResponse(Response response) {
this.response = response;
}

}