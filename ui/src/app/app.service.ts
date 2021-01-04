import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { map } from 'rxjs/operators';
import {Observable, Subject} from 'rxjs/index';

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private clubsUrl = '/api/sportsclubs';
  private sortPointsUrl = '/api/sortpoints';
  private sortGoalsUrl = '/api/sortgoals';
  private sortWinsUrl = '/api/sortwins';
  private matchesUrl = '/api/matches';
  private sortMatchesUrl = '/api/sortmatches';
  private matchUrl = '/api/match';
  private filterMatchesUrl = '/api/filtermatches';
  private tableEvent: Subject<any> = new Subject<any>();

  constructor(private http: HttpClient) {}

  // HTTP request to get all clubs from backend
  public getClubs() {
    return this.http.get(this.clubsUrl);
  }
  // HTTP request to sort the clubs and get all clubs from backend
  public sortPoints() {
    return this.http.get(this.sortPointsUrl);
  }
  // HTTP request to sort the clubs and get all clubs from backend
  public sortGoals() {
    return this.http.get(this.sortGoalsUrl);
  }
  // HTTP request to sort the clubs and get all clubs from backend
  public sortWins() {
    return this.http.get(this.sortWinsUrl);
  }
  // HTTP request to get all matches from backend
  public getMatches() {
    return this.http.get(this.matchesUrl);
  }
  // HTTP request to sort the matches and get all matches from backend
  public sortMatches() {
    return this.http.get(this.sortMatchesUrl);
  }
  // HTTP request to add a random match then send back the added match
  public postRandomMatch(): Observable<any> {
    return this.http.post(this.matchUrl, {});
  }
  // HTTP request to sent a date to the backend and retrieve a filtered list of
  // matches for that date
  public searchMatch(date: string) {
    return this.http.post(this.filterMatchesUrl, {date});
  }

  public listenTableEvent() {
    return this.tableEvent.asObservable();
  }

  public tableEventObserver(value: any) {
    this.tableEvent.next(value);
  }
}
