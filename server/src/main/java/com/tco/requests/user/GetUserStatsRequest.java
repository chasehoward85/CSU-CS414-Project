package com.tco.requests.user;
import com.tco.requests.Request;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserStatsRequest extends Request {
    private Integer wins;
    private Integer losses;
    private Integer played;
    private String userName;
    private final transient Logger log = LoggerFactory.getLogger(GetUserStatsRequest.class);
    private ArrayList<String> features;
    @Override
    public void prepareResponse() {
        this.wins = 0;
        this.losses = 0;
        this.played = 0;
        this.features = new ArrayList<>();
        features.add("getStats");
        log.trace("buildResponse -> {}", this);
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setWins(Integer wins) {
        this.wins= wins;
    }
    public void setLosses(Integer losses) {
        this.losses = losses;
    }
    public void setGamesPlayed(Integer played) {
        this.played = played;
    }
    public String getUserName() {
        return userName;
    }
    public Integer getWins(){
        return this.wins;
    }
    public Integer getLosses(){
        return this.losses;
    }
    public Integer getGamesPlayed(){
        return this.played;
    }
  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */
  public GetUserStatsRequest() {
    this.requestType = "getStats";
    }
    public GetUserStatsRequest(String userName, Integer wins, Integer losses, Integer played) {
    this();
    this.userName = userName;
    this.wins = wins;
    this.losses = losses;
    this.played = played;
    }
    public boolean validFeature(String feature){
    return features.contains(feature);

}
}