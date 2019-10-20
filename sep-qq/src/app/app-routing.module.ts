import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { QuestionDetailComponent } from './question-detail/question-detail.component';
import { SignupComponent } from './signup/signup.component';
import { CodingComponent } from './coding/coding.component';
import { QuestionsComponent } from './questions/questions.component';
import { LeaderboardComponent } from './leaderboard/leaderboard.component';
import { LeaderboardDetailsComponent } from './leaderboard-details/leaderboard-details.component';

const routes: Routes = [
  // { path: 'questions', component: QuestionsComponent },
  // { path: 'dashboard', component: DashboardComponent },
  // { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path:'dashboard',component:DashboardComponent },
  { path:'detail/:id',component:QuestionDetailComponent },
  { path:'signup', component:SignupComponent },
  { path:'detail/:id/:coding', component:CodingComponent },
  
  { path: 'questions', component: QuestionsComponent },
  { path: 'leaderboard', component: LeaderboardComponent },
  { path: 'leaderboard-details/:id', component: LeaderboardDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
