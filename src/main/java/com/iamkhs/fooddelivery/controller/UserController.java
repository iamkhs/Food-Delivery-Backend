package com.iamkhs.fooddelivery.controller;

import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.entity.User;
import com.iamkhs.fooddelivery.service.UserService;
import com.iamkhs.fooddelivery.service.impl.EmailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/food-app/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    /**
     * Save user information and send verification email
     *
     * @param user     the user object to be saved
     * @param request  the HTTP servlet request object
     * @return a response entity with the saved user object and HTTP status code 201 (CREATED)
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody @Valid User user, HttpServletRequest request){
        User savedUser = this.userService.saveUser(user);
        String mailBody = emailSenderService.createVerificationMailBody(savedUser, request);

        CompletableFuture.runAsync(()-> emailSenderService.sendMail(user.getEmail(), mailBody));

        executorService.schedule(()->{
            // if the user still not verify his / her account in 5 minute then delete the user from database
            UserDto currentUser = userService.getUser(savedUser.getId().toString());
            if (!currentUser.enable()){
                userService.deleteUser(savedUser.getId().toString());
                logger.info("User deleted for not verify his / her account within 5 minutes");
            }
        }, 5, TimeUnit.MINUTES);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        UserDto user = this.userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<User> allUsers(){
        return this.userService.allUsers();
    }

    @GetMapping("/save/verify")
    public void verifyUser(@RequestParam String code, HttpServletResponse response) throws IOException {
        boolean verified = this.userService.isUserVerified(code);
        if (verified){
            response.sendRedirect("http://localhost:4200/login");
            logger.info("User verified Successfully, redirecting to login page");
        }else {
            response.sendRedirect("http://localhost:4200/signup");
            logger.error("User verification Field! redirecting to signup page");
        }
    }
}
