package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.SportsClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.PremierLeagueManager;
import utils.ApplicationUtil;

import java.io.IOException;
import java.util.List;

public class SportsClubController extends Controller {

    public Result listSportsClubs() throws IOException, ClassNotFoundException {
        List<SportsClub> result = PremierLeagueManager.getInstance().getClubList();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }
}
