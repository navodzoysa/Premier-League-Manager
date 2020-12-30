import {Component, OnInit, ViewChild} from '@angular/core';
import {Match} from "../../models/Match";
import {AppService} from "../../app.service";
import {MatTable} from "@angular/material/table";
import {SportsClub} from "../../models/SportsClub";

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements OnInit {
  displayedColumns: string[] = ['teamA', 'teamAScore', 'teamBScore', 'teamB', 'date', 'random'];
  matchesData: Match[] = [];
  addedMatch: Match | undefined;
  addMatchResponse: string | undefined;
  @ViewChild("matchTable") table: MatTable<Match> | undefined;

  constructor(private appService: AppService) {}

  ngOnInit(): void {
    this.getMatchesData();
  }

  public getMatchesData(): void {
    this.appService.getMatches().subscribe((data:any) => {
      this.matchesData = data.response.map((match:any) => ({
        teamA: match.teamAName,
        teamAScore: match.teamAScore,
        teamBScore: match.teamBScore,
        teamB: match.teamBName,
        date: match.matchDate.day + '/' + match.matchDate.month + '/'
          + match.matchDate.year + ' at ' + match.matchDate.hour + ':' + match.matchDate.minute,
        randomMatch: match.isMatchRandom
      }));
    });
  }

  public addRandomMatch(): void {
    this.appService.postRandomMatch().subscribe((data:any) => {
      let response = data.response;
      this.addedMatch = {} as Match;
      this.addedMatch.teamA = response.teamAName;
      this.addedMatch.teamAScore = response.teamAScore;
      this.addedMatch.teamBScore = response.teamBScore;
      this.addedMatch.teamB = response.teamBName;
      this.addedMatch.date = response.matchDate.day + '/' + response.matchDate.month + '/'
        + response.matchDate.year + ' at ' + response.matchDate.hour + ':' + response.matchDate.minute;
      this.addedMatch.randomMatch = response.isMatchRandom;
      this.matchesData.push(this.addedMatch);
      this.addMatchResponse = JSON.stringify(this.addedMatch);
      // @ts-ignore
      this.table.renderRows();
    });
  }

  public sortMatchDates() :void {
    this.appService.sortMatches().subscribe((data: any) => {
      this.matchesData = data.response.map((match: any) => ({
        teamA: match.teamAName,
        teamAScore: match.teamAScore,
        teamBScore: match.teamBScore,
        teamB: match.teamBName,
        date: match.matchDate.day + '/' + match.matchDate.month + '/'
          + match.matchDate.year + ' at ' + match.matchDate.hour + ':' + match.matchDate.minute,
        randomMatch: match.isMatchRandom
      }));
    });
    // @ts-ignore
    this.table.renderRows();
  }
}
