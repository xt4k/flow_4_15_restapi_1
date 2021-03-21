package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private int dataId;

    @JsonProperty("email")
    private String dataEmail;

    @JsonProperty("first_name")
    private String dataFirstName;

    @JsonProperty("last_name")
    private String dataLastName;

    @JsonProperty("avatar")
    private String dataAvatar;

    @JsonProperty("url")
    private String supportUrl;

    @JsonProperty("text")
    private String supportText;

    @JsonProperty("job")
    private String job;

    private String password;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public User( ) {
        Faker faker = new Faker( );

        this.dataId = faker.hashCode( );
        this.dataFirstName = faker.name( ).firstName( );
        this.dataLastName = faker.name( ).lastName( );
        this.dataEmail = faker.internet( ).emailAddress( );
        this.dataAvatar = faker.avatar( ).image( );
        this.job = faker.job( ).position( );
        this.password = faker.internet( ).password( );

        this.supportUrl = faker.internet( ).url( );
        this.supportText = faker.lorem( ).sentence( );
    }

    @Override
    public String toString( ) {
        return "User{" +
                "\n, id=" + dataId +
                "\n, email='" + dataEmail + '\'' +
                "\n, firstName='" + dataFirstName + '\'' +
                "\n, lastName='" + dataLastName + '\'' +
                "\n, avatar='" + dataAvatar + '\'' +
                "\n, supportUrl='" + supportUrl + '\'' +
                "\n, supportText='" + supportText + '\'' +
                "\n, job='" + job + '\'' +
                '}';
    }

    public String getEmail( ) {
        return dataEmail;
    }

    public void setEmail(String s) {
        dataEmail = s;
    }

    public String getPassword( ) {
        return password;
    }

    public void setPassword(String psw) {
        password = psw;
    }

    public String getJob( ) {
        return job;
    }

    public String getName( ) {
        return dataFirstName;
    }
}

