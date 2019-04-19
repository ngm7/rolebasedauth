package rolebasedauth;

import java.util.UUID;

public class Resource {
    String name;
    String uid;

    Resource(String name) {
        this.name = name;
        this.uid = UUID.randomUUID().toString();
    }
}
