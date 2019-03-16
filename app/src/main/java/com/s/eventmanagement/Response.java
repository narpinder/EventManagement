package com.s.eventmanagement;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

private String status;
private String path;
private List<Data> data = null;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getPath() {
return path;
}

public void setPath(String path) {
this.path = path;
}

public List<Data> getData() {
return data;
}

public void setData(List<Data> data) {
this.data = data;
}

}