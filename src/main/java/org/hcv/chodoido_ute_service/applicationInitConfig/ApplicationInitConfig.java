package org.hcv.chodoido_ute_service.applicationInitConfig;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.entity.*;
import org.hcv.chodoido_ute_service.repository.MissionRepository;
import org.hcv.chodoido_ute_service.repository.ServicePackageRepository;
import org.hcv.chodoido_ute_service.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    ServicePackageRepository servicePackageRepository;
    MissionRepository missionRepository;

    public static Role ROLE_DEFAULT = Role.ADMIN;
    public static Long SERVICE_PACKAGE_TIME_DEFAULT = 2592000000L;  //24*60*60*1000*30 30DAY => (milis)
    public static ServicePackage SERVICE_PACKAGE_DEFAULT = ServicePackage.builder().name("Người mới").time(SERVICE_PACKAGE_TIME_DEFAULT).point(0L).description("Khi người dùng tạo tài khoản mơí.").countPost(3L).build();
    public static Mission MISSION_DANGBAITHANHCONG = new Mission(0L, "Mua hàng thành công", "Khi ngươi dùng mua hàng thành công", 50L, MissionType.NGAY,  true, new ArrayList<>());
    public static Mission MISSION_MUAHANGTHANHCONG = new Mission(0L, "Đăng bài viết", "Khi ngươi dùng đăng bài viết thành công", 20L, MissionType.NGAY,  true, new ArrayList<>());
    public static Mission MISSION_BANHANGTHANHCONG = new Mission(0L, "Bán hàng thành công", "Khi ngươi dùng đăng bài viết, và có người mua thành công", 20L, MissionType.NGAY,  true, new ArrayList<>());
    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(){
        log.info("Application initial");
        return args -> {
            initUser();
            initServicePackage();
            initMission();


            log.info("Application initialization completed.....");
        };
    }
// diem danh
    private void initMission() {
        if(!missionRepository.findByNameAndMissionType("Điểm danh", MissionType.NGAY)){
            missionRepository.save(new Mission(0L, "Điểm danh ngày 1", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 10L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 2", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 20L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 3", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 30L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 4", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 40L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 5", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 50L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 6", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 60L, MissionType.NGAY,  true, new ArrayList<>()));
            missionRepository.save(new Mission(0L, "Điểm danh ngày 7", "Khi ngươi dùng tiến hành điểm danh sẽ được điểm thưởng", 70L, MissionType.NGAY,  true, new ArrayList<>()));
        }

        if(!missionRepository.isExitByName(MISSION_DANGBAITHANHCONG.getName()))
            MISSION_DANGBAITHANHCONG = missionRepository.save(MISSION_DANGBAITHANHCONG);
        if(!missionRepository.isExitByName(MISSION_MUAHANGTHANHCONG.getName()))
            MISSION_MUAHANGTHANHCONG = missionRepository.save(MISSION_MUAHANGTHANHCONG);
        if(!missionRepository.isExitByName(MISSION_BANHANGTHANHCONG.getName()))
            MISSION_BANHANGTHANHCONG = missionRepository.save(MISSION_BANHANGTHANHCONG);
    }

    private void initServicePackage() {
        Optional<ServicePackage> servicePackage = servicePackageRepository.findByName(SERVICE_PACKAGE_DEFAULT.getName());
        if(servicePackage.isEmpty()){
            servicePackageRepository.save(SERVICE_PACKAGE_DEFAULT);
            log.info("Service for user default add success");
        }
    }

    private void initUser() {
        String USER_ADMIN = "admin@gmail.com";
        String PASS_ADMIN = "admin";
        if(userRepository.findByEmail(USER_ADMIN).isEmpty()){
            User user = userRepository.save(User
                    .builder()
                    .email(USER_ADMIN)
                    .password(passwordEncoder.encode(PASS_ADMIN))
                    .role(ROLE_DEFAULT)
                    .build());

            userRepository.save(user);
            log.warn("User admin has been created with default email: " + USER_ADMIN + " password: " + PASS_ADMIN + " ,please change it");
        }
    }
}
