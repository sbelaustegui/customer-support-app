import {AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {SupportModel} from "../../shared/models/support.model";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewChecked {

  @ViewChild('chatContainer') private chatContainer: ElementRef;

  @Input()
  support: SupportModel;
  messages: string[] = [];
  allBusyMessage: string = "Todos nuestros representantes están ocupados, por favor inténtelo de nuevo mas tarde.";

  message: string;

  constructor() { }

  ngOnInit(): void {
  }

  sendMessage() {
    this.message && this.messages.push(this.message);
    this.message = undefined;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
    } catch(err) {}
  }
}
