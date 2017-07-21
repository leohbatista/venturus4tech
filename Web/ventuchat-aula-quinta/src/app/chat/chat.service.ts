import { Observable } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import { Http, Headers, Response } from "@angular/http";
import * as io from "socket.io-client"; 

import 'rxjs/Rx';

@Injectable()
export class ChatService {

  private _headers: Headers;
  private _url: string = ''

  name: string = '';
  logTime: Date;
  server = null;

  constructor(private _http: Http) { 

    while(this.name == '' || this.name == null)
      this.name = prompt("What is your name?");

    this.logTime = new Date();
    this._url = "http://172.24.30.53:3000";
    
    this._headers = new Headers();
    this._headers.append('Content-Type', 'application/json');
    this.server = io(this._url);
  }

  public getMessages(): Observable<any[]> {
    return this._http.get(this._url).map(res => res.json());
  }

   public sendMessage(message: any): Observable<Response> {
    return this._http.post(this._url, JSON.stringify(message), { headers: this._headers });
  }

}
