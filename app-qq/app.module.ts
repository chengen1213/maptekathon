import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FileUploadModule } from 'ng2-file-upload';
import { DatePipe, registerLocaleData } from '@angular/common';
import {Observable} from "rxjs";
import { ReactiveFormsModule } from '@angular/forms'



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { QuestionDetailComponent } from './question-detail/question-detail.component';
import { SignupComponent } from './signup/signup.component';
import { TokenInterceptorService } from './token-interceptor.service';
import { CodingComponent } from './coding/coding.component';
import { QuestionsComponent } from './questions/questions.component';
import { IconsProviderModule } from './icons-provider.module';
import { NgZorroAntdModule, NZ_I18N, en_US } from 'ng-zorro-antd';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import en from '@angular/common/locales/en';

registerLocaleData(en);

// {
//   provide:HTTP_INTERCEPTORS,
//   useClass:TokenInterceptorService,
//   multi:true
// }
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent,
    QuestionDetailComponent,
    SignupComponent,
    CodingComponent,
    QuestionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FileUploadModule,
    IconsProviderModule,
    NgZorroAntdModule,
    BrowserAnimationsModule,
    
    ReactiveFormsModule,    

  ],
  providers: [DatePipe, { provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
