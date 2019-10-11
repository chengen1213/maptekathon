import { Component, OnInit } from '@angular/core';

import { QuestionService }  from '../question.service';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import {DatePipe} from "@angular/common";
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  constructor(
    public http:HttpClient,
    private questionService: QuestionService,
    private datePipe:DatePipe,    
    private location: Location,
    private route: ActivatedRoute,

    ) { }
 
  apiGetQuestion:any = 'http://localhost:8080/problems';
  public httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
  };

  arrQuestions: string [];
  title = "";
  id:number;
	description = "";
  time_limit=1;
	expirationDate =  "2019-01-01 00:00:00";
	publicDataset =  [];
  privateDataset = [];
 
  ngOnInit () {
    this.http.get(this.apiGetQuestion).subscribe(
      data => {
        this.arrQuestions = data as string [];	 // FILL THE ARRAY WITH DATA.
        //  console.log(this.arrQuestions[1]);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  }

  getQuestions(): void {
   
  }

  goBack(): void {
    this.location.back();
  }


  add(title_in:string): void {
    let nowTime = new Date();
    let expirationDate_byms = nowTime.valueOf() + (this.time_limit*60*1000);
    let endTime = new Date(expirationDate_byms);
    let endTime_Formating =this.datePipe.transform(endTime,'yyyy-MM-dd HH:mm:ss');

    this.title = title_in.trim();
    if (!this.title) { return; }
    this.http.post(this.apiGetQuestion,{"title":this.title,"description":this.description,"expirationDate":endTime_Formating,
    "publicDataset":this.publicDataset,"privateDataset":this.privateDataset},this.httpOptions).subscribe((response:any)=>
    {
      console.log(response);
    })
    this.goBack();

  }

  
  // deleteQuestion (question: Question | number): Observable<Question> {
  //   const id = typeof question === 'number' ? question : question.id;
  //   const url = `${this.questionsUrl}/${id}`;
  
  //   return this.http.delete<Question>(url, this.httpOptions).pipe(
  //     tap(_ => this.log(`deleted question id=${id}`)),
  //     catchError(this.handleError<Question>('deleteQuestion'))
  //   );
  // }

  delete(id: number): void {
   this.questionService.deleteQuestion(id).subscribe();
  }

}
