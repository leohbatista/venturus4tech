import { Injectable } from '@angular/core';

@Injectable()
export class ChatService {
  name: string = "";
  time: Date;

  constructor() {
    while (this.name == "" || this.name == null) {
      this.name = prompt("Qual é o seu nome?");
    }
    this.time = new Date();
   }

}
