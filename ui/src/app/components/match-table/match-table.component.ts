import {Component, OnInit, ViewChild} from '@angular/core';
import {Match} from "../../models/Match";
import {AppService} from "../../app.service";
import {MatTable} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements OnInit {
  displayedColumns: string[] = ['teamA', 'teamAScore', 'teamBScore', 'teamB', 'date', 'random'];
  matchesData: Match[] = [];
  addedMatch: Match | undefined;
  addMatchTeams: string | undefined;
  addMatchScore: string | undefined;
  addMatchDate: string | undefined;
  addMatchRand: string | undefined;
  @ViewChild('matchTable') table: MatTable<Match> | undefined;
  date: string | undefined;
  @ViewChild('date') dateInput: any;
  errorMessage: string | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

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

      this.addMatchTeams = "Teams: " + this.addedMatch.teamA + " vs " + this.addedMatch.teamB;
      this.addMatchScore = "Score: " + this.addedMatch.teamAScore + " - " + this.addedMatch.teamBScore;
      this.addMatchDate = "Date: " + this.addedMatch.date;
      this.addMatchRand = "Randomly Generated: " + this.addedMatch.randomMatch;
      // @ts-ignore
      this.table.renderRows();
      this.appService.tableEventObserver("update");
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

  public getFilteredMatches(value: string) {
    let dateRegex = /([0-2]\d{1}|3[0-1])\/(0\d{1}|1[0-2])\/\d{4}/;
    this.date = value;
    this.dateInput.nativeElement.value = '';
    if(this.date.match(dateRegex)) {
      this.appService.searchMatch(this.date).subscribe((data:any) => {
        if(data.response != "No matches found") {
          this.matchesData = data.response.map((match: any) => ({
            teamA: match.teamAName,
            teamAScore: match.teamAScore,
            teamBScore: match.teamBScore,
            teamB: match.teamBName,
            date: match.matchDate.day + '/' + match.matchDate.month + '/'
              + match.matchDate.year + ' at ' + match.matchDate.hour + ':' + match.matchDate.minute,
            randomMatch: match.isMatchRandom
          }));
          // @ts-ignore
          this.table.renderRows();
          this.errorMessage = undefined;
        }
        else {
          this.errorMessage = "No matches found for this date";
        }
      })
    }
    else{
      this.errorMessage = "Invalid date! Please enter a valid date";
    }
  }

  public resetTables() {
    this.getMatchesData();
    this.appService.tableEventObserver("update tables");
  }
}
