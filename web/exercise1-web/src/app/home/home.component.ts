import { Component, OnInit } from '@angular/core';
import {ChatService} from "../../shared/services/chat.service";
import {SupportModel} from "../../shared/models/support.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [ChatService]
})
export class HomeComponent implements OnInit {

  loadingSupport: boolean = false;
  error: boolean = false;
  support: SupportModel;

  constructor(private chatService: ChatService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {}

  getSupport() {
    this.loadingSupport = true;
    this.chatService.requestSupport().subscribe((support: SupportModel) => {
      this.support = support;
      this.loadingSupport = false;
    }, () => {
      this.error = true;
      this._snackBar.open("Ocurrió un error, por favor inténtelo nuevamente mas tarde.", "Cerrar", {
        duration: 5000,
        verticalPosition: 'top',
      });
    })
  }

  back() {
    this.support = undefined;
  }

}
