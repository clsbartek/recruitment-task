package pl.empik.recruitmenttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.empik.recruitmenttask.domain.User;
import pl.empik.recruitmenttask.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a user by its login", description = "Returns a user data with calculation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - User has not been found"),
            @ApiResponse(responseCode = "409", description = "Calculation was not possible for requested user")
    })
    @GetMapping("/{login}")
    public User getUser(@PathVariable String login) {
        return userService.getUser(login);
    }
}
