import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  userRoutes: string[] = [
    'register',
    'login',
    'logout'
  ]
  title = 'chat-frontend';
}
