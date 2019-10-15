import { Component, OnInit,EventEmitter } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { FileUploader,FileLikeObject } from 'ng2-file-upload';
import {DatePipe} from "@angular/common"
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';
import { Location } from '@angular/common';
import { FormGroup, FormControl, FormArray } from '@angular/forms';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';


import { ActivatedRoute } from '@angular/router';
import { QuestionService }  from '../question.service';





@Component({
  selector: 'app-leaderboard-details',
  templateUrl: './leaderboard-details.component.html',
  styleUrls: ['./leaderboard-details.component.css'],
  // styles:[`.popover-title{background-color:black;color:white}`],
  "styles": [
    "node_modules/bootstrap/dist/css/bootstrap.min.css"
  ]
})
export class LeaderboardDetailsComponent implements OnInit {
  basicUrl:any = 'http://localhost:8080/problems';
  solutions: {}[];
  searchValue = '';
  sortId: string | null = null;
  sortValue: string | null = null;

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
       
      
       for(var i =0;i<Object.keys(data).length;i++)
       {
        data[i].publicTimeComplexity=data[i].publicTimeComplexity+data[i].publicSpaceComplexity
                                                +data[i].timeComplexity+data[i].spaceComplexity;
       }
       data.sort(function(a, b){
            return a.publicTimeComplexity - b.publicTimeComplexity;
        });

       this.solutions = data as string[];

       
      //  console.log(this.solutions[0].id);
       console.log(typeof this.solutions);
    //    for(var i =0;i<Object.keys(this.solutions).length;i++)
    //    {
    //     this.solutions[i].publicTimeComplexity=this.solutions[i].publicTimeComplexity+this.solutions[i].publicSpaceComplexity
    //                                             +this.solutions[i].timeComplexity+this.solutions[i].spaceComplexity;
    //    }

    //   this.solutions.sort(function(a, b){
    //     return a.publicTimeComplexity - b.publicTimeComplexity;
    // });

   });
  }


  // reset(): void {
  //   this.searchValue = '';
  //   this.search();
  // }

  // sort(sortId: string, value: string): void {
  //   this.sortId = sortId;
  //   this.sortValue = value;
  //   this.search();
  // }


  // search(): void {
  //   // const filterFunc = (item: { ranking: number; Id: string;  score: number }) => {
  //   //   return (
  //   //     (this.listOfSearchAddress.length
  //   //       ? this.listOfSearchAddress.some(score => item.score!== -1)
  //   //       : true) && item.Id.indexOf(this.searchValue) !== -1
  //   //   );
  //   // };
  //   // const data = this.solutions.filter((item: { Id: string; ranking: number; score: number }) => filterFunc(item));
  //   this.solutions = this.solutions.sort((a, b) =>
  //     this.sortValue === 'ascend'
  //       ? a[this.sortId!] > b[this.sortId!]
  //         ? 1
  //         : -1
  //       : b[this.sortId!] > a[this.sortId!]
  //       ? 1
  //       : -1
  //   );
  // }



}

