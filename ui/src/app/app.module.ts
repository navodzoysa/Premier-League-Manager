import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

import { AppComponent } from './app.component';
// import { RouteExampleComponent } from './route-example/route-example.component';

import { AppService } from './app.service';
import { AppHttpInterceptorService } from './http-interceptor.service';
import {MatTableModule} from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClubTableComponent } from './components/club-table/club-table.component';

// const routes: Routes = [
//   {
//     path: 'java',
//     component: RouteExampleComponent,
//     data: { technology: 'Java' }
//   },
//   {
//     path: 'play',
//     component: RouteExampleComponent,
//     data: { technology: 'Play' }
//   },
//   {
//     path: 'angular',
//     component: RouteExampleComponent,
//     data: { technology: 'Angular' }
//   },
//   {
//     path: '**',
//     redirectTo: '/play',
//     pathMatch: 'full'
//   }
// ];

@NgModule({
  declarations: [
    AppComponent,
    ClubTableComponent,
    // RouteExampleComponent
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
    // RouterModule.forRoot(routes)
  ],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
