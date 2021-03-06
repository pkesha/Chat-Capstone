import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ChatService} from "../../services/chat/chat.service";

@Component({
  selector: 'app-chatmessages',
  templateUrl: './chatmessage.component.html',
  styleUrls: ['./chatmessage.component.css']
})
export class ChatmessageComponent implements OnInit {
  name = localStorage.getItem('name');
  user = localStorage.getItem('currentUser');

  @Input() chatMessage: any;

  @Output() deleteItemEvent = new EventEmitter<any>();

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
  }

  deleteMessage(chatMessageId: number): void {
    this.chatService.deleteChatMessage(chatMessageId)
      .subscribe((response: string) => {
        console.log(response + "message id " + chatMessageId);
        this.deleteItemEvent.emit();
      });
  }

  updateMessage(chatMessageId: number): void {
    this.chatService.updateChatMessage(chatMessageId)
      .subscribe((response: string) => {
        console.log(response + " chat id " + chatMessageId);
        this.deleteItemEvent.emit();
      });
  }

}
