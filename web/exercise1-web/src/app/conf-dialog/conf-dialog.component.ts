import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EmployeeModel} from "../../shared/models/employee.model";

@Component({
  selector: 'app-conf-dialog',
  templateUrl: './conf-dialog.component.html'
})
export class ConfDialogComponent implements OnInit{

  employeesQuantities: FormGroup = new FormGroup({});
  employeesObject: Object;

  constructor(
    public dialogRef: MatDialogRef<ConfDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EmployeeModel[]) {}

  ngOnInit(): void {
    this.employeesObject = this.data.reduce((obj, item) => Object.assign(obj, { [item.type]: item.quantity }), {});
    this.data.forEach(employee => {
      this.employeesQuantities.addControl(employee.type, new FormControl('', [Validators.required, Validators.min(0), Validators.pattern("[0-9]+")]))
    });
    setTimeout(() => this.employeesQuantities.setValue(this.employeesObject), 100);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.employeesQuantities.getRawValue());
  }

  getKeys(obj: Object) {
    return Object.keys(obj);
  }

}
