import { any } from 'codelyzer/util/function';
import { Observable } from 'rxjs/Rx';
import { Headers, Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import * as io from 'socket.io-client';
import 'rxjs/Rx';

@Injectable()
export class ChatService {
  name: string = "";
  time: Date;
  url: string = 'http://localhost:3000/messages'; 
  private _headers: Headers;

  server = null;

  constructor(private _http: Http) {
    while (this.name == "" || this.name == null) {
      this.name = prompt("Qual é o seu nome?");
    }
    this.time = new Date();
    this._headers = new Headers();
    this._headers.append('Content-Type','application/json');
    this.server = io(this.url);    
   }

  getMessages(): Observable<any[]> {
    return this._http.get(this.url).map(res => res.json()); // Equivale a ( function(res) { return res.json; } )   ( param => return ) arrow function
  }

  sendMessage( message:any ) : Observable<Response> {
    return this._http.post(this.url,JSON.stringify(message), { headers: this._headers });
  }

}