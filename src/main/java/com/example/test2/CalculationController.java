package com.example.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calc")
public class CalculationController {
    @Autowired
    private CalculationRepository calculationRepository;
    
    @GetMapping
    public ResponseEntity<Map<String, Integer>> calculate(
            @RequestParam String type,
            @RequestParam int x,
            @RequestParam int y
    ) {
        int result = 0;
        switch (type) {
            case "sum":
                result = x + y;
                break;
            case "mult":
                result = x * y;
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        Calculation calculation = new Calculation();
        calculation.setType(type);
        calculation.setX(x);
        calculation.setY(y);
        calculation.setResult(result);
        calculationRepository.save(calculation);
        Map<String, Integer> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }
}
