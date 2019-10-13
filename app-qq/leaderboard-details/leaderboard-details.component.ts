import { Component, OnInit,EventEmitter } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { FileUploader,FileLikeObject } from 'ng2-file-upload';
import {DatePipe} from "@angular/common"
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';
import { Location } from '@angular/common';
import { FormGroup, FormControl, FormArray } from '@angular/forms';

import { ActivatedRoute } from '@angular/router';
import { QuestionService }  from '../question.service';





@Component({
  selector: 'app-leaderboard-details',
  templateUrl: './leaderboard-details.component.html',
  styleUrls: ['./leaderboard-details.component.css']
})
export class LeaderboardDetailsComponent implements OnInit {
  basicUrl:any = 'http://localhost:8080/problems';
  solutions: any;

  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
  ) { }

  ngOnInit(): void {
    this.getSolutions();  
    
  }
  getSolutions(){
    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
     const url = `${this.basicUrl}/${id}/solutions`;
     console.log(url);
     this.questionService.getSolutions(id).subscribe(data => {
       this.solutions = data;
       console.log(this.solutions);
   });
  }




}
