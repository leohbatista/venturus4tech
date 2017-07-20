import { AfterViewChecked } from '@angular/core/src/metadata/lifecycle_hooks';
import { ViewChild } from '@angular/core/src/metadata/di';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ChatService } from "app/chat/chat.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit,AfterViewChecked {

  message: string = '';
  messageList: Object[] = [];

  @ViewChild('scroll') private myScrollContainer: ElementRef; // Semelhante ao document.getElementById()

  constructor(private chatService: ChatService) {
    this.chatService.getMessages().subscribe(
      (list) => this.messageList = list,
      (err) => console.error(err)
    );

    this.chatService.server.on('messages',function(message) {
      this.messageList.push(message);
    }); // Listener
  }

  isMyMessage(name) : boolean {
    return this.chatService.name == name;
  }

  public sendMessage(): void {
    var messageJson = { 
      message: this.message,
      time: new Date(),
      name: this.chatService.name
    }

    this.chatService.sendMessage(messageJson).subscribe(
      (res) => this.messageList.push(res.json()),
      (err) => console.error(err)
    );

    this.message = '';
  }    

  getKeyPress(e) {
    // se digitou apenas Enter, manda a mensagem, se apertou Shift também, não faz nada diferente, ou seja, apenas pula a linha
    if (e.code == "Enter" && !e.shiftKey) {
      this.sendMessage();
      e.preventDefault();
    }
  }

  scrollToBottom() : void {
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }

  public ngOnInit(): void {
    this.scrollToBottom();
  }

  public ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

}
