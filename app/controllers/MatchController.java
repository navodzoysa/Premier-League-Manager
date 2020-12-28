package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Match;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PremierLeagueManager;
import utils.ApplicationUtil;
import java.io.IOException;
import java.util.List;

public class MatchController extends Controller {

    public Result create() throws IOException, ClassNotFoundException {
        Match match = PremierLeagueManager.getInstance().addRandomMatch();
        JsonNode jsonObject = Json.toJson(match);
        jsonObject.toPrettyString();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result listMatches() throws IOException, ClassNotFoundException {
        List<Match> result = PremierLeagueManager.getInstance().getMatchList();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }
}
