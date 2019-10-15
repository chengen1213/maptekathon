import { Injectable } from '@angular/core';

import { HttpClient } from "@angular/common/http";
import {Observable, of} from "rxjs";
import { HttpErrorResponse } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  questions: string [];
  questionID: number;
  constructor(private http: HttpClient) {}
  apiGetQuestion:any = 'http://localhost:8080/problems';
  // public questionsUrl = 'api/questions';  // URL to web api
  public httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
  };
  

  ngOnInit () {
    // this.getQuestions;
    this.http.get(this.apiGetQuestion).subscribe(
      data => {
        this.questions = data as string [];	 // FILL THE ARRAY WITH DATA.
        //  console.log(this.questions[1]);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
   
  }

  getQuestion(id: number) {    
    const url = `${this.apiGetQuestion}/${id}`;
    return this.http.get<any>(url);
  }
  sendID(id: number){
    return   this.questionID=id;
  }
  getID(){    
    return this.questionID;
    console.log(this.questionID);
  }

  getQuestions()
  {   
    const url = `${this.apiGetQuestion}`;
    return this.http.get<any>(url);
  }

  /** DELETE: delete in the   questionlist page*/
deleteQuestion (id: number): Observable<any> {
  const url = `${this.apiGetQuestion}/${id}`;
  console.log(url);
  return this.http.delete<any>(url);

}

  // updateQuestion (question: any): Observable<any> {
  //   return this.http.put(this.apiGetQuestion, question, this.httpOptions).pipe(
  //     tap(_ => this.log(`updated question id=${question.id}`)),
      
  //   );
  // }

  // public httpOptions = {
  //   headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
  // };
 
  // /** Log a HeroService message with the MessageService */
  // private log(message: string) {
  //   this.messageService.add(`QuestionService: ${message}`);
  // }

getSolutions(id: number){
 
   const url = `${this.apiGetQuestion}/${id}/solutions`;
   console.log(url);   
  return this.http.get<any>(url);
  }
  
}


