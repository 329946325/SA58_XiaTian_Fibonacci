//package sg.nus.iss.jpa.oracle.resources;
//import com.codahale.metrics.annotation.Timed;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import java.util.*;
//
//@Path("/api/coins")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class CoinOptimizerResource {
//	private static final double[] VALID_DENOMINATIONS = {0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000};
//
//    @POST
//    @Path("/optimize")
//    @Timed
//    public Response getMinimumCoins(@QueryParam("targetAmount") double targetAmount, List<Double> coinDenominations) {
//        // Validate input
//        if (targetAmount < 0 || targetAmount > 10000) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid target amount").build();
//        }
//
//        for (double denomination : coinDenominations) {
//            if (!isValidDenomination(denomination)) {
//                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid coin denomination: " + denomination).build();
//            }
//        }
//
//        List<Double> result = optimizeCoins(targetAmount, coinDenominations);
//
//        Map<String, List<Double>> response = new HashMap<>();
//        response.put("coins", result);
//
//        return Response.ok(response).build();
//    }
//
//    private boolean isValidDenomination(double denomination) {
//        for (double validDenomination : VALID_DENOMINATIONS) {
//            if (denomination == validDenomination) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private List<Double> optimizeCoins(double targetAmount, List<Double> coinDenominations) {
//        Collections.sort(coinDenominations, Collections.reverseOrder());
//        List<Double> result = new ArrayList<>();
//
//        for (double denomination : coinDenominations) {
//            while (targetAmount >= denomination) {
//                targetAmount = Math.round((targetAmount - denomination) * 100.0) / 100.0;
//                result.add(denomination);
//            }
//        }
//
//        // Return result sorted by coin value in ascending order
//        Collections.sort(result);
//        return result;
//    }
//
//}
