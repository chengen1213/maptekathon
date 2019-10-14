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
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css'],
})
export class QuestionDetailComponent implements OnInit {


  // name = 'Angular';
  // dataset: File;
  // resData: any;
  // selectedFile = null;
  public uploader:FileUploader = new FileUploader({
  });
  apiUpload:any = 'http://localhost:8080/uploadFile';
  apiGetQuestion:any = 'http://localhost:8080/problems';
  public httpOptions = {
  headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
};




  title = "";
  id:number;
	description = "";
  time_limit:number ;
  expirationDate =  "2019-02-03 10:08:02";

  publicDataset =  ["1"];
  uploaded_public: [];
  privateDataset = [];

  private formModel: FormGroup;

  private question: any;
  private questionID:number;
  // public question: any ;


  constructor(
    public http:HttpClient,
    private datePipe:DatePipe,
    private route: ActivatedRoute,
    private questionService: QuestionService,

    private location: Location,
    
   

    ) { 
      this.formModel = new FormGroup({
        title: new FormControl(),      
       
        time_limit: new FormControl(),
        description: new FormControl(),
        // publicDataset: new FormArray([
        //   new FormControl()
        // ]),
        // privateDataset: new FormArray([
        //   new FormControl()
        // ]),
       
      })


    }
 

  public onFileSelected(event: EventEmitter<File[]>) {
    const file: File = event[0];

    console.log(file);

  }


  ngOnInit(): void {
    this.getQuestion();  
    
  }
  goBack(): void {
    this.location.back();
  }
  

  getQuestion() {
    // const id = +this.route.snapshot.paramMap.get('id');
    this.questionID = this.questionService.getID();
    this.questionService.getQuestion(this.questionID).subscribe(data => {
      this.question = data;
      console.log(this.question);
  });
}

// delete(): void {
//   const id = +this.route.snapshot.paramMap.get('id');
//   this.questionService.deleteQuestion(this.questionID);
//   console.log(id);
//   // this.goBack();
// }



  show()
  {

    let file_2 = this.uploader.queue[0];
    console.log(file_2);
  }

  postDataset()
  {


    for(var i =0;i<this.uploader.queue.length;i++)
    {
      let data = new FormData();
      let file_2 = this.uploader.queue[i]._file;
      data.append('file',file_2,file_2.name);
      this.http.post(this.apiUpload,data).subscribe((response)=>
      { 

        // this.publicDataset=response as string [];
        // this.publicDataset=response.id;
        console.log(this.publicDataset);
       
      })
     
    }
    
    this.uploader = new FileUploader({
    });
  
    
  }

  
 //update question content// need more function from server
 mySubmit(){
  // console.log(this.formModel.value);

  
  // let nowTime = new Date();
  // let expirationDate_byms = nowTime.valueOf() + (this.formModel.value.time_limit*60*1000);
  // let endTime = new Date(expirationDate_byms);
  // let endTime_Formating =this.datePipe.transform(endTime,'yyyy-MM-dd HH:mm:ss');

  

  // this.http.post(this.apiGetQuestion,{"title":this.formModel.value.title,"description":this.formModel.value.description,"expirationDate":endTime_Formating,
  // "publicDataset":this.publicDataset,"privateDataset":this.privateDataset},this.httpOptions).subscribe((response:any)=>
  // {
  //   console.log(response);
  // })
  // // this.goBack();


}



  postProblem(title_in:string,description_in:string,time_lmt_it:number,)
  {
    let nowTime = new Date();
    let expirationDate_byms = nowTime.valueOf() + (this.formModel.value.time_limit*60*1000);
    let endTime = new Date(expirationDate_byms);
    let endTime_Formating =this.datePipe.transform(endTime,'yyyy-MM-dd HH:mm:ss');

    this.http.post(this.apiGetQuestion,{"title":this.formModel.value.title,"description":this.formModel.value.description,"expirationDate":endTime_Formating,
    "publicDataset":this.publicDataset,"privateDataset":this.privateDataset},this.httpOptions).subscribe((response:any)=>
  
    {
      console.log(response);
    })
    this.goBack();
  }

  get()
  {
    this.http.get(this.apiGetQuestion).subscribe((response:any)=>{
    console.log(response);
  })
  }













  downLoadPrivate(){

  }
  downLoadPublic(){

  }





  

}


  // onFileSelected(event) {
  //   this.selectedFile = event.target.files[0];
  //   console.log(this.selectedFile);
  // }
  // onSubmit() {
  //     const payload = new FormData();
  //     payload.append('name', this.name);
  //     payload.append('dataset', this.selectedFile, this.selectedFile.name);
  //
  //     this.http.post(`https://srinu.org/Api.php?apicall=upload_sub_cat`,
  //         payload, {
  //           headers: {
  //             'Content-Type': 'multipart/form-data'
  //           }
  //         }
  //       ).subscribe((data: any) => {
  //         this.resData = data;
  //         console.log(this.resData);
  //       });
  //   }
