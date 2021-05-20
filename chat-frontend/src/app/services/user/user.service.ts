import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

const url = 'http://localhost:9092';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  registerUser(user: any): void {
    console.log('user service - registerUser method');
    this.http
      .post(`${url}/auth/users/register`, user)
      .subscribe(user => {
        console.log(user);
      });
  }
}
