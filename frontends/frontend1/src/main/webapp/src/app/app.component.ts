import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  title = 'webapp';
  constructor(protected route: Router){}

  goToWelcome():void{
    this.route.navigate(['/welcome']);
  }
}
