import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {

  constructor(private httpClient: HttpClient) {}

  requestSupport() {
    return this.httpClient.get(environment.url + '/employee/support');
  }

  requestEmployees() {
    return this.httpClient.get(environment.url + '/employee');
  }

  updateEmployeesQuantity(employeeQuantities) {
    return this.httpClient.put(environment.url + '/employee/quantity', employeeQuantities);
  }
}
