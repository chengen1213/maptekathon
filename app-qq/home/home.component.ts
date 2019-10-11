import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public http:HttpClient) { }

  ngOnInit() {
  }

  getData2()
  {
    let api = 'http://localhost:8080/greeting';
    this.http.jsonp(api,'callback').subscribe((response:any)=>
    {
      console.log(response);
    })
  }

}
