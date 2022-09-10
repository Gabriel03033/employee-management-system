package com.project.it.user;

import com.project.it.role.Role;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String address;
    private LocalDate birthday;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                mobile.equals(user.mobile) &&
                address.equals(user.address) &&
                birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, password, mobile, address, birthday);
    }
}
