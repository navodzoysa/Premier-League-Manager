import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/index';

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private clubsUrl = '/api/sportsclubs';
  private sortPointsUrl = '/api/sortpoints';
  private sortGoalssUrl = '/api/sortgoals';
  private sortWinsUrl = '/api/sortwins';
  private matchesUrl = '/api/matches';
  private matchUrl = '/api/match';
  private serviceUrl = '/api/summary';
  private dataPostTestUrl = '/api/match';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http get request to retrieve the welcome message from the backend service.
   */
  public getWelcomeMessage() {
    return this.http.get(this.serviceUrl).pipe(
      map(response => response)
    );
  }

  // HTTP request to get all clubs from backend
  public getClubs() {
    return this.http.get(this.clubsUrl);
  }

  public sortPoints() {
    return this.http.get(this.sortPointsUrl);
  }

  public sortGoals() {
    return this.http.get(this.sortGoalssUrl);
  }

  public sortWins() {
    return this.http.get(this.sortWinsUrl);
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public sendData(): Observable<any> {
    return this.http.post(this.dataPostTestUrl, {});
  }
}
