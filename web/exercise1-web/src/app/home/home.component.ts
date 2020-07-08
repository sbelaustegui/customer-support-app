import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../shared/services/employee.service";
import {SupportModel} from "../../shared/models/support.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {ConfDialogComponent} from "../conf-dialog/conf-dialog.component";
import {EmployeeModel} from "../../shared/models/employee.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [EmployeeService]
})
export class HomeComponent implements OnInit {

  loadingSupport: boolean = false;
  error: boolean = false;
  support: SupportModel;
  employees: EmployeeModel[];

  constructor(private employeeService: EmployeeService, private _snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit(): void {}

  getSupport() {
    this.loadingSupport = true;
    this.employeeService.requestSupport().subscribe((support: SupportModel) => {
      this.support = support;
      this.loadingSupport = false;
    }, () => this.handleError())
  }

  handleConfig(): void {
    if (!this.employees) {
      this.employeeService.requestEmployees().subscribe((employees: EmployeeModel[])=> {
        this.employees = employees;
        this.openDialog();
      })
    } else {
      this.openDialog();
    }
  }

  openDialog() {
    const dialogRef = this.dialog.open(ConfDialogComponent, {
      width: '400px',
      data: this.employees
    });

    dialogRef.afterClosed().subscribe(result => {
      let employeeQuantities = Object.entries(result).map(([key, value]) => {
        return {
          type: key,
          quantity: parseInt(value.toString())
        }
      });
      this.employeeService.updateEmployeesQuantity(employeeQuantities).subscribe(() => {
        this.employees = employeeQuantities;
        this._snackBar.open("Actualizado con éxito.", "Cerrar", {
          duration: 5000,
          verticalPosition: 'top',
        });
      })
    }, () => this.handleError());
  }

  back() {
    this.support = undefined;
  }

  handleError() {
    this.error = true;
    this._snackBar.open("Ocurrió un error, por favor inténtelo nuevamente mas tarde.", "Cerrar", {
      duration: 5000,
      verticalPosition: 'top',
    });
  }

}
