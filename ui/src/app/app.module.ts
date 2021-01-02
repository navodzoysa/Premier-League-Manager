import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppService } from './app.service';
import { AppHttpInterceptorService } from './http-interceptor.service';
import {MatTableModule} from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClubTableComponent } from './components/club-table/club-table.component';
import { MatchTableComponent } from './components/match-table/match-table.component';
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    ClubTableComponent,
    MatchTableComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    MatTableModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatPaginatorModule,
  ],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
