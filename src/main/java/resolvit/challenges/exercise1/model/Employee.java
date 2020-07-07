package resolvit.challenges.exercise1.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column(nullable = false)
    protected int level;

    @Column(nullable = false)
    private int quantity;

    @Column
    private String greetings;

    @OneToOne
    private Employee nextEmployee;

    public Employee() {
    }

    public Employee(String type, int level, int quantity, String greetings) {
        this.type = type;
        this.level = level;
        this.quantity = quantity;
        this.greetings = greetings;
    }

    public Optional<String> getGreetings() {
        if(this.quantity > 0){
            return Optional.of(greetings);
        }
        if(nextEmployee !=null){
            return nextEmployee.getGreetings();
        }
        return Optional.empty();
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Employee getNextEmployee() {
        return nextEmployee;
    }

    public void setNextEmployee(Employee nextEmployee) {
        this.nextEmployee = nextEmployee;
    }
}
