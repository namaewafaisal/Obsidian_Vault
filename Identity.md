install postgresql and start, create db, create user. add the url in application.yml file and username and password.
created a test entity
```java
import java.util.UUID;

@Entity
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
}
```
hibernate converts the TestUser from camlCase to snake_case. ddl-update checks the db if the tables exist and creates the table that does not exist.
```yaml
jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```


@Getter @Setter are better in lazy loading

`Controller -> service -> Repository -> DB`
repository gives default methods like findById etc but we have to add methods if needed like findByEmail(String email) where the camel case matters thats how spring parses and generates the query

