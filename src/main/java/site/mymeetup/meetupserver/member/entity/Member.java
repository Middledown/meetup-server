package site.mymeetup.meetupserver.member.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import site.mymeetup.meetupserver.MemberAndInterest.entity.MemberAndInterest;
import site.mymeetup.meetupserver.common.Role;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import site.mymeetup.meetupserver.geo.entity.Geo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Getter
@NoArgsConstructor
@Table(name = "member")

//UserDetails 관련 우선 주석 처리함
    //public class Member implements UserDetails {
    public class Member {

    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long memberId;

    //핸드폰으로 로그인
    @Column(unique = true, nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geo_id", nullable = false)
    private Geo geo;

    @Column(unique = true)
    private String kakao;

    @Column(unique = true)
    private String naver;

    private String password;

    @Column(nullable = false)
    private String nickname;

    private String intro;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private int status;

    private LocalDateTime dead_date;

    private String originalImg;

    private String saveImg;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_and_interest_id", nullable = false)
    public MemberAndInterest memberAndInterest;

    //== 생성자 ==//
    @Builder
    public Member(String phone, String nickname, String birth, int gender, Role role, int status, Geo geo, MemberAndInterest memberAndInterest, String originalImg, String saveImg) {
        this.phone = phone;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.geo = geo;
        this.memberAndInterest = memberAndInterest; // 단일 관심사 설정
        this.originalImg = originalImg;
        this.saveImg = saveImg;
    }

    //==update==//
    public void updateMember(String phone, String password){
        this.phone = phone;
        this.password = password;
    }

    //========== UserDetails implements ==========//
    /**
     * Token을 고유한 phone 값으로 생성
     */
//    @Override
//    public static String getUsername() {
//        return phone;
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add( new SimpleGrantedAuthority("ROLE_"+this.role.name()));
//        return authorities;
//    }
//
//        @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

}


