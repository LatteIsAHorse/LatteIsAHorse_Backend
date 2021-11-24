package team.latte.LatteIsAHorse.model.franchisee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.coupon.Coupon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Franchisee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long franchiseeId;

    @OneToMany(mappedBy = "franchisee", cascade = CascadeType.ALL)
    private List<Coupon> coupon = new ArrayList<>();

    private String name;

    public static Franchisee createFranchisee(String name) {
        Franchisee franchisee = new Franchisee();
        franchisee.name = name;
        return franchisee;
    }
}
