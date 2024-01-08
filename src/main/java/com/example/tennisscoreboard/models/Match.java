package com.example.tennisscoreboard.models;


import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Matches")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player_2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Match match = (Match) o;
        return getId() != null && Objects.equals(getId(), match.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
