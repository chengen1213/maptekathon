import { Injectable,Injector } from '@angular/core';
import {HttpInterceptor,HttpEvent,HttpRequest,HttpHandler} from '@angular/common/http';
import { AuthserviceService } from './authservice.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor  {

  constructor(private injector:Injector ) { }

  intercept(req:HttpRequest<any>,next:any)
  {
    let authService = this.injector.get(AuthserviceService);
    let tokenizedReq = req.clone({
      setHeaders:
      {
        //Authorization: `Bearer ${authService.getToken()}`
      }
    })
    if(req.url=="http://localhost:8080/register" ||req.url=="http://localhost:8080/authenticate")
    {
      //console.log(req.url);
    }
    else
    {
      //console.log(req.url);
      
      tokenizedReq = req.clone({
        // setHeaders:
        // {
        //   //content-type: 'text/plain',
        //   Authorization: `Bearer ${authService.getToken()}`
        // }
      })

      //console.log(tokenizedReq.headers);
    }
    return next.handle(tokenizedReq)

  }
}
