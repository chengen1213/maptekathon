import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-coding',
  templateUrl: './coding.component.html',
  styleUrls: ['./coding.component.css']
})
export class CodingComponent implements OnInit {

  solution:any = "#include <iostream>\nusing namespace std;\nint main() \n{\n\n}";
  api:any = "http://localhost:8080/problems/1/solutions";
  basicUrl="http://localhost:8080/problems";
  language = "cpp";

  public httpOptions = {
  headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
  };



  constructor(public http:HttpClient, 
    private route: ActivatedRoute,
    ) { }

  ngOnInit() {
  }
  
  post()
  {
    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
     const url = `${this.basicUrl}/${id}/solutions`;
     console.log(url);

    this.http.post(url,{"language":this.language,"code":this.solution},this.httpOptions).subscribe((response:any)=>
    {
      console.log(response);
    })
  }

  show()
  {
    console.log(this.solution);
  }

}
