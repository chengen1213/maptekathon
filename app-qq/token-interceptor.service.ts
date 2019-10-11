import { Injectable } from '@angular/core';
import {HttpInterceptor} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor  {

  constructor() { }

  intercept(req:any,next:any)
  {
    let tokenizedReq = req.clone({

      setHeaders:
      {
        Authorization: 'Bearer cc.yy.zz'
      }
    })

    return next.handle(tokenizedReq)
  }
}
