import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class ChatService {

  constructor(private httpClient: HttpClient) {}

  requestSupport() {
    return this.httpClient.get(environment.url + '/employee/support');
  }
}
