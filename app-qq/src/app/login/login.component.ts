import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  log_email:any = "";
  log_password:any = "";
  public httpOptions = {
  headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
};

   api:any = 'http://localhost:8080/authenticate';

  constructor(public http:HttpClient) { }

  ngOnInit() {
  }
  // getData()
  // {
  //   let api = 'http://localhost:8080/greeting';
  //   this.http.get(api).subscribe((response:any)=>{
  //     console.log(response);
  //   })
  //
  // }
  Log()
  {
    this.http.post(this.api,{"email":this.log_email,"password":this.log_password},this.httpOptions).subscribe((response:any)=>
    { 
      localStorage.setItem('token',response.token);
      localStorage.setItem('role',response.role);
      console.log(response);
    })
  }

  
  send_token()
  {
    let hello_api ='http://localhost:8080/greeting';
    //this.httpOptions.headers.set('Authorization',localStorage.getItem('token'));
    //console.log(this.httpOptions);
    this.http.get(hello_api).subscribe((response:any)=>
    {
      console.log(response);
    })
  }

}
