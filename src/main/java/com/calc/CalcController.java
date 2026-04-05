package com.calc;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin
public class CalcController {

    @PostMapping("/run")
    public Map<String, String> run(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        try {
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            java.io.PrintStream original = System.out;
            System.setOut(new java.io.PrintStream(out));

            new Interpreter().run(code);

            System.setOut(original);
            return Map.of("output", out.toString(), "error", "");
        } catch (CalcException e) {
            return Map.of("output", "", "error", e.getMessage());
        } catch (Exception e) {
            return Map.of("output", "", "error", "Unexpected error: " + e.getMessage());
        }
    }
}