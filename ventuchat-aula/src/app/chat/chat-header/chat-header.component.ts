import { Component, OnInit } from '@angular/core';
import { ChatService } from "app/chat/chat.service";

@Component({
  selector: 'app-chat-header',
  templateUrl: './chat-header.component.html',
  styleUrls: ['./chat-header.component.css']
})
export class ChatHeaderComponent {

  constructor(private service: ChatService) { }

  getUsername() {
    return this.service.name;
  }

  getLoginTime(){
    return this.service.time;
  }


}
