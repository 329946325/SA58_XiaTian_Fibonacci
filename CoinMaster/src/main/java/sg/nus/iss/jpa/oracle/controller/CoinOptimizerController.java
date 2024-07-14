package sg.nus.iss.jpa.oracle.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sg.nus.iss.jpa.oracle.model.CoinRequest;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/coins")
public class CoinOptimizerController {

    @PostMapping("/optimize")
    public ResponseEntity<List<Double>> optimizeCoins(@RequestBody CoinRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> coinDenominations = request.getCoinDenominations();

        // Validate input
        if (targetAmount < 0 || targetAmount > 10000) {
            return ResponseEntity.badRequest().body(null);
        }

        // Calculate the minimum number of different coins
        List<Double> optimizedResult = getMinimumDifferentCoins(targetAmount, coinDenominations);
        Collections.sort(optimizedResult);
        return ResponseEntity.ok(optimizedResult);
    }

    private List<Double> getMinimumDifferentCoins(double targetAmount, List<Double> coinDenominations) {

    	// Initialize
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0); 
        
        Map<Integer, Integer> lastCoin = new HashMap<>();

        for (double coin : coinDenominations) {
            int coinValue = (int) (coin * 100);
            for (int amount = coinValue; amount <= (int) (targetAmount * 100); amount++) {
                if (dp.containsKey(amount - coinValue) && dp.get(amount - coinValue) + 1 < dp.getOrDefault(amount, Integer.MAX_VALUE)) {
                    dp.put(amount, dp.get(amount - coinValue) + 1);
                    lastCoin.put(amount, coinValue);
                }
            }
        }
          
        List<Double> changeResult = new ArrayList<>();
        
        // Input Validation
        if (!dp.containsKey((int) (targetAmount * 100)) || dp.get((int) (targetAmount * 100)) == Integer.MAX_VALUE) {
            System.out.println("No exact change found for " + targetAmount);
            return changeResult; 
        }
        
        for (int amount = (int) (targetAmount * 100); amount > 0; amount -= lastCoin.get(amount)) {
            changeResult.add(lastCoin.get(amount) / 100.0); 
        }

        return changeResult;
    }
}