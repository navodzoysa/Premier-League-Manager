import { Component, OnInit } from '@angular/core';
import { SportsClub } from '../../models/SportsClub';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-club-table',
  templateUrl: './club-table.component.html',
  styleUrls: ['./club-table.component.css']
})
export class ClubTableComponent implements OnInit {
  displayedColumns: string[] = ['clubName', 'matchesPlayed', 'wins', 'losses', 'draws',
                                'goalsScored', 'goalsReceived', 'goalDifference', 'points'];
  clubData: SportsClub[] = [];

  constructor(private appService: AppService) {}

  ngOnInit(): void {
    this.getClubsData();
  }

  public getClubsData() :void {
    this.appService.getClubs().subscribe((data: any) => {
      this.clubData = data.response.map((club: any) => ({
        clubName: club.clubName,
        matchesPlayed: club.matchesPlayed,
        wins: club.wins,
        losses: club.defeats,
        draws: club.draws,
        goalsScored: club.goalsScored,
        goalsReceived: club.goalsReceived,
        goalDifference: club.goalDifference,
        points: club.clubPoints,
      }));
      console.log(this.clubData);
    });
  }
}
