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
  private serviceUrl = '/api/summary';
  private dataPostTestUrl = '/api/match';
  private filterMatchesUrl = '/api/filtermatches';
  private tableEvent: Subject<any> = new Subject<any>();

  constructor(private http: HttpClient) {
  }

  // HTTP request to get all clubs from backend
  public getClubs() {
    return this.http.get(this.clubsUrl);
  }

  public sortPoints() {
    return this.http.get(this.sortPointsUrl);
  }

  public sortGoals() {
    return this.http.get(this.sortGoalsUrl);
  }

  public sortWins() {
    return this.http.get(this.sortWinsUrl);
  }

  public getMatches() {
    return this.http.get(this.matchesUrl);
  }

  public sortMatches() {
    return this.http.get(this.sortMatchesUrl);
  }

  public postRandomMatch(): Observable<any> {
    return this.http.post(this.matchUrl, {});
  }

  public listenTableEvent() {
    return this.tableEvent.asObservable();
  }

  public tableEventObserver(value: any) {
    this.tableEvent.next(value);
  }

  public searchMatch(date: string) {
    return this.http.post(this.filterMatchesUrl, {date});
  }
}
