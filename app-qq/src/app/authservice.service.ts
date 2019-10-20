import { Component, OnInit,Injectable  } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';;

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
   authenticate_api:any = 'http://localhost:8080/authenticate';
  constructor(private http:HttpClient) { }

  login_user(userName,password)
  {
    return this.http.post(this.authenticate_api,{'email':userName,'password':password})
  }

  getToken()
  {
    return localStorage.getItem('token');
  }
}