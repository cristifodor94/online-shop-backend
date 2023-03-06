package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.test.DatabaseManipulationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Profile("test")

public class TestController {
    private final DatabaseManipulationService databaseManipulationService;

    @PostMapping(value = "/populate")
    public void populateDatabase() {
        databaseManipulationService.fillInDatabase();
    }

    @PostMapping(value = "/clear")
    public void deleteDatabase() {
        databaseManipulationService.deleteAll();
    }
}
