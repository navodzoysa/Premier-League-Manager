package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Match;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
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

    public Result sortMatches() throws IOException, ClassNotFoundException {
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
        premierLeagueManager.sortMatchDates();
        List<Match> result = premierLeagueManager.getMatchList();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    public Result filterMatches(Http.Request request) throws IOException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if(json == null ) {
            return badRequest("Expecting JSON data");
        }
        else{
            String date = json.findValue("date").textValue();
            System.out.println(date);
            List<Match> result = PremierLeagueManager.getInstance().filterMatchDates(date);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = Json.toJson("No matches found");
            if(!result.isEmpty()) {
                jsonData = mapper.convertValue(result, JsonNode.class);
            }
            return ok(ApplicationUtil.createResponse(jsonData, true));
        }
    }
}
