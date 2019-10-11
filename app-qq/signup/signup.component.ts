import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user_signup_userName:any = "";
  user_signup_password:any = "";
  user_signup_email:any = "";

  return_userName:any = "";
  return_email:any = "";
  get_all:any[] = [];
  public api_register = 'http://localhost:8080/register';
  public api_authenticate = 'http://localhost:8080/authenticate';
  public api_hello_gradle = 'http://localhost:8080/greeting';
  public httpOptions = {
  headers: new HttpHeaders({'Content-Type':  'application/json' ,'Authorization': 'my-auth-token'})
};


  public new_httpheader:any = "";

  public return_token:string = '';


  constructor(public http:HttpClient) { }

  ngOnInit() {
  }

  getreturn_userName()
  {

    alert(this.return_userName);
  }

  getreturn_token(acc:any)
  {

    alert(acc);
  }

  doSignup()
  {
    this.http.post(this.api_register,{"userName":this.user_signup_userName ,"email":this.user_signup_email,"password":this.user_signup_password},this.httpOptions).subscribe((response:any)=>
    {
      this.return_userName = response.username;
      this.return_email = response.email;
      console.log(response);
    })
  }

  getToken()
  {
    this.http.post(this.api_authenticate,{"email":this.return_email ,"password":this.user_signup_password},this.httpOptions).subscribe((response:any)=>
    {
      this.return_token = response.token;
      console.log(response);
    })
  }

  getLoggedInUser(auth_token){
        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ${auth_token}'
        })
      this.http.get(this.api_hello_gradle).subscribe((response:any)=>
      {
          console.log(response);
      });

    }

  print_token()
  {
    alert(this.return_token);
  }

}
