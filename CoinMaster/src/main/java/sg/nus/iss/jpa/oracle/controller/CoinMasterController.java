package sg.nus.iss.jpa.oracle.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.jpa.oracle.model.CoinRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinMasterController {

    @PostMapping("/calculate")
    public ResponseEntity<List<Double>> calculateCoin(@RequestBody CoinRequest request) {
        double targetAmount = request.getTargetAmount();
        List<Double> coinDenominations = request.getCoinDenominations();
        
        // 	Validate input
        if (targetAmount < 0 || targetAmount > 10000) {
            return ResponseEntity.badRequest().body(null);
        }

        // Calculate the minimum number of coins
        List<Double> changeResult = getCoins(targetAmount, coinDenominations);
        Collections.sort(changeResult);
        return ResponseEntity.ok(changeResult);
    }

    private List<Double> getCoins(double targetAmount, List<Double> coinDenominations) {
        
    	List<Double> changeResult = new ArrayList<>();
        Collections.sort(coinDenominations, Collections.reverseOrder());
        
        for (double coin : coinDenominations) {
            while (targetAmount >= coin) {
            	changeResult.add(coin);
                targetAmount -= coin;
                // Handle precision issues
                targetAmount = Math.round(targetAmount * 100.0) / 100.0; 
            }
        }
        return changeResult;
    }
}