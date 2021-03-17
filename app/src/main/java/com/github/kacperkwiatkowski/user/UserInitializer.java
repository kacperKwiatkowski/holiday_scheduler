//package com.github.kacperkwiatkowski.user;
//
//import com.github.kacperkwiatkowski.enums.RoleType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class UserInitializer {
//
//    private final UserFacade userFacade;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserInitializer(UserFacade userFacade, PasswordEncoder passwordEncoder) {
//        this.userFacade = userFacade;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    void init() {
//            if(userFacade.getAllUsers().size()==0) {
//                //CREATE ADMINS
//                userFacade.save(new UserSecurityDto("ola@gmail.com", passwordEncoder.encode("1234"), "Ola", "Olska", RoleType.ADMIN, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("ida@gmail.com", passwordEncoder.encode("1234"), "Ida", "Idowska", RoleType.ADMIN, 26, "profile-image"));
//
//                //CREATE TEAM LEADERS
//                userFacade.save(new UserSecurityDto("maria@gmail.com", passwordEncoder.encode("1234"), "Maria", "Marecka", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("wojciech@gmail.com", passwordEncoder.encode("1234"), "Wojciech", "Wojski", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("natalia@gmail.com", passwordEncoder.encode("1234"), "Natalia", "Natalska", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("grzegorz@gmail.com", passwordEncoder.encode("1234"), "Grzegorz", "Gregorowicz", RoleType.TEAM_LEADER, 26, "profile-image"));
//
//
//                //CREATE USERS
//                userFacade.save(new UserSecurityDto("adam@gmail.com", passwordEncoder.encode("1234"), "Adam", "Adamski", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("magda@gmail.com", passwordEncoder.encode("1234"), "Magda", "Magdanska", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("maciej@gmail.com", passwordEncoder.encode("1234"), "Maciej", "Mackowski", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("edyta@gmail.com", passwordEncoder.encode("1234"), "Edyta", "Edycka", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("ewa@gmail.com", passwordEncoder.encode("1234"), "Ewa", "Ewska", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("mariusz@gmail.com", passwordEncoder.encode("1234"), "Mariusz", "Marski", RoleType.EMPLOYEE, 26, "profile-image"));
//            }
//    }
//
//}
