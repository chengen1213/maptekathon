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
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  arrQuestions: string [];
  title = "";
  id:number;
	description = "";
  time_limit=1;
	expirationDate =  "2019-01-01 00:00:00";
	publicDataset =  [];
  privateDataset = [];
  uploaded_public=[];
  private formModel: FormGroup;
  // listOfDisplayData = [...Object.keys(this.arrQuestions)];
  listOfSearchAddress: string[] = [];
  searchValue = '';
  sortId: string | null = null;
  sortValue: string | null = null;
  listOfFilterAddress = [{ text: '90', value: '90' }, { text: '80', value: '80' }];


  public uploader:FileUploader = new FileUploader({
  });
  public onFileSelected(event: EventEmitter<File[]>) {
    const file: File = event[0];

    console.log(file);

  }
 
  apiGetQuestion:any = 'http://localhost:8080/problems';
  apiUpload:any = 'http://localhost:8080/uploadFile';
  public httpOptions = {
    headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
  };


  constructor(
    public http:HttpClient,
    private questionService: QuestionService,
    private datePipe:DatePipe,    
    private location: Location,
    private route: ActivatedRoute,

    ){ 
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



  role:string = ""; 
  role_admin:any = true;
  ngOnInit () {
    
    this.getQuestions(this.apiGetQuestion);
    this.role = localStorage.getItem('role');
    if (this.role=="ROLE_USER")
    {
      this.role_admin = false;
    }
    console.log(this.role_admin);
  }

  getQuestions(any): void {
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

  goBack(): void {
    this.location.back();
  }


  add(): void {
    let nowTime = new Date();
    let expirationDate_byms = nowTime.valueOf() + (this.formModel.value.time_limit*60*1000);
    let endTime = new Date(expirationDate_byms);
    let endTime_Formating =this.datePipe.transform(endTime,'yyyy-MM-dd HH:mm:ss');

    // this.title = title_in.trim();
    // if (!this.title) { return; }
    this.http.post(this.apiGetQuestion,{
      "title":this.formModel.value.title,
      "description":this.formModel.value.description,
      "expirationDate":endTime_Formating,
      "publicDataset":this.publicDataset,
      "privateDataset":this.privateDataset},
      this.httpOptions).subscribe((response:any)=>
    {
      console.log(response);
      this.getQuestions(this.apiGetQuestion);
      console.log(this.formModel.value.time_limit);
    })
    

  }

  //send the current question ID to questions.service
  sendID(id: number){
    return this.questionService.sendID(id);
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
   this.questionService.deleteQuestion(id).subscribe( ()=> {
    this.getQuestions(this.apiGetQuestion);
    });
  }
  


  show()
  {

    let file_2 = this.uploader.queue[0];
    console.log(file_2);
  }

  postPublicDataset()
  {
    for(var i =0;i<this.uploader.queue.length;i++)
    {
      let data = new FormData();
      let file_2 = this.uploader.queue[i]._file;
      data.append('file',file_2,file_2.name);
      this.http.post(this.apiUpload,data).subscribe((response)=>
      { 
        this.temp_q1=response;
        
        this.publicDataset.push(this.temp_q1.id);
        // this.publicDataset=response as string [];
        // this.publicDataset.push(response.id);
        console.log(this.publicDataset);
       
      })
     
    }
    
    this.uploader = new FileUploader({
    });   
  }

private temp_q1:Object={
 
    creationDate: null,
    description: "", 
    expirationDate: null, 
    id: null, 
    privateDataset: [],
     publicDataset: [],
     title: "" ,
};



  postPrivateDataset()
  {


    for(var i =0;i<this.uploader.queue.length;i++)
    {
      let data = new FormData();
      let file_2 = this.uploader.queue[i]._file;
      data.append('file',file_2,file_2.name);
      this.http.post(this.apiUpload,data).subscribe((response)=>
      { 
        this.temp_q1=response;
        // this.publicDataset=response as string [];
        // this.privateDataset.push(response.id);
        this.privateDataset.push(this.temp_q1.id);
        console.log(this.privateDataset);
       
      })
     
    }
    
    this.uploader = new FileUploader({
    });
  
    
  }


  private selectedLevel;


  data:Array<string> = [
      "0",
      "cpp",
      "c#",
      "java",
  ];
  
  selected(){
    console.log(this.selectedLevel)
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

  // filterAddressChange(value: string[]): void {
  //   this.listOfSearchAddress = value;
  //   this.search();
  // }

  // search(): void {
  //   const filterFunc = (item: { ranking: number; Id: string;  score: number }) => {
  //     return (
  //       (this.listOfSearchAddress.length
  //         ? this.listOfSearchAddress.some(score => item.score!== -1)
  //         : true) && item.Id.indexOf(this.searchValue) !== -1
  //     );
  //   };
  //   const data = this.arrQuestions.filter((item: { Id: string; ranking: number; score: number }) => filterFunc(item));
  //   this.listOfDisplayData = data.sort((a, b) =>
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
