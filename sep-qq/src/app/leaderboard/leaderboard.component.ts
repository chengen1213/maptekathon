import { Component, OnInit,EventEmitter } from '@angular/core';
import { QuestionService }  from '../question.service';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import {DatePipe} from "@angular/common";
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { FileUploader,FileLikeObject } from 'ng2-file-upload';
import { FormGroup, FormControl, FormArray } from '@angular/forms';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {
  

  constructor() { }

  ngOnInit() {
  }



}
