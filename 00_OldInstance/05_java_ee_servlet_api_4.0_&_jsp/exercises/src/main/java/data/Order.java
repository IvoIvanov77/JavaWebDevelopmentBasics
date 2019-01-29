package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Order {
    private String id;

    private User client;

    private Cat cat;

    private final LocalDateTime madeOn;

    public Order(User client, Cat cat) {
        this.id = UUID.randomUUID().toString();
        this.client = client;
        this.cat = cat;
        this.madeOn = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public Cat getCat() {
        return cat;
    }

    public LocalDateTime getMadeOn() {
        return madeOn;
    }

    public String getFormatedDate(){
        return this.getMadeOn()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
