import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { QuestionService } from '../question.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  private questions:any[] = [];
  apiGetQuestion:any = 'http://localhost:8080/problems';

  constructor(public questionService:QuestionService, public http:HttpClient) { }
  
  ngOnInit() {
    // this.http.get(this.apiGetQuestion).subscribe(
    //   data => {
    //     this.questions = data as string [];	 // FILL THE ARRAY WITH DATA.
    //     //  console.log(this.questions[1]);
    //   },
    //   (err: HttpErrorResponse) => {
    //     console.log (err.message);
    //   }
    // );
    this.getQuestions();   
  }
  getQuestions(): void {
    this.questionService.getQuestions().subscribe(questions => this.questions = questions.slice(0, 4));
  }
 
  }

