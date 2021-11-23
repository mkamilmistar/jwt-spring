package com.jwt.userservice;

import com.jwt.userservice.model.request.PostRoleToUserRequest;
import com.jwt.userservice.model.request.PostSaveRoleRequest;
import com.jwt.userservice.model.request.PostSaveUserRequest;
import com.jwt.userservice.service.PostRoleToUserService;
import com.jwt.userservice.service.PostSaveRoleService;
import com.jwt.userservice.service.PostSaveUserService;
import com.jwt.userservice.util.Constant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class);
	}

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Bean
  CommandLineRunner run (PostSaveUserService postSaveUserService, PostSaveRoleService postSaveRoleService,
                         PostRoleToUserService postRoleToUserService) {
    return args -> {
        postSaveRoleService.execute(new PostSaveRoleRequest(Constant.ROLE_USER));
        postSaveRoleService.execute(new PostSaveRoleRequest(Constant.ROLE_MANAGER));
        postSaveRoleService.execute(new PostSaveRoleRequest(Constant.ROLE_ADMIN));
        postSaveRoleService.execute(new PostSaveRoleRequest(Constant.ROLE_SUPER_ADMIN));

        postSaveUserService.execute(new PostSaveUserRequest("M. Kamil", "kamil", "1234", new ArrayList<>()));
        postSaveUserService.execute(new PostSaveUserRequest("Melia Suspariana", "melia", "1234", new ArrayList<>()));
        postSaveUserService.execute(new PostSaveUserRequest("Adhyfa Fahmi Hidayat", "adhyfa", "1234", new ArrayList<>()));
        postSaveUserService.execute(new PostSaveUserRequest("Indra Pratama Anugrah", "indra", "1234", new ArrayList<>()));

        postRoleToUserService.execute(new PostRoleToUserRequest("melia", Constant.ROLE_USER));
        postRoleToUserService.execute(new PostRoleToUserRequest("melia", Constant.ROLE_MANAGER));
        postRoleToUserService.execute(new PostRoleToUserRequest("adhyfa", Constant.ROLE_MANAGER));
        postRoleToUserService.execute(new PostRoleToUserRequest("indra", Constant.ROLE_ADMIN));
        postRoleToUserService.execute(new PostRoleToUserRequest("kamil", Constant.ROLE_ADMIN));
        postRoleToUserService.execute(new PostRoleToUserRequest("kamil", Constant.ROLE_SUPER_ADMIN));
        postRoleToUserService.execute(new PostRoleToUserRequest("kamil", Constant.ROLE_USER));
    };
  }

}
