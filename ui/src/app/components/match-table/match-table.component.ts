import { Component, OnInit } from '@angular/core';
import {Match} from "../../models/Match";
import {AppService} from "../../app.service";

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements OnInit {
  displayedColumns: string[] = ['teamA', 'teamAScore', 'teamBScore', 'teamB', 'date', 'random'];
  matchesData: Match[] = [];

  constructor(private appSerivce: AppService) { }

  ngOnInit(): void {
    this.getMatchesData();
  }

  public getMatchesData(): void {
    this.appSerivce.getMatches().subscribe((data:any) => {
      this.matchesData = data.response.map((match:any) => ({
        teamA: match.teamAName,
        teamAScore: match.teamAScore,
        teamBScore: match.teamBScore,
        teamB: match.teamBName,
        date: match.matchDate.day + '/' + match.matchDate.month + '/'
          + match.matchDate.year + ' at ' + match.matchDate.hour + ':' + match.matchDate.minute,
        random: match.isMatchRandom
      }));
    });
    console.log(this.matchesData);
  }

  public addRandomMatch(): void {

  }
}
